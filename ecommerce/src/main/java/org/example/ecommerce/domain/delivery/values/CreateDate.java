package org.example.ecommerce.domain.delivery.values;

import org.example.ecommerce.generic.ValueObject;

import java.time.LocalDate;
import java.util.Objects;

public class CreateDate implements ValueObject<LocalDate> {
    private LocalDate value;

    public CreateDate(LocalDate value) {
        this.value = Objects.requireNonNull(value);
    }

    @Override
    public LocalDate value() {
        return value;
    }
}
