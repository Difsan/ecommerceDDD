package org.example.ecommerce.business.product;

import org.example.ecommerce.business.commons.EventsRepository;
import org.example.ecommerce.domain.product.command.ChangeStockFromProductCommand;
import org.example.ecommerce.domain.product.command.ChangeTitleFromCategoryCommand;
import org.example.ecommerce.domain.product.events.CategoryCreated;
import org.example.ecommerce.domain.product.events.ProductCreated;
import org.example.ecommerce.domain.product.events.StockChangedFromProduct;
import org.example.ecommerce.domain.product.events.TitleChangedFromCategory;
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
class ChangeTitleFromCategoryUseCaseTest {
    @Mock
    private EventsRepository eventsRepository;
    private ChangeTitleFromCategoryUseCase changeTitleFromCategoryUseCase;

    @BeforeEach
    void setup(){changeTitleFromCategoryUseCase = new ChangeTitleFromCategoryUseCase(eventsRepository);}

    @Test
    void successfulScenario(){

        //Arrange

        ChangeTitleFromCategoryCommand changeTitleFromCategoryCommand =
                new ChangeTitleFromCategoryCommand("productID",
                        "categoryID","Mobile");

        ProductCreated productCreated = new ProductCreated("Iphone 14",
                "Apple", "a good phone", 800.50, 8);
        productCreated.setAggregateRootId(changeTitleFromCategoryCommand.getProductID());

        CategoryCreated categoryCreated = new CategoryCreated("categoryID", "Phones");
        categoryCreated.setAggregateRootId(changeTitleFromCategoryCommand.getProductID());

        TitleChangedFromCategory titleChangedFromCategory = new TitleChangedFromCategory("categoryID",
                "Mobile");
        titleChangedFromCategory.setAggregateRootId(changeTitleFromCategoryCommand.getProductID());

        Mockito.when(
                eventsRepository.findByAggregatedRootId(changeTitleFromCategoryCommand.getProductID()))
                .thenReturn(List.of(titleChangedFromCategory));

        Mockito.when(eventsRepository.saveEvent(ArgumentMatchers.any(TitleChangedFromCategory.class)))
                .thenAnswer(invocationOnMock -> {
                    return invocationOnMock.getArgument(0);
                });

        //action
        List<DomainEvent> domainEventList = changeTitleFromCategoryUseCase.apply(changeTitleFromCategoryCommand);
        TitleChangedFromCategory event = (TitleChangedFromCategory) domainEventList.get(0);
        
        //Assert
        Assertions.assertEquals(1, domainEventList.size());
        Assertions.assertEquals(changeTitleFromCategoryCommand.getProductID(), domainEventList.get(0).aggregateRootId());
        Assertions.assertInstanceOf(TitleChangedFromCategory.class, domainEventList.get(0));
        Assertions.assertEquals("Mobile", event.getNewTitle());
    }
}