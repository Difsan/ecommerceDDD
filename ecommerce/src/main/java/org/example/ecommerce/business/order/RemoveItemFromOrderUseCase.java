package org.example.ecommerce.business.order;

import org.example.ecommerce.business.commons.EventsRepository;
import org.example.ecommerce.business.commons.UseCaseForCommand;
import org.example.ecommerce.domain.order.Order;
import org.example.ecommerce.domain.order.command.RemoveItemFromOrderCommand;
import org.example.ecommerce.domain.order.values.ItemID;
import org.example.ecommerce.domain.order.values.OrderID;
import org.example.ecommerce.domain.product.values.ProductID;
import org.example.ecommerce.generic.DomainEvent;

import java.util.List;
import java.util.stream.Collectors;

public class RemoveItemFromOrderUseCase implements UseCaseForCommand<RemoveItemFromOrderCommand> {
    private final EventsRepository eventsRepository;

    public RemoveItemFromOrderUseCase(EventsRepository eventsRepository) {
        this.eventsRepository = eventsRepository;
    }

    @Override
    public List<DomainEvent> apply(RemoveItemFromOrderCommand command) {
        List<DomainEvent> orderEvents =  eventsRepository.findByAggregatedRootId(command.getOrderID());
        Order order = Order.from(OrderID.of(command.getOrderID()), orderEvents);
        order.removeItemFromOrder(ProductID.of(command.getProductID()), ItemID.of(command.getItemID()));
        return order.getUncommittedChanges().stream().map(event->eventsRepository.saveEvent(event)).collect(Collectors.toList());
    }
}
