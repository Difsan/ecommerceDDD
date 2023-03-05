package org.example.ecommerce.domain.order.command;

import org.example.ecommerce.generic.Command;
import org.example.ecommerce.generic.DomainEvent;

public class ChangeQuantityFromItemCommand extends Command {

    private String orderID;
    private String itemID;
    private Integer newQuantity;

    public ChangeQuantityFromItemCommand(String orderID, String itemID, Integer newQuantity) {
        this.orderID = orderID;
        this.itemID = itemID;
        this.newQuantity = newQuantity;
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

    public Integer getNewQuantity() {
        return newQuantity;
    }

    public void setNewQuantity(Integer newQuantity) {
        this.newQuantity = newQuantity;
    }
}
