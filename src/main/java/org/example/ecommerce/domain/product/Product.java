package org.example.ecommerce.domain.product;

import org.example.ecommerce.domain.product.events.ProductCreated;
import org.example.ecommerce.domain.product.values.*;
import org.example.ecommerce.generic.AggregateRoot;

public class Product extends AggregateRoot<ProductID> {
    protected Features features;
    protected Seller seller;
    protected Category category;

    public Product(ProductID productID, Features features,
                   SellerID sellerID, Data data,
                   CategoryID categoryID, Title title) {
        super(productID);
        subscribe(new ProductChange(this));
        /*appendChange(new ProductCreated(features.value(),
                sellerID.value(), data.value(),categoryID.value(),
                title.value())).apply();*/
    }
}
