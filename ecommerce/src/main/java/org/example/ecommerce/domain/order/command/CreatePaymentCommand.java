package org.example.ecommerce.domain.order.command;

import org.example.ecommerce.generic.Command;
import org.example.ecommerce.generic.DomainEvent;

public class CreatePaymentCommand extends Command {

    private String orderID;
    private  String paymentID;
    private String type;

    public CreatePaymentCommand(String orderID, String paymentID, String type) {
        this.orderID = orderID;
        this.paymentID = paymentID;
        this.type = type;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(String paymentID) {
        this.paymentID = paymentID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
