package org.example.ecommerce.business.order;

import org.example.ecommerce.business.commons.EventsRepository;
import org.example.ecommerce.business.commons.UseCaseForCommand;
import org.example.ecommerce.domain.order.Order;
import org.example.ecommerce.domain.order.command.ChangeQuantityFromItemCommand;
import org.example.ecommerce.domain.order.values.ItemID;
import org.example.ecommerce.domain.order.values.OrderID;
import org.example.ecommerce.domain.order.values.Quantity;
import org.example.ecommerce.generic.DomainEvent;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ChangeQuantityFromItemUseCase implements UseCaseForCommand<ChangeQuantityFromItemCommand> {
    private final EventsRepository eventsRepository;

    public ChangeQuantityFromItemUseCase(EventsRepository eventsRepository) {
        this.eventsRepository = eventsRepository;
    }

    @Override
    public List<DomainEvent> apply(ChangeQuantityFromItemCommand command) {
        List<DomainEvent> orderEvents =  eventsRepository.findByAggregatedRootId(command.getOrderID());
        Order order = Order.from(OrderID.of(command.getOrderID()), orderEvents);
        order.changeQuantityFromItem(ItemID.of(command.getItemID()), new Quantity(command.getNewQuantity()));
        return order.getUncommittedChanges().stream().map(event->eventsRepository.saveEvent(event)).collect(Collectors.toList());
    }
}
