package org.example.ecommerce.business.order;

import org.example.ecommerce.business.commons.EventsRepository;
import org.example.ecommerce.domain.order.command.AddItemCommand;
import org.example.ecommerce.domain.order.command.ChangeQuantityFromItemCommand;
import org.example.ecommerce.domain.order.command.ChangeTotalFromOrderCommand;
import org.example.ecommerce.domain.order.events.ItemAdded;
import org.example.ecommerce.domain.order.events.OrderCreated;
import org.example.ecommerce.domain.order.events.QuantityChangedFromItem;
import org.example.ecommerce.domain.order.events.TotalChangedFromOrder;
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
class ChangeQuantityFromItemUseCaseTest {
    @Mock
    private EventsRepository eventsRepository;
    private ChangeQuantityFromItemUseCase changeQuantityFromItemUseCase;

    @BeforeEach
    void setup(){changeQuantityFromItemUseCase = new ChangeQuantityFromItemUseCase(eventsRepository);}

    @Test
    void successfulScenario(){

        //Arrange
        OrderCreated orderCreated = new OrderCreated(LocalDate.now());
        orderCreated.setAggregateRootId("orderID");

        AddItemCommand addItemCommand = new AddItemCommand(orderCreated.aggregateRootId(),
                "itemID", "productID", 5, 90.65);

        ItemAdded itemAdded = new ItemAdded(addItemCommand.getItemID(), addItemCommand.getProductID(),
                addItemCommand.getQuantity(), addItemCommand.getSubTotal());
        itemAdded.setAggregateRootId(orderCreated.aggregateRootId());

        ChangeQuantityFromItemCommand changeQuantityFromItemCommand = new ChangeQuantityFromItemCommand(
                orderCreated.aggregateRootId(), itemAdded.getItemID(), 10);

        QuantityChangedFromItem quantityChangedFromItem =
                new QuantityChangedFromItem(changeQuantityFromItemCommand.getItemID(),
                        changeQuantityFromItemCommand.getNewQuantity());
        quantityChangedFromItem.setAggregateRootId(changeQuantityFromItemCommand.getOrderID());

        Mockito.when(
                eventsRepository.findByAggregatedRootId(changeQuantityFromItemCommand.getOrderID()))
                .thenReturn(List.of(quantityChangedFromItem));

        Mockito.when(eventsRepository.saveEvent(ArgumentMatchers.any(QuantityChangedFromItem.class)))
                .thenAnswer(invocationOnMock -> {
                    return invocationOnMock.getArgument(0);
                });

        //action
        List<DomainEvent> domainEventList = changeQuantityFromItemUseCase.apply(changeQuantityFromItemCommand);
        QuantityChangedFromItem event = (QuantityChangedFromItem) domainEventList.get(0);

        //Assert
        Assertions.assertEquals(1, domainEventList.size());
        Assertions.assertEquals(quantityChangedFromItem.aggregateRootId(), event.aggregateRootId());
        Assertions.assertInstanceOf(QuantityChangedFromItem.class, domainEventList.get(0));
        Assertions.assertEquals(quantityChangedFromItem.getNewQuantity(), event.getNewQuantity());
    }
}