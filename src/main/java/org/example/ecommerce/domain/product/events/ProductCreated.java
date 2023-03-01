package org.example.ecommerce.domain.product.events;

import org.example.ecommerce.generic.DomainEvent;

public class ProductCreated extends DomainEvent {
    /*private Features features;

    public ProductCreated() {
        super("org.example.productCreated");
    }

    public ProductCreated(Features features) {
        super("org.example.productCreated");
        this.features = features;
    }

    public Features getFeatures() {
        return features;
    }*/

    private final String productTitle;
    private final String productBrand;
    private final String productDescription;
    private final Double productUnitPrice;
    private final Integer productStock;

    public ProductCreated(String productTitle, String productBrand,
                          String productDescription,
                          Double productUnitPrice, Integer productStock) {
        super("org.example.productCreated");
        this.productTitle = productTitle;
        this.productBrand = productBrand;
        this.productDescription = productDescription;
        this.productUnitPrice = productUnitPrice;
        this.productStock = productStock;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public String getProductBrand() {
        return productBrand;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public Double getProductUnitPrice() {
        return productUnitPrice;
    }

    public Integer getProductStock() {
        return productStock;
    }
}
