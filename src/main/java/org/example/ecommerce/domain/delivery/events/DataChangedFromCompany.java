package org.example.ecommerce.domain.delivery.events;

import org.example.ecommerce.domain.delivery.values.CompanyID;
import org.example.ecommerce.domain.delivery.values.Data;
import org.example.ecommerce.generic.DomainEvent;

public class DataChangedFromCompany extends DomainEvent {
    private final CompanyID companyID;
    private  final Data data;

    public DataChangedFromCompany(CompanyID companyID, Data data) {
        super("org.example.dataChangedFromCompany");
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
