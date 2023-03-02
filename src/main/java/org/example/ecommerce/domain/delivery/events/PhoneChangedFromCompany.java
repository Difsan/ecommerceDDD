package org.example.ecommerce.domain.delivery.events;

import org.example.ecommerce.domain.delivery.values.CompanyID;
import org.example.ecommerce.generic.DomainEvent;

public class PhoneChangedFromCompany extends DomainEvent {
    private final String companyID;
    private  final String phone;

    public PhoneChangedFromCompany( String companyID, String phone) {
        super("org.example.phoneChangedFromCompany");
        this.companyID = companyID;
        this.phone = phone;
    }

    public String getCompanyID() {
        return companyID;
    }

    public String getPhone() {
        return phone;
    }
}
