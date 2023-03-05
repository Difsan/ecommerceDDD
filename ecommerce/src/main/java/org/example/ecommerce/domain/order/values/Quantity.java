package org.example.ecommerce.domain.order.values;

import org.example.ecommerce.generic.ValueObject;

import java.util.Objects;

public class Quantity implements ValueObject<Integer> {
    private final Integer value;

    public Quantity(Integer value) {
        this.value = Objects.requireNonNull(value);
        if (this.value <= 0){ throw new IllegalArgumentException("Quantity has to be greater than 0");}
    }

    @Override
    public Integer value() {
        return value;
    }
}
