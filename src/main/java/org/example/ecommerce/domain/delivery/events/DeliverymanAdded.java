package org.example.ecommerce.domain.delivery.events;

import org.example.ecommerce.domain.delivery.values.DeliverymanID;
import org.example.ecommerce.domain.delivery.values.PersonalInfo;
import org.example.ecommerce.generic.DomainEvent;

public class DeliverymanAdded extends DomainEvent {
    private final DeliverymanID id;
    private final PersonalInfo personalInfo;

    public DeliverymanAdded(DeliverymanID id, PersonalInfo personalInfo) {
        super("org.example.deliverymanAdded");
        this.id = id;
        this.personalInfo = personalInfo;
    }

    public DeliverymanID getId() {
        return id;
    }

    public PersonalInfo getPersonalInfo() {
        return personalInfo;
    }
}
