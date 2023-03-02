package org.example.ecommerce.domain.delivery;

import org.example.ecommerce.domain.delivery.values.CompanyID;
import org.example.ecommerce.domain.delivery.values.DeliverymanID;
import org.example.ecommerce.domain.delivery.values.Name;
import org.example.ecommerce.domain.delivery.values.Phone;
import org.example.ecommerce.generic.Entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Company extends Entity<CompanyID> {

    private Name name;

    private Phone phone;
    private List<Deliveryman> deliverymen;

    public Company(CompanyID companyID, Name name, Phone phone) {
        super(companyID);
        this.name = name;
        this.phone = phone;
        this.deliverymen = new ArrayList<>();
    }

    public void addADeliveryman(DeliverymanID deliverymanID, Name name, Phone phone){
        Objects.requireNonNull(deliverymanID);
        Objects.requireNonNull(name);
        Objects.requireNonNull(phone);
        Deliveryman deliveryman = new Deliveryman(deliverymanID, name, phone);
        deliverymen.add(deliveryman);
    }

    public void changePhone (Phone phone){ this.phone = phone; }

    public Name name() { return name; }
    public Phone phone() { return phone; }

    public List<Deliveryman> deliverymen() { return deliverymen;}
}
