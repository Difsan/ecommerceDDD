package org.example.ecommerce.domain.delivery.values;

import org.example.ecommerce.generic.ValueObject;

import java.time.LocalDate;
import java.util.Objects;

public class Dates implements ValueObject<Dates.Props> {

    private final LocalDate createDate;
    private final LocalDate deliveredDate;

    public Dates(LocalDate createDate, LocalDate total) {
        this.createDate = Objects.requireNonNull(createDate);
        this.deliveredDate = Objects.requireNonNull(total);
        if (this.deliveredDate.isBefore(this.createDate)) {
            throw new IllegalArgumentException("deliveredDate is not valid");
        }
    }

    @Override
    public Props value() {
        return new Props() {
            @Override
            public LocalDate createDate() {
                return null;
            }

            @Override
            public LocalDate deliveredDate() {
                return null;
            }
        };
    }

    interface Props{
        LocalDate createDate();
        LocalDate deliveredDate();
    }
}
