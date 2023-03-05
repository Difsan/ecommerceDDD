package org.example.ecommerce.domain.product;

import org.example.ecommerce.domain.product.values.*;
import org.example.ecommerce.generic.Entity;

import java.util.Objects;

public class Seller extends Entity<SellerID> {

    private Name name;
    private Nit nit;
    private Email email;
    private Description description;

    public Seller(SellerID sellerID, Name name, Nit nit, Email email, Description description) {
        super(sellerID);
        this.name = Objects.requireNonNull(name);
        this.nit = Objects.requireNonNull(nit);
        this.email = Objects.requireNonNull(email);
        this.description = Objects.requireNonNull(description);
    }

  public void changeEmail(Email email){
        this.email= email;
    }

    public Name Name() {
        return name;
    }

    public Nit Nit() {
        return nit;
    }

    public Email Email() {
        return email;
    }

    public Description Description() {
        return description;
    }
}
