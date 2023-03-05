package org.example.ecommerce.domain.delivery.events;

import org.example.ecommerce.domain.delivery.values.Order;
import org.example.ecommerce.generic.DomainEvent;

public class OrderAdded extends DomainEvent {
    private final String order;


    public OrderAdded(String order) {
        super("org.example.orderAdded");
        this.order = order;
    }

    public String getOrder() {
        return order;
    }
}
