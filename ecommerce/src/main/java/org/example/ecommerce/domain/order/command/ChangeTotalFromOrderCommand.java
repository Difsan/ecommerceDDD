package org.example.ecommerce.domain.order.command;

import org.example.ecommerce.generic.Command;
import org.example.ecommerce.generic.DomainEvent;

public class ChangeTotalFromOrderCommand extends Command {

    private String orderID;
    private  Double newTotal;

    public ChangeTotalFromOrderCommand(String orderID, Double newTotal) {
        this.orderID = orderID;
        this.newTotal = newTotal;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public Double getNewTotal() {
        return newTotal;
    }

    public void setNewTotal(Double newTotal) {
        this.newTotal = newTotal;
    }
}
