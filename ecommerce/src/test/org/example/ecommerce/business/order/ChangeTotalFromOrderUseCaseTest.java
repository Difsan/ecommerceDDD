package org.example.ecommerce.business.order;

import org.example.ecommerce.business.commons.EventsRepository;
import org.example.ecommerce.business.product.ChangeStockFromProductUseCase;
import org.example.ecommerce.domain.order.command.ChangeTotalFromOrderCommand;
import org.example.ecommerce.domain.order.events.OrderCreated;
import org.example.ecommerce.domain.order.events.TotalChangedFromOrder;
import org.example.ecommerce.domain.product.command.ChangeStockFromProductCommand;
import org.example.ecommerce.domain.product.events.ProductCreated;
import org.example.ecommerce.domain.product.events.StockChangedFromProduct;
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
class ChangeTotalFromOrderUseCaseTest {
    @Mock
    private EventsRepository eventsRepository;
    private ChangeTotalFromOrderUseCase changeTotalFromOrderUseCase;

    @BeforeEach
    void setup(){changeTotalFromOrderUseCase = new ChangeTotalFromOrderUseCase(eventsRepository);}

    @Test
    void successfulScenario(){

        //Arrange
        OrderCreated orderCreated = new OrderCreated(LocalDate.now());
        orderCreated.setAggregateRootId("orderID");

        ChangeTotalFromOrderCommand changeTotalFromOrderCommand = new ChangeTotalFromOrderCommand(
                orderCreated.aggregateRootId(), 987.65);

        TotalChangedFromOrder totalChangedFromOrder =
                new TotalChangedFromOrder(changeTotalFromOrderCommand.getNewTotal());
        totalChangedFromOrder.setAggregateRootId(changeTotalFromOrderCommand.getOrderID());

        Mockito.when(
                eventsRepository.findByAggregatedRootId(changeTotalFromOrderCommand.getOrderID()))
                .thenReturn(List.of(totalChangedFromOrder));

        Mockito.when(eventsRepository.saveEvent(ArgumentMatchers.any(TotalChangedFromOrder.class)))
                .thenAnswer(invocationOnMock -> {
                    return invocationOnMock.getArgument(0);
                });

        //action
        List<DomainEvent> domainEventList = changeTotalFromOrderUseCase.apply(changeTotalFromOrderCommand);
        TotalChangedFromOrder event = (TotalChangedFromOrder) domainEventList.get(0);

        //Assert
        Assertions.assertEquals(1, domainEventList.size());
        Assertions.assertEquals(changeTotalFromOrderCommand.getOrderID(), domainEventList.get(0).aggregateRootId());
        Assertions.assertInstanceOf(TotalChangedFromOrder.class, domainEventList.get(0));
        Assertions.assertEquals(totalChangedFromOrder.getNewTotal(), event.getNewTotal());
    }
}