package org.example.ecommerce.business.delivery;

import org.example.ecommerce.business.commons.EventsRepository;
import org.example.ecommerce.domain.delivery.command.AddDeliverymanCommand;
import org.example.ecommerce.domain.delivery.command.AddOrderCommand;
import org.example.ecommerce.domain.delivery.command.CreateCompanyCommand;
import org.example.ecommerce.domain.delivery.command.CreateDeliveryCommand;
import org.example.ecommerce.domain.delivery.events.CompanyCreated;
import org.example.ecommerce.domain.delivery.events.DeliveryCreated;
import org.example.ecommerce.domain.delivery.events.DeliverymanAdded;
import org.example.ecommerce.domain.delivery.events.OrderAdded;
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
class AddDeliverymanUseCaseTest {
    @Mock
    private EventsRepository eventsRepository;
    private AddDeliverymanUseCase addDeliverymanUseCase;

    @BeforeEach
    void setup(){addDeliverymanUseCase = new AddDeliverymanUseCase(eventsRepository);}

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

        AddDeliverymanCommand addDeliverymanCommand = new AddDeliverymanCommand(deliveryCreated.aggregateRootId(),
                companyCreated.getCompanyID(), "deliverymanID", "Carlos", "9874561");

        DeliverymanAdded deliverymanAdded = new DeliverymanAdded(addDeliverymanCommand.getDeliverymanID(),
                addDeliverymanCommand.getName(), addDeliverymanCommand.getPhone());
        deliverymanAdded.setAggregateRootId(addDeliverymanCommand.getDeliveryID());

        //Act
        Mockito.when(
                        eventsRepository.findByAggregatedRootId(addDeliverymanCommand.getDeliveryID()))
                .thenReturn(List.of(deliverymanAdded));

        Mockito.when(eventsRepository.saveEvent(ArgumentMatchers.any(DeliverymanAdded.class)))
                .thenAnswer(invocationOnMock -> {
                    return invocationOnMock.getArgument(0);
                });

        //action
        List<DomainEvent> domainEventList = addDeliverymanUseCase.apply(addDeliverymanCommand);
        DeliverymanAdded event = (DeliverymanAdded) domainEventList.get(0);

        //Assert
        Assertions.assertEquals(1, domainEventList.size());
        Assertions.assertEquals(event.aggregateRootId(), domainEventList.get(0).aggregateRootId());
        Assertions.assertEquals(event.getDeliverymanID(), ((DeliverymanAdded) domainEventList.get(0)).getDeliverymanID());
        Assertions.assertEquals(event.getName(), ((DeliverymanAdded) domainEventList.get(0)).getName());
        Assertions.assertInstanceOf(DeliverymanAdded.class, domainEventList.get(0));

    }
}