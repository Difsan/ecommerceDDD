package org.example.ecommerce.domain.order.values;

import org.example.ecommerce.generic.ValueObject;

import java.util.Objects;

public class Data implements ValueObject<Data.Props> {

    private final Integer quantity;
    private final Double subtotal;

    public Data(Integer quantity, Double subtotal) {
        this.quantity = Objects.requireNonNull(quantity);
        if (this.quantity <= 0) {
            throw new IllegalArgumentException("quantity is not valid");
        }
        this.subtotal = Objects.requireNonNull(subtotal);
        if (this.subtotal <= 0) {
            throw new IllegalArgumentException("subtotal is not valid");
        }
    }

    @Override
    public Props value() {
        return new Props() {
            @Override
            public Integer quantity() {
                return null;
            }

            @Override
            public Double subtotal() {
                return null;
            }
        };
    }

    interface Props{
        Integer quantity();
        Double subtotal();
    }
}
