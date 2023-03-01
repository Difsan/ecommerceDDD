package org.example.ecommerce.domain.order.command;

import org.example.ecommerce.generic.Command;
import org.example.ecommerce.generic.DomainEvent;

public class RemoveItemFromOrderCommand extends Command {
    private String productID;
    private String itemID;

    public RemoveItemFromOrderCommand(String productID, String itemID) {
        this.productID = productID;
        this.itemID = itemID;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }
}
