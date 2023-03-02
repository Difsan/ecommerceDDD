package org.example.ecommerce.domain.delivery.events;

import org.example.ecommerce.generic.DomainEvent;

public class DeliverymanRemovedFromCompany extends DomainEvent {
    private final String companyID;
    private final String deliverymanID;

    public DeliverymanRemovedFromCompany(String companyID, String deliverymanID) {
        super("org.example.deliverymanRemovedFromCompany");
        this.companyID = companyID;
        this.deliverymanID = deliverymanID;
    }

    public String getCompanyID() {
        return companyID;
    }

    public String getDeliverymanID() {
        return deliverymanID;
    }
}
