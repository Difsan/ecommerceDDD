package org.example.ecommerce.domain.order;

import org.example.ecommerce.domain.order.values.PaymentID;
import org.example.ecommerce.domain.order.values.Type;
import org.example.ecommerce.generic.Entity;

import java.util.Objects;

public class Payment extends Entity<PaymentID> {
    private Type type;

    public Payment(PaymentID id, Type type) {

        super(id);
        this.type = Objects.requireNonNull(type);
    }

    public void changeType(Type type){ this.type = type; }

    public Type type(){return type; }
}
