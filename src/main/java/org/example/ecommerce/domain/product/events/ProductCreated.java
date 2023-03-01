package org.example.ecommerce.domain.product.events;

import org.example.ecommerce.domain.product.Category;
import org.example.ecommerce.domain.product.Seller;
import org.example.ecommerce.domain.product.values.Features;
import org.example.ecommerce.generic.DomainEvent;

public class ProductCreated extends DomainEvent {
    private Features features;
    private Seller seller;
    private Category category;


    public ProductCreated() {
        super("org.example.productCreated");
    }

    public ProductCreated(Features features, Seller seller, Category category) {
        super("org.example.productCreated");
        this.features = features;
        this.seller = seller;
        this.category = category;
    }

    public Features getFeatures() {
        return features;
    }

    public Seller getSeller() {
        return seller;
    }

    public Category getCategory() {
        return category;
    }
}
