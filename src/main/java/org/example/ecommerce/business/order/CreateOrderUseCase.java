package org.example.ecommerce.business.order;

import org.example.ecommerce.business.commons.EventsRepository;
import org.example.ecommerce.business.commons.UseCaseForCommand;
import org.example.ecommerce.domain.order.Order;
import org.example.ecommerce.domain.order.command.CreateOrderCommand;
import org.example.ecommerce.domain.order.values.CreateDate;
import org.example.ecommerce.domain.order.values.OrderID;
import org.example.ecommerce.domain.order.values.Total;
import org.example.ecommerce.domain.order.values.UserID;
import org.example.ecommerce.generic.DomainEvent;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CreateOrderUseCase implements UseCaseForCommand<CreateOrderCommand> {
    private final EventsRepository eventsRepository;

    public CreateOrderUseCase(EventsRepository eventsRepository) {
        this.eventsRepository = eventsRepository;
    }

    @Override
    public List<DomainEvent> apply(CreateOrderCommand command) {
        Order order = new Order(OrderID.of(command.getOrderID()),new CreateDate(command.getCreateDate()));
        return order.getUncommittedChanges().stream().map(event->eventsRepository.saveEvent(event)).collect(Collectors.toList());
    }
}
