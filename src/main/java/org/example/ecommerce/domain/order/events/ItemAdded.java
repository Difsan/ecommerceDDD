package org.example.ecommerce.domain.order.events;

import org.example.ecommerce.generic.DomainEvent;

public class ItemAdded extends DomainEvent {
    private final String itemID;
    private final String productID;
    private final Integer quantity;

    private final Double subTotal;

    public ItemAdded( String itemID,
                     String productID, Integer quantity, Double subTotal) {
        super("org.example.itemAdded");
        this.itemID = itemID;
        this.productID = productID;
        this.quantity = quantity;
        this.subTotal = subTotal;
    }

    public String getItemID() {
        return itemID;
    }

    public String getProductID() {
        return productID;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Double getSubTotal() {
        return subTotal;
    }
}
