package org.example.ecommerce.domain.order.command;

import org.example.ecommerce.generic.Command;
import org.example.ecommerce.generic.DomainEvent;

public class RemoveItemFromOrderCommand extends Command {

    private String orderID;
    private String productID;
    private String itemID;

    public RemoveItemFromOrderCommand(String orderID, String productID, String itemID) {
        this.orderID = orderID;
        this.productID = productID;
        this.itemID = itemID;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
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
