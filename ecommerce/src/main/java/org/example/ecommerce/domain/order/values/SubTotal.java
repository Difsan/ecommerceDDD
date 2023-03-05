package org.example.ecommerce.domain.order.values;

import org.example.ecommerce.generic.ValueObject;

import java.util.Objects;

public class SubTotal implements ValueObject<Double> {
    private Double value;

    public SubTotal(Double value) {
        this.value = Objects.requireNonNull(value);
        if (this.value <= 0){ throw new IllegalArgumentException("Subtotal has to be bigger than 0");}
    }

    @Override
    public Double value() {
        return value;
    }
}
