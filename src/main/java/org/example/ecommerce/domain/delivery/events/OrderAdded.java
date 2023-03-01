package org.example.ecommerce.domain.delivery.events;

import org.example.ecommerce.domain.delivery.values.Order;
import org.example.ecommerce.generic.DomainEvent;

public class OrderAdded extends DomainEvent {
    private final Order order;


    public OrderAdded(Order order) {
        super("org.example.orderAdded");
        this.order = order;
    }

    public Order getOrder() {
        return order;
    }
}
