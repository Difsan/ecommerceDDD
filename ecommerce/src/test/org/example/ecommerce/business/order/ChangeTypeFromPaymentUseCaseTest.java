package org.example.ecommerce.business.order;

import org.example.ecommerce.business.commons.EventsRepository;
import org.example.ecommerce.domain.order.command.ChangeTypeFromPaymentCommand;
import org.example.ecommerce.domain.order.command.CreatePaymentCommand;
import org.example.ecommerce.domain.order.events.*;
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
class ChangeTypeFromPaymentUseCaseTest {
    @Mock
    private EventsRepository eventsRepository;
    private ChangeTypeFromPaymentUseCase changeTypeFromPaymentUseCase;

    @BeforeEach
    void setup(){changeTypeFromPaymentUseCase = new ChangeTypeFromPaymentUseCase(eventsRepository);}

    @Test
    void successfulScenario(){

        //Arrange
        OrderCreated orderCreated = new OrderCreated(LocalDate.now());
        orderCreated.setAggregateRootId("orderID");

        CreatePaymentCommand createPaymentCommand = new CreatePaymentCommand(orderCreated.aggregateRootId(),
                "paymentID", "Bank Account");

        PaymentCreated paymentCreated = new PaymentCreated(createPaymentCommand.getPaymentID(),
                createPaymentCommand.getType());
        paymentCreated.setAggregateRootId(createPaymentCommand.getOrderID());

        ChangeTypeFromPaymentCommand changeTypeFromPaymentCommand =
                new ChangeTypeFromPaymentCommand(paymentCreated.aggregateRootId(), paymentCreated.getPaymentID(),
                        "Credit Card");

        TypeChangedFromPayment typeChangedFromPayment = new TypeChangedFromPayment(
                changeTypeFromPaymentCommand.getPaymentID(), changeTypeFromPaymentCommand.getType());
        typeChangedFromPayment.setAggregateRootId(changeTypeFromPaymentCommand.getOrderID());

        Mockito.when(
                eventsRepository.findByAggregatedRootId(changeTypeFromPaymentCommand.getOrderID()))
                .thenReturn(List.of(typeChangedFromPayment));

        Mockito.when(eventsRepository.saveEvent(ArgumentMatchers.any(TypeChangedFromPayment.class)))
                .thenAnswer(invocationOnMock -> invocationOnMock.getArgument(0));

        //action
        List<DomainEvent> domainEventList = changeTypeFromPaymentUseCase.apply(changeTypeFromPaymentCommand);
        TypeChangedFromPayment event = (TypeChangedFromPayment) domainEventList.get(0);

        //Assert
        Assertions.assertEquals(1, domainEventList.size());
        Assertions.assertEquals(typeChangedFromPayment.aggregateRootId(), event.aggregateRootId());
        Assertions.assertInstanceOf(TypeChangedFromPayment.class, domainEventList.get(0));
        Assertions.assertEquals(typeChangedFromPayment.getType(), event.getType());
    }
}