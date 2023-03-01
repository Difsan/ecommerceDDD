package org.example.ecommerce.domain.delivery.events;

import org.example.ecommerce.domain.delivery.values.Dates;
import org.example.ecommerce.generic.DomainEvent;

public class DeliveryCreated extends DomainEvent {

    private Dates dates;
    public DeliveryCreated(Dates dates) {
        super("org.example.deliveryCreated");
        this.dates = dates;
    }

    public Dates getDates() {
        return dates;
    }
}
