package org.example.ecommerce.domain.delivery.events;

import org.example.ecommerce.generic.DomainEvent;

public class OrderRemoved extends DomainEvent {
    private final String order;


    public OrderRemoved(String order) {
        super("org.example.orderRemoved");
        this.order = order;
    }

    public String getOrder() {
        return order;
    }
}
