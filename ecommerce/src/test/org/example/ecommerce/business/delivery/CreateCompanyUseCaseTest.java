package org.example.ecommerce.business.delivery;

import org.example.ecommerce.business.commons.EventsRepository;
import org.example.ecommerce.domain.delivery.command.AddOrderCommand;
import org.example.ecommerce.domain.delivery.command.CreateCompanyCommand;
import org.example.ecommerce.domain.delivery.command.CreateDeliveryCommand;
import org.example.ecommerce.domain.delivery.events.CompanyCreated;
import org.example.ecommerce.domain.delivery.events.DeliveryCreated;
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
class CreateCompanyUseCaseTest {
    @Mock
    private EventsRepository eventsRepository;
    private CreateCompanyUseCase createCompanyUseCase;

    @BeforeEach
    void setup(){createCompanyUseCase = new CreateCompanyUseCase(eventsRepository);}

    @Test
    void successfulScenario(){
        //Arrange
        CreateDeliveryCommand createDeliveryCommand = new CreateDeliveryCommand("deliveryID",
                LocalDate.now());

        DeliveryCreated deliveryCreated = new DeliveryCreated(
                createDeliveryCommand.getCreateDate(), createDeliveryCommand.getDeliveredDate());
        deliveryCreated.setAggregateRootId(createDeliveryCommand.getDeliveryID());

        CreateCompanyCommand createCompanyCommand = new CreateCompanyCommand(deliveryCreated.aggregateRootId(), "companyID",
                "Apple", "689745");

        CompanyCreated companyCreated = new CompanyCreated(createCompanyCommand.getCompanyID(),
                createCompanyCommand.getName(), createCompanyCommand.getPhone());
        companyCreated.setAggregateRootId(createCompanyCommand.getDeliveryID());

        //Act
        Mockito.when(
                        eventsRepository.findByAggregatedRootId(createCompanyCommand.getDeliveryID()))
                .thenReturn(List.of(companyCreated));

        Mockito.when(eventsRepository.saveEvent(ArgumentMatchers.any(CompanyCreated.class)))
                .thenAnswer(invocationOnMock -> {
                    return invocationOnMock.getArgument(0);
                });

        //action
        List<DomainEvent> domainEventList = createCompanyUseCase.apply(createCompanyCommand);
        CompanyCreated event = (CompanyCreated) domainEventList.get(0);

        //Assert
        Assertions.assertEquals(1, domainEventList.size());
        Assertions.assertEquals(event.aggregateRootId(), domainEventList.get(0).aggregateRootId());
        Assertions.assertEquals(event.getCompanyID(), ((CompanyCreated) domainEventList.get(0)).getCompanyID());
        Assertions.assertEquals(event.getName(), ((CompanyCreated) domainEventList.get(0)).getName());
        Assertions.assertInstanceOf(CompanyCreated.class, domainEventList.get(0));

    }
}