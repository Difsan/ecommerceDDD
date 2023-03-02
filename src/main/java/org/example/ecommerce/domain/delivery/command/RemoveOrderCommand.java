package org.example.ecommerce.domain.delivery.command;

import org.example.ecommerce.generic.Command;

public class RemoveOrderCommand extends Command {
    private String deliveryID;
    private String order;


    public RemoveOrderCommand(String deliveryID, String order) {
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

    public void setOrder(String order) {
        this.order = order;
    }
}
