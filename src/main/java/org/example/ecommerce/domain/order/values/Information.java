package org.example.ecommerce.domain.order.values;

import org.example.ecommerce.generic.ValueObject;

import java.time.LocalDate;
import java.util.Objects;

public class Information implements ValueObject<Information.Props> {

    private final LocalDate createDate;
    private final Double total;

    public Information(LocalDate createDate, Double total) {
        this.createDate = Objects.requireNonNull(createDate);
        this.total = Objects.requireNonNull(total);
        if (this.total <= 0) {
            throw new IllegalArgumentException("total is not valid");
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
            public Double total() {
                return null;
            }
        };
    }

    interface Props{
        LocalDate createDate();
        Double total();
    }
}
