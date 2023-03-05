package org.example.ecommerce.domain.product.values;

import org.example.ecommerce.generic.ValueObject;

import java.util.Objects;

public class Name implements ValueObject<String> {
    private final String value;

    public Name(String value) {
        this.value = Objects.requireNonNull(value);
        if (this.value.isEmpty()){ throw new IllegalArgumentException("Name is not valid");}
    }

    @Override
    public String value() {
        return value;
    }
}
