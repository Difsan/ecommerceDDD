package org.example.ecommerce.domain.delivery.command;

import org.example.ecommerce.generic.Command;
import org.example.ecommerce.generic.DomainEvent;

public class AddDeliverymanCommand extends Command {

    private String deliveryID;

    private String companyID;
    private String deliverymanID;
    private String name;
    private String phone;

    public AddDeliverymanCommand(String deliveryID, String companyID, String deliverymanID, String name, String phone) {
        this.deliveryID = deliveryID;
        this.companyID = companyID;
        this.deliverymanID = deliverymanID;
        this.name = name;
        this.phone = phone;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
