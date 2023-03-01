package org.example.ecommerce.domain.product;

import org.example.ecommerce.domain.product.events.*;
import org.example.ecommerce.domain.product.values.Features;
import org.example.ecommerce.domain.product.values.Title;
import org.example.ecommerce.generic.EventChange;

public class ProductChange extends EventChange {
    public ProductChange(Product product){
        apply((ProductCreated event)->{
            product.features = event.getFeatures();
        });
        apply((SellerCreated event) -> {
            product.seller = new Seller(event.getSellerID(), event.getData());
        });
        apply((CategoryCreated event) -> {
            product.category = new Category(event.getCategoryID(), event.getTitle());
        });
        apply((DataChangedFromSeller event) -> {
            product.seller.changeData(event.getData());
        });
        apply((TitleChangedFromCategory event) ->{
            product.category.changeTitle(event.getTitle());
        });
    }


}
