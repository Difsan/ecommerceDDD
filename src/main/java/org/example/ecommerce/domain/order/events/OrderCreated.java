package org.example.ecommerce.domain.order.events;

import org.example.ecommerce.domain.order.values.User;
import org.example.ecommerce.generic.DomainEvent;

import java.time.LocalDate;

public class OrderCreated extends DomainEvent {

    private LocalDate createDate;

    private Double total;
    private String userID;

    public OrderCreated(LocalDate createDate, Double total, String userID) {
        super("org.example.orderCreated");
        this.createDate = createDate;
        this.total = total;
        this.userID = userID;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public Double getTotal() {
        return total;
    }

    public String getUserID() {
        return userID;
    }
}

