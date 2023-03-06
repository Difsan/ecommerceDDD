package org.example.ecommerce.business.product;

import org.example.ecommerce.business.commons.EventsRepository;
import org.example.ecommerce.domain.product.command.CreateSellerCommand;
import org.example.ecommerce.domain.product.events.CategoryCreated;
import org.example.ecommerce.domain.product.events.SellerCreated;
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
class CreateSellerUseCaseTest {

    @Mock
    private EventsRepository eventsRepository;
    private CreateSellerUseCase createSellerUseCase;

    @BeforeEach
    void setup(){createSellerUseCase = new CreateSellerUseCase(eventsRepository);}

    @Test
    void successfulScenario(){
        //Arrange
        CreateSellerCommand createSellerCommand = new CreateSellerCommand("productID",
                "sellerID", "IShop",
                "879654", "ishop@gmail.com", "Official shop for Apple products"
                );

        SellerCreated sellerCreated = new SellerCreated("sellerID", "IShop",
                "879654", "ishop@gmail.com", "Official shop for Apple products");
        sellerCreated.setAggregateRootId(createSellerCommand.getProductID());

        Mockito.when(
                eventsRepository.findByAggregatedRootId(createSellerCommand.getProductID())
        ).thenReturn(List.of(sellerCreated));

        Mockito.when(eventsRepository.saveEvent(ArgumentMatchers.any(SellerCreated.class)))
                .thenAnswer(invocationOnMock -> {
                    return invocationOnMock.getArgument(0);
                });

        //action
        List<DomainEvent> domainEventList = createSellerUseCase.apply(createSellerCommand);

        //Assert
        Assertions.assertEquals(1, domainEventList.size());
        Assertions.assertEquals(createSellerCommand.getProductID(), domainEventList.get(0).aggregateRootId());
        Assertions.assertInstanceOf(SellerCreated.class, domainEventList.get(0));
    }

}