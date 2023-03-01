package org.example.ecommerce.domain.delivery;

import org.example.ecommerce.domain.delivery.values.CompanyID;
import org.example.ecommerce.domain.delivery.values.Data;
import org.example.ecommerce.domain.delivery.values.DeliverymanID;
import org.example.ecommerce.domain.delivery.values.PersonalInfo;
import org.example.ecommerce.generic.Entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Company extends Entity<CompanyID> {
    private Data data;
    private List<Deliveryman> deliverymen;

    public Company(CompanyID id, Data data) {
        super(id);
        this.data = data;
        this.deliverymen = new ArrayList<>();
    }

    public void changeData(Data data) { this.data = data; }

    public void addADeliveryman(DeliverymanID deliverymanID, PersonalInfo personalInfo){
        Objects.requireNonNull(deliverymanID);
        Objects.requireNonNull(personalInfo);
        Deliveryman deliveryman = new Deliveryman(deliverymanID, personalInfo);
        deliverymen.add(deliveryman);
    }

    public Data data(){return data; }

    public List<Deliveryman> deliverymen() { return deliverymen;}
}
