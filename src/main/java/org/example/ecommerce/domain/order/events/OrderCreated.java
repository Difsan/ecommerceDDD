package org.example.ecommerce.domain.order.events;

import org.example.ecommerce.domain.order.values.Information;
import org.example.ecommerce.generic.DomainEvent;

public class OrderCreated extends DomainEvent {

    private Information information;
    private String userID;

    public OrderCreated(String type) {
        super(type);
    }
}
