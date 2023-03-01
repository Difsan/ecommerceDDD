package org.example.ecommerce.domain.order.events;

import org.example.ecommerce.domain.order.values.Information;
import org.example.ecommerce.domain.order.values.User;
import org.example.ecommerce.generic.DomainEvent;

public class OrderCreated extends DomainEvent {

    private Information information;
    private User userID;

    public OrderCreated(Information information, User userID) {
        super("org.example.orderCreated");
        this.information = information;
        this.userID = userID;
    }

    public Information getInformation() {
        return information;
    }

    public User getUserID() {
        return userID;
    }
}

