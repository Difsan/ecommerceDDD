package org.example.ecommerce.domain.delivery.values;

import org.example.ecommerce.generic.ValueObject;

import java.util.Objects;

public class Name implements ValueObject<String> {
    private String value;

    public Name(String value) {
        this.value = Objects.requireNonNull(value);
        if (this.value.isEmpty()){throw new IllegalArgumentException("Name is not valid");}
    }

    @Override
    public String value() {
        return value;
    }
}
