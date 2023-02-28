package org.example.ecommerce.domain.order.values;

import org.example.ecommerce.generic.ValueObject;

import java.util.Objects;

public class User implements ValueObject<String> {

    private final String value;

    public User(String value) {
        this.value = Objects.requireNonNull(value);
        if (this.value.isEmpty()){ throw new IllegalArgumentException("User is not valid");}
    }

    @Override
    public String value() {
        return value;
    }
}
