package org.example.ecommerce.domain.order.events;

import org.example.ecommerce.domain.order.values.Total;
import org.example.ecommerce.generic.DomainEvent;

public class TotalChangedFromOrder extends DomainEvent {


    private final Double newTotal;

    public TotalChangedFromOrder(Double newTotal) {
        super("org.example.totalChangedFromOrder");
        this.newTotal = newTotal;
    }

    public Double getNewTotal() {
        return newTotal;
    }
}
