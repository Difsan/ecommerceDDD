package org.example.ecommerce.business.order;

import org.example.ecommerce.business.commons.EventsRepository;
import org.example.ecommerce.business.commons.UseCaseForCommand;
import org.example.ecommerce.domain.order.Order;
import org.example.ecommerce.domain.order.command.ChangeTotalFromOrderCommand;
import org.example.ecommerce.domain.order.values.OrderID;
import org.example.ecommerce.domain.order.values.Total;
import org.example.ecommerce.generic.DomainEvent;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ChangeTotalFromOrderUseCase implements UseCaseForCommand<ChangeTotalFromOrderCommand> {
    private final EventsRepository eventsRepository;

    public ChangeTotalFromOrderUseCase(EventsRepository eventsRepository) {
        this.eventsRepository = eventsRepository;
    }

    @Override
    public List<DomainEvent> apply(ChangeTotalFromOrderCommand command) {
        List<DomainEvent> orderEvents =  eventsRepository.findByAggregatedRootId(command.getOrderID());
        Order order = Order.from(OrderID.of(command.getOrderID()), orderEvents);
        order.changeTotalFromOrder(new Total(command.getNewTotal()));
        return order.getUncommittedChanges().stream().map(event->eventsRepository.saveEvent(event)).collect(Collectors.toList());
    }
}
