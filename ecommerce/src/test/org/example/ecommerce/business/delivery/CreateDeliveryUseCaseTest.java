package org.example.ecommerce.business.delivery;

import org.example.ecommerce.business.commons.EventsRepository;
import org.example.ecommerce.domain.delivery.command.CreateDeliveryCommand;
import org.example.ecommerce.domain.delivery.events.DeliveryCreated;
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
class CreateDeliveryUseCaseTest {
    @Mock
    private EventsRepository eventsRepository;
    private CreateDeliveryUseCase createDeliveryUseCase;

    @BeforeEach
    void setup(){createDeliveryUseCase = new CreateDeliveryUseCase(eventsRepository);}

    @Test
    void successfulScenario(){
        //Arrange
        CreateDeliveryCommand createDeliveryCommand = new CreateDeliveryCommand("deliveryID",
                LocalDate.now());

        DeliveryCreated deliveryCreated = new DeliveryCreated(
                createDeliveryCommand.getCreateDate(), createDeliveryCommand.getDeliveredDate());
        deliveryCreated.setAggregateRootId(createDeliveryCommand.getDeliveryID());

        Mockito.when(eventsRepository.saveEvent(ArgumentMatchers.any(DeliveryCreated.class)))
                .thenAnswer(invocationOnMock -> {
                    return invocationOnMock.getArgument(0);
                });

        //Act
        List<DomainEvent> domainEventList = createDeliveryUseCase.apply(createDeliveryCommand);
        DeliveryCreated event = (DeliveryCreated) domainEventList.get(0);

        //Assert
        Assertions.assertEquals(1, domainEventList.size());
        Assertions.assertEquals(deliveryCreated.aggregateRootId(), event.aggregateRootId());
        Assertions.assertEquals(deliveryCreated.getCreateDate(), event.getCreateDate());
        Assertions.assertEquals(deliveryCreated.getDeliveredDate(), event.getDeliveredDate());

    }
}