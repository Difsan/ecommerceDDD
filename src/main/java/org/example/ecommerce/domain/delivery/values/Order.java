package org.example.ecommerce.domain.delivery.values;

import org.example.ecommerce.generic.ValueObject;

import java.util.Objects;

public class Order implements ValueObject<String> {

    private final String value;

    public Order(String value) {
        this.value = Objects.requireNonNull(value);
        if (this.value.isEmpty()){ throw new IllegalArgumentException("Order is not valid");}
    }

    @Override
    public String value() {
        return value;
    }
}
