package org.example.ecommerce.business.product;

import org.example.ecommerce.business.commons.EventsRepository;
import org.example.ecommerce.domain.product.command.ChangeEmailFromSellerCommand;
import org.example.ecommerce.domain.product.command.ChangeTitleFromCategoryCommand;
import org.example.ecommerce.domain.product.events.*;
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
class ChangeEmailFromUserUseCaseTest {
    @Mock
    private EventsRepository eventsRepository;
    private ChangeEmailFromSellerUseCase changeEmailFromSellerUseCase;

    @BeforeEach
    void setup(){changeEmailFromSellerUseCase = new ChangeEmailFromSellerUseCase(eventsRepository);}

    @Test
    void successfulScenario(){

        //Arrange

        ProductCreated productCreated = new ProductCreated("Iphone 14",
                "Apple", "a good phone", 800.50, 8);
        productCreated.setAggregateRootId("productID");

        SellerCreated sellerCreated = new SellerCreated("sellerID", "IShop",
                "879654", "ishop@gmail.com", "Official shop for Apple products");
        sellerCreated.setAggregateRootId(productCreated.aggregateRootId());

        ChangeEmailFromSellerCommand changeEmailFromSellerCommand =
                new ChangeEmailFromSellerCommand(productCreated.aggregateRootId(),
                        sellerCreated.getSellerID(), "newEmail@hotmail.com");


        EmailChangedFromSeller emailChangedFromSeller = new EmailChangedFromSeller(changeEmailFromSellerCommand.getSellerID(),
                changeEmailFromSellerCommand.getEmail());
        emailChangedFromSeller.setAggregateRootId(changeEmailFromSellerCommand.getProductID());

        Mockito.when(
                eventsRepository.findByAggregatedRootId(changeEmailFromSellerCommand.getProductID()))
                .thenReturn(List.of(emailChangedFromSeller));

        Mockito.when(eventsRepository.saveEvent(ArgumentMatchers.any(EmailChangedFromSeller.class)))
                .thenAnswer(invocationOnMock -> {
                    return invocationOnMock.getArgument(0);
                });

        //action
        List<DomainEvent> domainEventList = changeEmailFromSellerUseCase.apply(changeEmailFromSellerCommand);
        EmailChangedFromSeller event = ( EmailChangedFromSeller) domainEventList.get(0);
        
        //Assert
        Assertions.assertEquals(1, domainEventList.size());
        Assertions.assertEquals(changeEmailFromSellerCommand.getProductID(), domainEventList.get(0).aggregateRootId());
        Assertions.assertInstanceOf(EmailChangedFromSeller.class, domainEventList.get(0));
        Assertions.assertEquals(emailChangedFromSeller.getEmail(), event.getEmail());
    }
}