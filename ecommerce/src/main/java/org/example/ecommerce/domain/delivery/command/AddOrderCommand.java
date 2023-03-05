package org.example.ecommerce.domain.delivery.command;

import org.example.ecommerce.generic.Command;
import org.example.ecommerce.generic.DomainEvent;

public class AddOrderCommand extends Command {
    private String deliveryID;
    private final String order;


    public AddOrderCommand(String deliveryID, String order) {
        this.deliveryID = deliveryID;
        this.order = order;
    }

    public String getDeliveryID() {
        return deliveryID;
    }

    public void setDeliveryID(String deliveryID) {
        this.deliveryID = deliveryID;
    }

    public String getOrder() {
        return order;
    }
}
