package org.example.ecommerce.domain.order.events;

import org.example.ecommerce.domain.order.values.PaymentID;
import org.example.ecommerce.domain.order.values.Type;
import org.example.ecommerce.generic.DomainEvent;

public class TypeChangedFromPayment extends DomainEvent {
    private final String paymentID;
    private final String type;


    public TypeChangedFromPayment(String paymentID, String type) {
        super("org.example.typeChangedFromPayment");
        this.paymentID = paymentID;
        this.type = type;
    }

    public String getPaymentID() {
        return paymentID;
    }

    public String getType() {
        return type;
    }
}
