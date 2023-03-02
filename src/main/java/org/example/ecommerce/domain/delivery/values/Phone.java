package org.example.ecommerce.domain.delivery.values;

import org.example.ecommerce.generic.ValueObject;

import java.util.Objects;

public class Phone implements ValueObject<String> {
    private final String value;

    public Phone(String value) {
        this.value = Objects.requireNonNull(value);
        if (this.value.isEmpty()){ throw new IllegalArgumentException("Phone is not valid");}
    }

    @Override
    public String value() {
        return value;
    }
}

