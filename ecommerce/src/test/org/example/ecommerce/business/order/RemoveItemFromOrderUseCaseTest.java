package org.example.ecommerce.business.order;

import org.example.ecommerce.business.commons.EventsRepository;
import org.example.ecommerce.domain.order.command.AddItemCommand;
import org.example.ecommerce.domain.order.command.RemoveItemFromOrderCommand;
import org.example.ecommerce.domain.order.events.ItemAdded;
import org.example.ecommerce.domain.order.events.ItemRemovedFromOrder;
import org.example.ecommerce.domain.order.events.OrderCreated;
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
class RemoveItemFromOrderUseCaseTest {
    @Mock
    private EventsRepository eventsRepository;
    private RemoveItemFromOrderUseCase removeItemFromOrderUseCase;

    @BeforeEach
    void setup(){removeItemFromOrderUseCase = new RemoveItemFromOrderUseCase(eventsRepository);}

    @Test
    void successfulScenario(){

        //Arrange
        OrderCreated orderCreated = new OrderCreated(LocalDate.now());
        orderCreated.setAggregateRootId("orderID");

        ItemAdded itemAdded = new ItemAdded("itemID", "productID", 5, 90.65);
        itemAdded.setAggregateRootId(orderCreated.aggregateRootId());

        RemoveItemFromOrderCommand removeItemFromOrderCommand = new RemoveItemFromOrderCommand(itemAdded.aggregateRootId(),
                itemAdded.getProductID(), itemAdded.getItemID());

        ItemRemovedFromOrder itemRemovedFromOrder = new ItemRemovedFromOrder(removeItemFromOrderCommand.getProductID(),
                removeItemFromOrderCommand.getItemID());
        itemRemovedFromOrder.setAggregateRootId(orderCreated.aggregateRootId());

        Mockito.when(
                eventsRepository.findByAggregatedRootId(itemRemovedFromOrder.aggregateRootId()))
                .thenReturn(List.of(itemRemovedFromOrder));

        Mockito.when(eventsRepository.saveEvent(ArgumentMatchers.any(ItemRemovedFromOrder.class)))
                .thenAnswer(invocationOnMock -> {
                    return invocationOnMock.getArgument(0);
                });

        //action
        List<DomainEvent> domainEventList = removeItemFromOrderUseCase.apply(removeItemFromOrderCommand);
        ItemRemovedFromOrder event = (ItemRemovedFromOrder) domainEventList.get(0);

        //Assert
        Assertions.assertEquals(1, domainEventList.size());
        Assertions.assertEquals(event.aggregateRootId(), domainEventList.get(0).aggregateRootId());
        Assertions.assertInstanceOf(ItemRemovedFromOrder.class, domainEventList.get(0));
    }
}