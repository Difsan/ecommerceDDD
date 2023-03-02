package org.example.ecommerce.domain.product.command;

import org.example.ecommerce.generic.Command;

public class CreateSellerCommand extends Command {

    private String productID;
    private String sellerID;

    private String sellerName;
    private String sellerNit;
    private String sellerEmail;
    private String sellerDescription;

    public CreateSellerCommand(String productID, String sellerID, String sellerName,
                               String sellerNit, String sellerEmail, String sellerDescription) {
        this.productID = productID;
        this.sellerID = sellerID;
        this.sellerName = sellerName;
        this.sellerNit = sellerNit;
        this.sellerEmail = sellerEmail;
        this.sellerDescription = sellerDescription;
    }

    public String getProductID() {
        return productID;
    }

    public String getSellerID() {
        return sellerID;
    }

    public void setSellerID(String sellerID) {
        this.sellerID = sellerID;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getSellerNit() {
        return sellerNit;
    }

    public void setSellerNit(String sellerNit) {
        this.sellerNit = sellerNit;
    }

    public String getSellerEmail() {
        return sellerEmail;
    }

    public void setSellerEmail(String sellerEmail) {
        this.sellerEmail = sellerEmail;
    }

    public String getSellerDescription() {
        return sellerDescription;
    }

    public void setSellerDescription(String sellerDescription) {
        this.sellerDescription = sellerDescription;
    }
}
