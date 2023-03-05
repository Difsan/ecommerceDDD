package org.example.ecommerce.domain.product.events;

import org.example.ecommerce.domain.product.values.SellerID;
import org.example.ecommerce.generic.DomainEvent;

public class EmailChangedFromSeller extends DomainEvent {
    private final String sellerID;
    private final String email;

    public EmailChangedFromSeller(String sellerID, String email) {
        super("org.example.emailChangedFromSeller");
        this.sellerID = sellerID;
        this.email = email;
    }

    public String getSellerID() {
        return sellerID;
    }

    public String getEmail() {
        return email;
    }
}
