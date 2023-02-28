package org.example.ecommerce.domain.delivery.values;

import org.example.ecommerce.generic.Identity;

public class DeliveryID extends Identity {

    public DeliveryID(String deliveryID){
        super(deliveryID);
    }

    public DeliveryID() {

    }

    public static DeliveryID of(String  deliveryID) {
        return new DeliveryID(deliveryID);
    }
}
