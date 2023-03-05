package org.example.ecommerce.domain.product.events;

import org.example.ecommerce.domain.product.values.SellerID;
import org.example.ecommerce.generic.DomainEvent;

public class SellerCreated extends DomainEvent {
    private final String sellerID;

    private String sellerName;
    private String sellerNit;
    private String sellerEmail;
    private String sellerDescription;

    public SellerCreated(String sellerID,
                         String sellerName, String sellerNit,
                         String sellerEmail, String sellerDescription) {
        super("org.example.sellerCreated");
        this.sellerID = sellerID;
        this.sellerName = sellerName;
        this.sellerNit = sellerNit;
        this.sellerEmail = sellerEmail;
        this.sellerDescription = sellerDescription;
    }

    public String getSellerID() {
        return sellerID;
    }

    public String getSellerName() {
        return sellerName;
    }

    public String getSellerNit() {
        return sellerNit;
    }

    public String getSellerEmail() {
        return sellerEmail;
    }

    public String getSellerDescription() {
        return sellerDescription;
    }
}
