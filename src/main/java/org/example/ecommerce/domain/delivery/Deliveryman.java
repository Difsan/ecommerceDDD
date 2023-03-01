package org.example.ecommerce.domain.delivery;

import org.example.ecommerce.domain.delivery.values.*;
import org.example.ecommerce.generic.Entity;

import java.util.List;
import java.util.Objects;

public class Deliveryman extends Entity<DeliverymanID> {

    private DeliverymanID id;
    private PersonalInfo personalInfo;

    public Deliveryman(DeliverymanID id, PersonalInfo personalInfo) {
        super(id);
        this.personalInfo = Objects.requireNonNull(personalInfo);
        this.id = id;
    }

    public void changePersonalInfo(PersonalInfo personalInfo){ this.personalInfo = personalInfo; }

    public PersonalInfo personalInfo(){ return personalInfo; }

    public DeliverymanID deliverymanID() { return id; }
}
