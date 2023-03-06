package org.example.ecommerce.business.order;

import org.example.ecommerce.business.commons.EventsRepository;
import org.example.ecommerce.domain.order.command.AddItemCommand;
import org.example.ecommerce.domain.order.command.CreatePaymentCommand;
import org.example.ecommerce.domain.order.events.ItemAdded;
import org.example.ecommerce.domain.order.events.OrderCreated;
import org.example.ecommerce.domain.order.events.PaymentCreated;
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
class AddItemUseCaseTest {
    @Mock
    private EventsRepository eventsRepository;
    private AddItemUseCase addItemUseCase;

    @BeforeEach
    void setup(){addItemUseCase = new AddItemUseCase(eventsRepository);}

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

        Mockito.when(
                eventsRepository.findByAggregatedRootId(addItemCommand.getOrderID()))
                .thenReturn(List.of(itemAdded));

        Mockito.when(eventsRepository.saveEvent(ArgumentMatchers.any(ItemAdded.class)))
                .thenAnswer(invocationOnMock -> {
                    return invocationOnMock.getArgument(0);
                });

        //action
        List<DomainEvent> domainEventList = addItemUseCase.apply(addItemCommand);
        ItemAdded event = (ItemAdded) domainEventList.get(0);

        //Assert
        Assertions.assertEquals(1, domainEventList.size());
        Assertions.assertEquals(addItemCommand.getOrderID(), domainEventList.get(0).aggregateRootId());
        Assertions.assertInstanceOf(ItemAdded.class, domainEventList.get(0));
    }
}