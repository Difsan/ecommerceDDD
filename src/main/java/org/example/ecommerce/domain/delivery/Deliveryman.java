package org.example.ecommerce.domain.delivery;

import org.example.ecommerce.domain.delivery.values.*;
import org.example.ecommerce.generic.Entity;

import java.util.List;
import java.util.Objects;

public class Deliveryman extends Entity<DeliverymanID> {

    private DeliverymanID deliverymanID;

    private Name name;

    private Phone phone;

    public Deliveryman(DeliverymanID deliverymanID, Name name, Phone phone) {
        super(deliverymanID);
        this.name = Objects.requireNonNull(name);
        this.phone = Objects.requireNonNull(phone);
        this.deliverymanID = deliverymanID;
    }

    public void changePhone (Phone phone){ this.phone = phone; }

    public DeliverymanID deliverymanID() { return deliverymanID; }
    public Name name() { return name; }
    public Phone phone() { return phone; }

}
