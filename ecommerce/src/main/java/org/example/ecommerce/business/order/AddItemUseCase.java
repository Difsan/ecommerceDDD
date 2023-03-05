package org.example.ecommerce.business.order;

import org.example.ecommerce.business.commons.EventsRepository;
import org.example.ecommerce.business.commons.UseCaseForCommand;
import org.example.ecommerce.domain.order.Order;
import org.example.ecommerce.domain.order.command.AddItemCommand;
import org.example.ecommerce.domain.order.values.*;
import org.example.ecommerce.domain.product.Product;
import org.example.ecommerce.domain.product.values.ProductID;
import org.example.ecommerce.generic.DomainEvent;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AddItemUseCase implements UseCaseForCommand<AddItemCommand> {

    private final EventsRepository eventsRepository;

    public AddItemUseCase(EventsRepository eventsRepository) {
        this.eventsRepository = eventsRepository;
    }

    @Override
    public List<DomainEvent> apply(AddItemCommand command) {
        List<DomainEvent> orderEvents =  eventsRepository.findByAggregatedRootId(command.getOrderID());
        Order order = Order.from(OrderID.of(command.getOrderID()), orderEvents);
        order.addAnItem(ItemID.of(command.getItemID()), ProductID.of(command.getProductID()),
                new Quantity(command.getQuantity()), new SubTotal(command.getSubTotal()));
        return order.getUncommittedChanges().stream().map(event->eventsRepository.saveEvent(event)).collect(Collectors.toList());
    }
}
