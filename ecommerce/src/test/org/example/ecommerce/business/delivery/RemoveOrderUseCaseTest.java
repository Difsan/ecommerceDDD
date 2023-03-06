package org.example.ecommerce.business.delivery;

import org.example.ecommerce.business.commons.EventsRepository;
import org.example.ecommerce.domain.delivery.command.AddOrderCommand;
import org.example.ecommerce.domain.delivery.command.CreateDeliveryCommand;
import org.example.ecommerce.domain.delivery.command.RemoveOrderCommand;
import org.example.ecommerce.domain.delivery.events.DeliveryCreated;
import org.example.ecommerce.domain.delivery.events.OrderAdded;
import org.example.ecommerce.domain.delivery.events.OrderRemoved;
import org.example.ecommerce.generic.DomainEvent;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class RemoveOrderUseCaseTest {
    @Mock
    private EventsRepository eventsRepository;
    private RemoveOrderUseCase removeOrderUseCase;

    @BeforeEach
    void setup(){
        removeOrderUseCase = new RemoveOrderUseCase(eventsRepository);}

    @Test
    void successfulScenario(){
        //Arrange
        CreateDeliveryCommand createDeliveryCommand = new CreateDeliveryCommand("deliveryID",
                LocalDate.now());

        DeliveryCreated deliveryCreated = new DeliveryCreated(
                createDeliveryCommand.getCreateDate(), createDeliveryCommand.getDeliveredDate());
        deliveryCreated.setAggregateRootId(createDeliveryCommand.getDeliveryID());

        OrderAdded orderAdded = new OrderAdded(
                "orderID");
        orderAdded.setAggregateRootId(deliveryCreated.aggregateRootId());

        RemoveOrderCommand removeOrderCommand = new RemoveOrderCommand(orderAdded.aggregateRootId(),
                orderAdded.getOrder());

        OrderRemoved orderRemoved = new OrderRemoved(removeOrderCommand.getOrder());
        orderRemoved.setAggregateRootId(removeOrderCommand.getDeliveryID());

        //Act
        Mockito.when(
                        eventsRepository.findByAggregatedRootId(removeOrderCommand.getDeliveryID()))
                .thenReturn(List.of(orderAdded));

        Mockito.when(eventsRepository.saveEvent(ArgumentMatchers.any(OrderRemoved.class)))
                .thenAnswer(invocationOnMock -> {
                    return invocationOnMock.getArgument(0);
                });

        //action
        List<DomainEvent> domainEventList = removeOrderUseCase.apply(removeOrderCommand);
        OrderRemoved event = (OrderRemoved) domainEventList.get(0);

        //Assert
        Assertions.assertEquals(1, domainEventList.size());
        Assertions.assertEquals(event.aggregateRootId(), domainEventList.get(0).aggregateRootId());
        Assertions.assertEquals(event.getOrder(), ((OrderRemoved) domainEventList.get(0)).getOrder());
        Assertions.assertInstanceOf(OrderRemoved.class, domainEventList.get(0));

    }
}