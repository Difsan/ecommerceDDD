package org.example.ecommerce.business.product;

import org.example.ecommerce.business.commons.EventsRepository;
import org.example.ecommerce.domain.product.command.CreateCategoryCommand;
import org.example.ecommerce.domain.product.events.CategoryCreated;
import org.example.ecommerce.domain.product.events.ProductCreated;
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

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CreateCategoryUseCaseTest {
    @Mock
    private EventsRepository eventsRepository;
    private CreateCategoryUseCase createCategoryUseCase;

    @BeforeEach
    void setup(){createCategoryUseCase = new CreateCategoryUseCase(eventsRepository);}

    @Test
    void successfulScenario(){

        //Arrange
        CreateCategoryCommand createCategoryCommand =
                new CreateCategoryCommand("productID", "categoryID","Phones");

        ProductCreated productCreated = new ProductCreated("Iphone 14",
                "Apple", "a good phone", 800.50, 8);
        productCreated.setAggregateRootId(createCategoryCommand.getProductID());
        CategoryCreated categoryCreated = new CategoryCreated("categoryID", "Phones");
        categoryCreated.setAggregateRootId(createCategoryCommand.getProductID());

        Mockito.when(
                eventsRepository.findByAggregatedRootId(createCategoryCommand.getProductID()))
                .thenReturn(List.of(categoryCreated));

        Mockito.when(eventsRepository.saveEvent(ArgumentMatchers.any(CategoryCreated.class)))
                .thenAnswer(invocationOnMock -> {
                    return invocationOnMock.getArgument(0);
                });

        //action
        List<DomainEvent> domainEventList = createCategoryUseCase.apply(createCategoryCommand);

        //Assert
        Assertions.assertEquals(1, domainEventList.size());
        Assertions.assertEquals(createCategoryCommand.getProductID(), domainEventList.get(0).aggregateRootId());
        Assertions.assertInstanceOf(CategoryCreated.class, domainEventList.get(0));
    }
}