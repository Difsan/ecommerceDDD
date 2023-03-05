package org.example.ecommerce.domain.order.command;

import org.example.ecommerce.generic.Command;
import org.example.ecommerce.generic.DomainEvent;

public class AddItemCommand extends Command {

    private String orderID;
    private String itemID;
    private String productID;
    private Integer quantity;

    private Double subTotal;

    public AddItemCommand(String orderID, String itemID, String productID, Integer quantity, Double subTotal) {
        this.orderID = orderID;
        this.itemID = itemID;
        this.productID = productID;
        this.quantity = quantity;
        this.subTotal = subTotal;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(Double subTotal) {
        this.subTotal = subTotal;
    }
}
