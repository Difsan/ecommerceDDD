package org.example.ecommerce.domain.product.events;

import org.example.ecommerce.domain.product.Category;
import org.example.ecommerce.domain.product.Seller;
import org.example.ecommerce.domain.product.values.Features;
import org.example.ecommerce.generic.DomainEvent;

public class ProductCreated extends DomainEvent {
    private Features features;

    public ProductCreated() {
        super("org.example.productCreated");
    }

    public ProductCreated(Features features) {
        super("org.example.productCreated");
        this.features = features;
    }

    public Features getFeatures() {
        return features;
    }

}
