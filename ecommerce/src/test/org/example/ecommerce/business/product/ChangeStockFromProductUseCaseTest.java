package org.example.ecommerce.business.product;

import org.example.ecommerce.business.commons.EventsRepository;
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

import java.util.List;

@ExtendWith(MockitoExtension.class)
class ChangeStockFromProductUseCaseTest {
    @Mock
    private EventsRepository eventsRepository;
    private ChangeStockFromProductUseCase changeStockFromProductUseCase;

    @BeforeEach
    void setup(){changeStockFromProductUseCase = new ChangeStockFromProductUseCase(eventsRepository);}

    @Test
    void successfulScenario(){

        //Arrange
        ChangeStockFromProductCommand changeStockFromProductCommand =
                new ChangeStockFromProductCommand("productID",9);

        ProductCreated productCreated = new ProductCreated("Iphone 14",
                "Apple", "a good phone", 800.50, 8);
        productCreated.setAggregateRootId(changeStockFromProductCommand.getProductID());
        StockChangedFromProduct stockChangedFromProduct = new StockChangedFromProduct(9);
        stockChangedFromProduct.setAggregateRootId(changeStockFromProductCommand.getProductID());

        Mockito.when(
                eventsRepository.findByAggregatedRootId(changeStockFromProductCommand.getProductID()))
                .thenReturn(List.of(stockChangedFromProduct));

        Mockito.when(eventsRepository.saveEvent(ArgumentMatchers.any(StockChangedFromProduct.class)))
                .thenAnswer(invocationOnMock -> {
                    return invocationOnMock.getArgument(0);
                });

        //action
        List<DomainEvent> domainEventList = changeStockFromProductUseCase.apply(changeStockFromProductCommand);
        StockChangedFromProduct event = (StockChangedFromProduct) domainEventList.get(0);

        //Assert
        Assertions.assertEquals(1, domainEventList.size());
        Assertions.assertEquals(changeStockFromProductCommand.getProductID(), domainEventList.get(0).aggregateRootId());
        Assertions.assertInstanceOf(StockChangedFromProduct.class, domainEventList.get(0));
        Assertions.assertEquals(9, event.getNewStock());
    }
}