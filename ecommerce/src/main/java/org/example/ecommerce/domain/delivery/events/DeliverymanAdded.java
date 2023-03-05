package org.example.ecommerce.domain.delivery.events;

import org.example.ecommerce.generic.DomainEvent;

public class DeliverymanAdded extends DomainEvent {
    private final String deliverymanID;
    private final String name;
    private final String phone;

    public DeliverymanAdded(String deliverymanID, String name, String phone) {
        super("org.example.deliverymanAdded");
        this.deliverymanID = deliverymanID;
        this.name = name;
        this.phone = phone;
    }

    public String getDeliverymanID() {
        return deliverymanID;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }
}
