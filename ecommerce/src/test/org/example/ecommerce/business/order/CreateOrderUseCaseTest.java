package org.example.ecommerce.business.order;

import org.example.ecommerce.business.commons.EventsRepository;
import org.example.ecommerce.domain.order.command.CreateOrderCommand;
import org.example.ecommerce.domain.order.events.OrderCreated;
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

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CreateOrderUseCaseTest {

    @Mock
    private EventsRepository eventsRepository;
    private CreateOrderUseCase createOrderUseCase;

    @BeforeEach
    void setup(){createOrderUseCase = new CreateOrderUseCase(eventsRepository);}

    @Test
    void successfulScenario() {
        CreateOrderCommand createOrderCommand = new CreateOrderCommand("orderID");
        OrderCreated orderCreated = new OrderCreated(LocalDate.now());
        orderCreated.setAggregateRootId(createOrderCommand.getOrderID());
        Mockito.when(eventsRepository.saveEvent(ArgumentMatchers.any(OrderCreated.class)))
                .thenAnswer(invocationOnMock -> {
                    return invocationOnMock.getArgument(0);
                });
        List<DomainEvent> domainEventList = createOrderUseCase.apply(createOrderCommand);
        OrderCreated event = (OrderCreated) domainEventList.get(0);

        Assertions.assertEquals(1, domainEventList.size());
        Assertions.assertEquals(orderCreated.aggregateRootId(), event.aggregateRootId());
        Assertions.assertEquals(orderCreated.getCreateDate(), event.getCreateDate());
    }

}