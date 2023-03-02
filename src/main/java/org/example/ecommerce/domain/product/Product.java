package org.example.ecommerce.domain.product;

import org.example.ecommerce.domain.product.events.*;
import org.example.ecommerce.domain.product.values.*;
import org.example.ecommerce.generic.AggregateRoot;
import org.example.ecommerce.generic.DomainEvent;

import java.util.List;

public class Product extends AggregateRoot<ProductID> {

    protected Title tile;
    protected Brand brand;
    protected Description description;
    protected UnitPrice unitPrice;
    protected Stock stock;
    protected Seller seller;
    protected Category category;

    public Product(ProductID productID,Title tile, Brand brand,
                   Description description, UnitPrice unitPrice,Stock stock) {
        super(productID);
        subscribe(new ProductChange(this));
        appendChange(new ProductCreated(tile.value(), brand.value(),
                description.value(), unitPrice.value(), stock.value())).apply();
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

    public void createSeller(SellerID sellerID, Name name, Nit nit, Email email, Description description){
        appendChange(new SellerCreated(sellerID.value(), name.value(), nit.value(), email.value(),
                description.value())).apply();
    }

    public void createCategory(CategoryID categoryID, Title title){
        appendChange(new CategoryCreated(categoryID.value(), title.value())).apply();
    }

    public void changeStockFromProduct( Stock newStock){
        appendChange(new StockChangedFromProduct(newStock.value())).apply();
    }

    public void changeEmailFromSeller(SellerID sellerID, Email newEmail){
        appendChange(new EmailChangedFromSeller(sellerID.value(), newEmail.value())).apply();
    }

    public void changeTitleFromCategory(CategoryID categoryID, Title newTitle){
        appendChange(new TitleChangedFromCategory(categoryID.value(), newTitle.value())).apply();
    }


}
