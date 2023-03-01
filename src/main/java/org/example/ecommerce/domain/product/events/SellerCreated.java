package org.example.ecommerce.domain.product.events;

import org.example.ecommerce.domain.product.values.Data;
import org.example.ecommerce.domain.product.values.SellerID;
import org.example.ecommerce.generic.DomainEvent;

public class SellerCreated extends DomainEvent {
    private final SellerID sellerID;
    private final Data data;

    public SellerCreated(SellerID sellerID, Data data) {
        super("org.example.sellerCreated");
        this.sellerID = sellerID;
        this.data = data;
    }

    public SellerID getSellerID() {
        return sellerID;
    }

    public Data getData() {
        return data;
    }
}
