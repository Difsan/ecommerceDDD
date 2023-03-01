package org.example.ecommerce.domain.product;

import org.example.ecommerce.domain.product.events.*;
import org.example.ecommerce.domain.product.values.*;
import org.example.ecommerce.generic.AggregateRoot;
import org.example.ecommerce.generic.DomainEvent;

import java.util.List;

public class Product extends AggregateRoot<ProductID> {
    protected Features features;
    protected Seller seller;
    protected Category category;

    public Product(ProductID productID, Features features) {
        super(productID);
        subscribe(new ProductChange(this));
        appendChange(new ProductCreated(features)).apply();
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

    public void createSeller(Data data){
        appendChange(new SellerCreated(new SellerID(), data)).apply();
    }

    public void createCategory(Title title){
        appendChange(new CategoryCreated(new CategoryID(), title)).apply();
    }

    public void changeDataFromSeller(SellerID sellerID, Data data){
        appendChange(new DataChangedFromSeller(sellerID, data)).apply();
    }

    public void changeTitleFromCategory(CategoryID categoryID, Title title){
        appendChange(new TitleChangedFromCategory(categoryID, title)).apply();
    }


}
