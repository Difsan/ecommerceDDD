package org.example.ecommerce.domain.delivery.values;

import org.example.ecommerce.generic.Identity;

public class CompanyID extends Identity {

    public CompanyID(String companyID){
        super(companyID);
    }

    public CompanyID() {

    }

    public static CompanyID of(String  companyID) {
        return new CompanyID(companyID);
    }
}
