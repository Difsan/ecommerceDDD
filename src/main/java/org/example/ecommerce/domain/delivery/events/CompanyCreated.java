package org.example.ecommerce.domain.delivery.events;

import org.example.ecommerce.domain.delivery.values.CompanyID;
import org.example.ecommerce.domain.delivery.values.Data;
import org.example.ecommerce.generic.DomainEvent;

public class CompanyCreated extends DomainEvent {
    private final CompanyID companyID;
    private final Data data;

    public CompanyCreated(CompanyID companyID, Data data) {
        super("org.example.companyCreated");
        this.companyID = companyID;
        this.data = data;
    }

    public CompanyID getCompanyID() {
        return companyID;
    }

    public Data getData() {
        return data;
    }
}
