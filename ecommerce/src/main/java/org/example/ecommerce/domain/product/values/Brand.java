package org.example.ecommerce.domain.product.values;

import org.example.ecommerce.generic.ValueObject;

import java.util.Objects;

public class Brand implements ValueObject<String> {
    private final String value;

    public Brand(String value) {
        this.value = Objects.requireNonNull(value);
        if (this.value.isEmpty()){ throw new IllegalArgumentException("Brand is not valid");}
    }

    @Override
    public String value() {
        return value;
    }
}
