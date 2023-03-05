package org.example.ecommerce.domain.product;

import org.example.ecommerce.domain.product.events.*;
import org.example.ecommerce.domain.product.values.*;
import org.example.ecommerce.generic.EventChange;

public class ProductChange extends EventChange {
    public ProductChange(Product product){
        apply((ProductCreated event)->{
            product.tile = new Title(event.getProductTitle());
            product.brand = new Brand(event.getProductBrand());
            product.description = new Description(event.getProductDescription());
            product.unitPrice = new UnitPrice(event.getProductUnitPrice());
            product.stock = new Stock(event.getProductStock());
        });
        apply((SellerCreated event) -> {
            product.seller = new Seller(SellerID.of(event.getSellerID()),
                    new Name(event.getSellerName()), new Nit(event.getSellerNit()),
                    new Email(event.getSellerEmail()), new Description(event.getSellerDescription()));
        });
        apply((CategoryCreated event) -> {
            product.category = new Category(CategoryID.of(event.getCategoryID()), new Title(event.getCategoryTitle()));
        });
        apply((StockChangedFromProduct event) -> {
            product.stock = new Stock(event.getNewStock());
        });
        apply((EmailChangedFromSeller event) -> {
            product.seller.changeEmail(new Email(event.getEmail()));
        });
        apply((TitleChangedFromCategory event) ->{
            product.category.changeTitle(new Title(event.getNewTitle()));
        });
    }


}
