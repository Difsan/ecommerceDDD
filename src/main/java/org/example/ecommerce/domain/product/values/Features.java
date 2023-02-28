package org.example.ecommerce.domain.product.values;

import org.example.ecommerce.generic.ValueObject;

public class Features implements ValueObject<Features.Props> {

    private final String title;
    private final String brand;
    private final String description;
    private final double unitPrice;
    private final int stock;

    public Features(String title, String brand, String description, double unitPrice, int stock) {
        this.title = title;
        this.brand = brand;
        this.description = description;
        this.unitPrice = unitPrice;
        this.stock = stock;
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
            public double unitPrice() {
                return unitPrice;
            }

            @Override
            public int stock() {
                return stock;
            }
        };
    }

    interface Props{
        String title();
        String brand();
        String description();
        double unitPrice();
        int stock();
    }
}
