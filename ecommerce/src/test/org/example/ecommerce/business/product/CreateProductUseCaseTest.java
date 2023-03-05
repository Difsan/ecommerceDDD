package org.example.ecommerce.business.product;

import org.example.ecommerce.business.commons.EventsRepository;
import org.example.ecommerce.domain.product.command.CreateProductCommand;
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
@ExtendWith(MockitoExtension.class)
class CreateProductUseCaseTest {
    @Mock
    private EventsRepository eventsRepository;
    private CreateProductUseCase createProductUseCase;

    @BeforeEach
    void setup(){
        createProductUseCase = new CreateProductUseCase(eventsRepository);
    }

    @Test
    void successfulScenario(){

        CreateProductCommand createProductCommand = new CreateProductCommand("productID", "Iphone 14",
                "Apple", "a good phone", 800.50, 8);
        ProductCreated productCreated = new ProductCreated("Iphone 14",
                "Apple", "a good phone", 800.50, 8);
        productCreated.setAggregateRootId("productID");
        Mockito.when(eventsRepository.saveEvent(ArgumentMatchers.any(ProductCreated.class)))
                .thenAnswer(invocationOnMock -> {
                    return invocationOnMock.getArgument(0);
                });
        List<DomainEvent> domainEventList = createProductUseCase.apply(createProductCommand);

        Assertions.assertEquals(1, domainEventList.size());
        Assertions.assertEquals("productID", domainEventList.get(0).aggregateRootId());
    }
}