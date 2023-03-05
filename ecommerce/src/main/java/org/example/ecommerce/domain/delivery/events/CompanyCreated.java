package org.example.ecommerce.domain.delivery.events;

import org.example.ecommerce.generic.DomainEvent;

public class CompanyCreated extends DomainEvent {
    private final String companyID;
    private final String name;
    private final String phone;

    public CompanyCreated(String companyID, String name, String phone) {
        super("org.example.companyCreated");
        this.companyID = companyID;
        this.name = name;
        this.phone = phone;
    }

    public String getCompanyID() {
        return companyID;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }
}
