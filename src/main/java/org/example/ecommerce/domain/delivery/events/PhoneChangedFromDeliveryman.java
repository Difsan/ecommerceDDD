package org.example.ecommerce.domain.delivery.events;

import org.example.ecommerce.generic.DomainEvent;

public class PhoneChangedFromDeliveryman extends DomainEvent {
    private final String deliverymanID;
    private final String phone;

    public PhoneChangedFromDeliveryman(String deliverymanID, String phone) {
        super("org.example.phoneInfoChangedFromDeliveryman");
        this.deliverymanID = deliverymanID;
        this.phone = phone;
    }


    public String getDeliverymanID() {
        return deliverymanID;
    }

    public String getPhone() {
        return phone;
    }
}
