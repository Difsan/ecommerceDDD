package org.example.ecommerce.domain.product;

import org.example.ecommerce.domain.product.events.ProductCreated;
import org.example.ecommerce.domain.product.values.*;
import org.example.ecommerce.generic.AggregateRoot;
import org.example.ecommerce.generic.DomainEvent;

import java.util.List;
import java.util.Set;

public class Product extends AggregateRoot<ProductID> {
    protected Features features;
    protected Seller seller;
    protected Category category;

    public Product(ProductID productID, Features features,
                   Seller seller, Category category) {
        super(productID);
        subscribe(new ProductChange(this));
        appendChange(new ProductCreated(features, seller, category)).apply();
    }
    public Product(ProductID productID) {
        super(productID);
        subscribe(new ProductChange(this));
    }

    public static Product from(ProductID id, List<DomainEvent> events){
        Product product = new Product(id);
        events.forEach(event -> product.applyEvent(event));
        return product;
    }


}
