package org.example.ecommerce.domain.delivery.values;

import org.example.ecommerce.generic.ValueObject;

import java.time.LocalDate;
import java.util.Objects;

public class DeliveredDate implements ValueObject<LocalDate> {

    private final LocalDate value;
    private CreateDate createDate;

    public DeliveredDate(LocalDate value) {
        this.value = Objects.requireNonNull(value);
        /*if (this.value.isBefore(createDate.value())) {
            throw new IllegalArgumentException("deliveredDate is not valid");
        }*/
    }

    @Override
    public LocalDate value() {
        return value;
    }
}
