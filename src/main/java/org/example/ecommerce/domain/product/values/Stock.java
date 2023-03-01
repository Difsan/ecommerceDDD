package org.example.ecommerce.domain.product.values;

import org.example.ecommerce.generic.ValueObject;

import java.util.Objects;

public class Stock implements ValueObject<Integer> {
    private final Integer value;

    public Stock(Integer value) {
        this.value = Objects.requireNonNull(value);
        if (this.value < 0){ throw new IllegalArgumentException("Stock has to be a positive number");}
    }

    @Override
    public Integer value() {
        return value;
    }
}
