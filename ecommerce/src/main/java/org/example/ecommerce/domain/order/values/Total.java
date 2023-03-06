package org.example.ecommerce.domain.order.values;

import org.example.ecommerce.generic.ValueObject;

import java.time.LocalDate;
import java.util.Objects;

public class Total implements ValueObject<Double> {
    private Double value;

    public Total(Double value) {
        this.value = Objects.requireNonNull(value);
        if (this.value < 0){ throw new IllegalArgumentException("Total has to be a positive number");}
    }

    @Override
    public Double value() {
        return value;
    }
}
