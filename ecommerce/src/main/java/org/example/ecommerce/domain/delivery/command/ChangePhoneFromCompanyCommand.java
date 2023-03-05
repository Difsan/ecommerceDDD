package org.example.ecommerce.domain.delivery.command;

import org.example.ecommerce.generic.Command;
import org.example.ecommerce.generic.DomainEvent;

public class ChangePhoneFromCompanyCommand extends Command {
    private String deliveryID;
    private String companyID;
    private String phone;

    public ChangePhoneFromCompanyCommand(String deliveryID, String companyID, String phone) {
        this.deliveryID = deliveryID;
        this.companyID = companyID;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
