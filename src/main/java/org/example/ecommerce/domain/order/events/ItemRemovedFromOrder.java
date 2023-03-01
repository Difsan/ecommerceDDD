package org.example.ecommerce.domain.order.events;

import org.example.ecommerce.domain.product.values.ProductID;
import org.example.ecommerce.generic.DomainEvent;

public class ItemRemovedFromOrder extends DomainEvent {
    private final String productID;
    private final String itemID;

    public ItemRemovedFromOrder( String productID, String itemID) {
        super("org.example.itemRemovedFromOrder");
        this.productID = productID;
        this.itemID = itemID;
    }

    public String getProductID() {
        return productID;
    }

    public String getItemID() {
        return itemID;
    }
}
