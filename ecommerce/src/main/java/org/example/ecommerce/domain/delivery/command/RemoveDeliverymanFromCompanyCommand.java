package org.example.ecommerce.domain.delivery.command;

import org.example.ecommerce.generic.Command;
import org.example.ecommerce.generic.DomainEvent;

public class RemoveDeliverymanFromCompanyCommand extends Command {
    private String deliveryID;
    private String companyID;
    private String deliverymanID;

    public RemoveDeliverymanFromCompanyCommand(String deliveryID, String companyID, String deliverymanID) {
        this.deliveryID = deliveryID;
        this.companyID = companyID;
        this.deliverymanID = deliverymanID;
    }

    public String getDeliveryID() {
        return deliveryID;
    }

    public void setDeliveryID(String deliveryID) {
        this.deliveryID = deliveryID;
    }

    public String getCompanyID() {
        return companyID;
    }

    public void setCompanyID(String companyID) {
        this.companyID = companyID;
    }

    public String getDeliverymanID() {
        return deliverymanID;
    }

    public void setDeliverymanID(String deliverymanID) {
        this.deliverymanID = deliverymanID;
    }
}
