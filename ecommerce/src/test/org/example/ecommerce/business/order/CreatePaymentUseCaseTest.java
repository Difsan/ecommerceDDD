package org.example.ecommerce.business.order;

import org.example.ecommerce.business.commons.EventsRepository;
import org.example.ecommerce.business.product.CreateCategoryUseCase;
import org.example.ecommerce.domain.order.command.CreatePaymentCommand;
import org.example.ecommerce.domain.order.events.OrderCreated;
import org.example.ecommerce.domain.order.events.PaymentCreated;
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

import java.time.LocalDate;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class CreatePaymentUseCaseTest {
    @Mock
    private EventsRepository eventsRepository;
    private CreatePaymentUseCase createPaymentUseCase;

    @BeforeEach
    void setup(){createPaymentUseCase = new CreatePaymentUseCase(eventsRepository);}

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

        Mockito.when(
                eventsRepository.findByAggregatedRootId(createPaymentCommand.getOrderID()))
                .thenReturn(List.of(paymentCreated));

        Mockito.when(eventsRepository.saveEvent(ArgumentMatchers.any(PaymentCreated.class)))
                .thenAnswer(invocationOnMock -> {
                    return invocationOnMock.getArgument(0);
                });

        //action
        List<DomainEvent> domainEventList = createPaymentUseCase.apply(createPaymentCommand);
        PaymentCreated event = (PaymentCreated) domainEventList.get(0);

        //Assert
        Assertions.assertEquals(1, domainEventList.size());
        Assertions.assertEquals(createPaymentCommand.getOrderID(), domainEventList.get(0).aggregateRootId());
        Assertions.assertInstanceOf(PaymentCreated.class, domainEventList.get(0));
    }
}