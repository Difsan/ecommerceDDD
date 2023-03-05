package org.example.ecommerce.domain.order.values;

import org.example.ecommerce.generic.Identity;

public class PaymentID extends Identity {
    public PaymentID(String paymentID){
        super(paymentID);
    }

    public PaymentID() {

    }

    public static  PaymentID of(String  paymentID) {
        return new  PaymentID(paymentID);
    }
}
