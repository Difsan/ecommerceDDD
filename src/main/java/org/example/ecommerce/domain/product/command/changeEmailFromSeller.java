package org.example.ecommerce.domain.product.command;

import org.example.ecommerce.generic.Command;
import org.example.ecommerce.generic.DomainEvent;

public class changeEmailFromSeller extends Command {

    private String productID;
    private String sellerID;
    private String email;

    public changeEmailFromSeller(String productID, String sellerID, String email) {
        this.productID = productID;
        this.sellerID = sellerID;
        this.email = email;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getSellerID() {
        return sellerID;
    }

    public void setSellerID(String sellerID) {
        this.sellerID = sellerID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
