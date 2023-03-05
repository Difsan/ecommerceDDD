package org.example.ecommerce.domain.product.values;

import org.example.ecommerce.generic.ValueObject;

import java.util.Objects;

public class UnitPrice implements ValueObject<Double> {
    private final Double value;

    public UnitPrice(Double value) {
        this.value = Objects.requireNonNull(value);
        if (this.value <= 0){ throw new IllegalArgumentException("UnitPrice has to be bigger than 0");}
    }

    @Override
    public Double value() {
        return value;
    }
}
