package org.example.ecommerce.domain.delivery;

import org.example.ecommerce.domain.delivery.events.*;
import org.example.ecommerce.domain.delivery.values.*;
import org.example.ecommerce.domain.order.values.OrderID;
import org.example.ecommerce.generic.AggregateRoot;
import org.example.ecommerce.generic.DomainEvent;

import java.util.List;
import java.util.Objects;

public class Delivery extends AggregateRoot<DeliveryID> {

    protected Dates dates;
    protected List<Order> ordersIDS;
    protected Company company;

    public Delivery(DeliveryID id,Dates dates) {
        super(id);
        subscribe(new DeliveryChange(this));
        appendChange(new DeliveryCreated(dates)).apply();
    }

    public Delivery(DeliveryID id) {
        super(id);
        subscribe(new DeliveryChange(this));
    }

    public static Delivery from(DeliveryID id, List<DomainEvent> events){
        Delivery delivery = new Delivery(id);
        events.forEach(event -> delivery.applyEvent(event));
        return delivery;
    }

    public void addAnOrder(Order order){
        Objects.requireNonNull(order);
        appendChange(new OrderAdded(order)).apply();
    }

    public void createCompany(Data data){
        appendChange(new CompanyCreated(new CompanyID(),data)).apply();
    }

    public void addADeliveryman(DeliverymanID id, PersonalInfo personalInfo){
        Objects.requireNonNull(id);
        Objects.requireNonNull(personalInfo);
        appendChange(new DeliverymanAdded(id, personalInfo)).apply();
    }

    public void changeDataFromCompany(CompanyID companyID, Data data){
        appendChange(new DataChangedFromCompany(companyID, data)).apply();
    }

    public void changePersonalInfoFromDeliveryman(DeliverymanID id, PersonalInfo personalInfo){
        Objects.requireNonNull(id);
        Objects.requireNonNull(personalInfo);
        appendChange(new PersonalInfoChangedFromDeliveryman(id, personalInfo)).apply();
    }
}
