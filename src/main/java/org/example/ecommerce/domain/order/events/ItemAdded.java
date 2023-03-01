package org.example.ecommerce.domain.order.events;

import org.example.ecommerce.domain.order.values.Data;
import org.example.ecommerce.domain.order.values.ItemID;
import org.example.ecommerce.domain.order.values.Product;
import org.example.ecommerce.generic.DomainEvent;

public class ItemAdded extends DomainEvent {
    private final ItemID id;
    private final Product productID;
    private Data data;

    public ItemAdded(ItemID id, Product productID, Data data) {
        super("org.example.itemAdded");
        this.id = id;
        this.productID = productID;
        this.data = data;
    }

    public ItemID getId() {
        return id;
    }

    public Product getProductID() {
        return productID;
    }

    public Data getData() {
        return data;
    }
}
