package org.example.ecommerce.domain.product.events;

import org.example.ecommerce.domain.product.values.Data;
import org.example.ecommerce.domain.product.values.SellerID;
import org.example.ecommerce.generic.DomainEvent;

public class DataChangedFromSeller extends DomainEvent {
    private final SellerID sellerID;
    private final Data data;

    public DataChangedFromSeller(SellerID sellerID, Data data) {
        super("org.example.dataChangedFromSeller");
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
