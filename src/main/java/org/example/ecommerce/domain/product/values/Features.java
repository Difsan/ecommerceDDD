package org.example.ecommerce.domain.product.values;

import org.example.ecommerce.generic.ValueObject;

import java.util.Objects;

public class Features implements ValueObject<Features.Props> {

    private final String title;
    private final String brand;
    private final String description;
    private final Double unitPrice;
    private final Integer stock;

    public Features(String title, String brand, String description, Double unitPrice, Integer stock) {
        this.title = title;
        this.brand = brand;
        this.description = description;
        this.unitPrice = unitPrice;
        this.stock = Objects.requireNonNull(stock);
        if (this.stock <= 0) {
            throw new IllegalArgumentException("stock must be a positive number");
        }
    }

    @Override
    public Props value() {
        return new Props() {
            @Override
            public String title() {
                return title;
            }

            @Override
            public String brand() {
                return brand;
            }

            @Override
            public String description() {
                return description;
            }

            @Override
            public Double unitPrice() {
                return unitPrice;
            }

            @Override
            public Integer stock() {
                return stock;
            }
        };
    }

    interface Props{
        String title();
        String brand();
        String description();
        Double unitPrice();
        Integer stock();
    }
}
