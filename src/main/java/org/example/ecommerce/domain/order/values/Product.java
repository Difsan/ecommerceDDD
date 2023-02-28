package org.example.ecommerce.domain.order.values;

import org.example.ecommerce.generic.ValueObject;

import java.util.Objects;

public class Product implements ValueObject<String> {

    private final String value;

    public Product(String value) {
        this.value = Objects.requireNonNull(value);
        if (this.value.isEmpty()){ throw new IllegalArgumentException("Product is not valid");}
    }

    @Override
    public String value() {
        return value;
    }
}
