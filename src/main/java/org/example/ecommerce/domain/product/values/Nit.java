package org.example.ecommerce.domain.product.values;

import org.example.ecommerce.generic.ValueObject;

import java.util.Objects;

public class Nit implements ValueObject<String> {
    private final String value;

    public Nit(String value) {
        this.value = Objects.requireNonNull(value);
        if (this.value.isEmpty()){ throw new IllegalArgumentException("Nit is not valid");}
    }

    @Override
    public String value() {
        return value;
    }
}
