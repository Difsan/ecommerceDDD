package org.example.ecommerce.domain.product;

import org.example.ecommerce.domain.product.events.ProductCreated;
import org.example.ecommerce.domain.product.values.Features;
import org.example.ecommerce.domain.product.values.Title;
import org.example.ecommerce.generic.EventChange;

public class ProductChange extends EventChange {
    public ProductChange(Product product){
        apply((ProductCreated event)->{
            product.features = event.getFeatures();
            product.category = event.getCategory();
            product.seller = event.getSeller();
        });
    }


}
