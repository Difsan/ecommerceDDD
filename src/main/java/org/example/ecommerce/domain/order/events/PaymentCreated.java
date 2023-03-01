package org.example.ecommerce.domain.order.events;

import org.example.ecommerce.domain.order.values.PaymentID;
import org.example.ecommerce.domain.order.values.Type;
import org.example.ecommerce.generic.DomainEvent;

public class PaymentCreated extends DomainEvent {
    private final PaymentID paymentID;
    private Type type;

    public PaymentCreated(PaymentID paymentID, Type type) {
        super("org.example.paymentCreated");
        this.paymentID = paymentID;
        this.type = type;
    }

    public PaymentID getPaymentID() {
        return paymentID;
    }

    public Type getType() {
        return type;
    }
}
