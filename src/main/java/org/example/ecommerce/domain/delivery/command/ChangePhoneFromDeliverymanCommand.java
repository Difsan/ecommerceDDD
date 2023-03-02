package org.example.ecommerce.domain.delivery.command;

import org.example.ecommerce.generic.Command;
import org.example.ecommerce.generic.DomainEvent;

public class ChangePhoneFromDeliverymanCommand extends Command {

    private String deliveryID;
    private String companyID;
    private String deliverymanID;
    private String phone;

    public ChangePhoneFromDeliverymanCommand(String deliveryID, String companyID, String deliverymanID, String phone) {
        this.deliveryID = deliveryID;
        this.companyID = companyID;
        this.deliverymanID = deliverymanID;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
