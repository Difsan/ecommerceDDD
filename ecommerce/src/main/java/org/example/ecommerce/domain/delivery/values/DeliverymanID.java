package org.example.ecommerce.domain.delivery.values;

import org.example.ecommerce.generic.Identity;

public class DeliverymanID extends Identity {

    public DeliverymanID(String deliverymanID){
        super(deliverymanID);
    }

    public DeliverymanID() {

    }

    public static DeliverymanID of(String  deliverymanID) {
        return new DeliverymanID(deliverymanID);
    }
}
