package org.example.ecommerce.business.delivery;

import org.example.ecommerce.business.commons.EventsRepository;
import org.example.ecommerce.domain.delivery.command.AddOrderCommand;
import org.example.ecommerce.domain.delivery.command.CreateDeliveryCommand;
import org.example.ecommerce.domain.delivery.events.DeliveryCreated;
import org.example.ecommerce.domain.delivery.events.OrderAdded;
import org.example.ecommerce.domain.order.events.ItemAdded;
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
class AddOrderUseCaseTest {
    @Mock
    private EventsRepository eventsRepository;
    private AddOrderUseCase addOrderUseCase;

    @BeforeEach
    void setup(){addOrderUseCase = new AddOrderUseCase(eventsRepository);}

    @Test
    void successfulScenario(){
        //Arrange
        CreateDeliveryCommand createDeliveryCommand = new CreateDeliveryCommand("deliveryID",
                LocalDate.now());

        DeliveryCreated deliveryCreated = new DeliveryCreated(
                createDeliveryCommand.getCreateDate(), createDeliveryCommand.getDeliveredDate());
        deliveryCreated.setAggregateRootId(createDeliveryCommand.getDeliveryID());

        AddOrderCommand addOrderCommand = new AddOrderCommand(deliveryCreated.aggregateRootId(),
                "orderID");

        OrderAdded orderAdded = new OrderAdded(addOrderCommand.getOrder());
        orderAdded.setAggregateRootId(addOrderCommand.getDeliveryID());

        //Act
        Mockito.when(
                        eventsRepository.findByAggregatedRootId(addOrderCommand.getDeliveryID()))
                .thenReturn(List.of(orderAdded));

        Mockito.when(eventsRepository.saveEvent(ArgumentMatchers.any(OrderAdded.class)))
                .thenAnswer(invocationOnMock -> {
                    return invocationOnMock.getArgument(0);
                });

        //action
        List<DomainEvent> domainEventList = addOrderUseCase.apply(addOrderCommand);
        OrderAdded event = (OrderAdded) domainEventList.get(0);

        //Assert
        Assertions.assertEquals(1, domainEventList.size());
        Assertions.assertEquals(event.getOrder(), ((OrderAdded) domainEventList.get(0)).getOrder());
        Assertions.assertEquals(event.aggregateRootId(), ((OrderAdded) domainEventList.get(0)).aggregateRootId());
        Assertions.assertInstanceOf(OrderAdded.class, domainEventList.get(0));

    }
}