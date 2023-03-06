package org.example.ecommerce.business.delivery;

import org.example.ecommerce.business.commons.EventsRepository;
import org.example.ecommerce.domain.delivery.command.AddDeliverymanCommand;
import org.example.ecommerce.domain.delivery.command.CreateDeliveryCommand;
import org.example.ecommerce.domain.delivery.command.RemoveDeliverymanFromCompanyCommand;
import org.example.ecommerce.domain.delivery.events.CompanyCreated;
import org.example.ecommerce.domain.delivery.events.DeliveryCreated;
import org.example.ecommerce.domain.delivery.events.DeliverymanAdded;
import org.example.ecommerce.domain.delivery.events.DeliverymanRemovedFromCompany;
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
class RemoveDeliverymanUseCaseTest {
    @Mock
    private EventsRepository eventsRepository;
    private RemoveDeliverymanFromCompanyUseCase removeDeliverymanFromCompanyUseCase;

    @BeforeEach
    void setup(){
        removeDeliverymanFromCompanyUseCase = new RemoveDeliverymanFromCompanyUseCase(eventsRepository);}

    @Test
    void successfulScenario(){
        //Arrange
        CreateDeliveryCommand createDeliveryCommand = new CreateDeliveryCommand("deliveryID",
                LocalDate.now());

        DeliveryCreated deliveryCreated = new DeliveryCreated(
                createDeliveryCommand.getCreateDate(), createDeliveryCommand.getDeliveredDate());
        deliveryCreated.setAggregateRootId(createDeliveryCommand.getDeliveryID());

        CompanyCreated companyCreated = new CompanyCreated("companyID",
                "Apple", "689745");
        companyCreated.setAggregateRootId(deliveryCreated.aggregateRootId());

        DeliverymanAdded deliverymanAdded = new DeliverymanAdded("deliverymanID", "Carlos", "9874561");
        deliverymanAdded.setAggregateRootId(companyCreated.aggregateRootId());

        RemoveDeliverymanFromCompanyCommand removeDeliverymanFromCompanyCommand = new RemoveDeliverymanFromCompanyCommand(
                deliveryCreated.aggregateRootId(), companyCreated.getCompanyID(), deliverymanAdded.getDeliverymanID()
        );

        DeliverymanRemovedFromCompany deliverymanRemovedFromCompany = new DeliverymanRemovedFromCompany(
                removeDeliverymanFromCompanyCommand.getCompanyID(),
                removeDeliverymanFromCompanyCommand.getDeliveryID());

        //Act
        Mockito.when(
                        eventsRepository.findByAggregatedRootId(removeDeliverymanFromCompanyCommand.getDeliveryID()))
                .thenReturn(List.of(deliverymanAdded));

        Mockito.when(eventsRepository.saveEvent(ArgumentMatchers.any(DeliverymanRemovedFromCompany.class)))
                .thenAnswer(invocationOnMock -> {
                    return invocationOnMock.getArgument(0);
                });

        //action
        List<DomainEvent> domainEventList = removeDeliverymanFromCompanyUseCase.apply(removeDeliverymanFromCompanyCommand);
        DeliverymanRemovedFromCompany event = (DeliverymanRemovedFromCompany) domainEventList.get(0);

        //Assert
        Assertions.assertEquals(1, domainEventList.size());
        Assertions.assertEquals(event.aggregateRootId(), domainEventList.get(0).aggregateRootId());
        Assertions.assertEquals(event.getDeliverymanID(), ((DeliverymanRemovedFromCompany) domainEventList.get(0)).getDeliverymanID());
        Assertions.assertEquals(event.getCompanyID(), ((DeliverymanRemovedFromCompany) domainEventList.get(0)).getCompanyID());
        Assertions.assertInstanceOf(DeliverymanRemovedFromCompany.class, domainEventList.get(0));

    }
}