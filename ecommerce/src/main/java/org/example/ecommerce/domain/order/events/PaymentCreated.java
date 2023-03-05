package org.example.ecommerce.domain.order.events;

import org.example.ecommerce.domain.order.values.PaymentID;
import org.example.ecommerce.domain.order.values.Type;
import org.example.ecommerce.generic.DomainEvent;

public class PaymentCreated extends DomainEvent {
    private final String paymentID;
    private final String type;

    public PaymentCreated(String paymentID, String type) {
        super("org.example.paymentCreated");
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
