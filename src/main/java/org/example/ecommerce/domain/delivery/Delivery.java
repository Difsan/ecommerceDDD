package org.example.ecommerce.domain.delivery;

import org.example.ecommerce.domain.delivery.events.*;
import org.example.ecommerce.domain.delivery.values.*;
import org.example.ecommerce.generic.AggregateRoot;
import org.example.ecommerce.generic.DomainEvent;

import java.util.List;
import java.util.Objects;

public class Delivery extends AggregateRoot<DeliveryID> {

    protected CreateDate createDate;
    protected DeliveredDate deliveredDate;
    protected List<Order> ordersIDS;
    protected Company company;

    public Delivery(DeliveryID deliveryID, CreateDate createDate, DeliveredDate deliveredDate) {
        super(deliveryID);
        subscribe(new DeliveryChange(this));
        appendChange(new DeliveryCreated(deliveryID.value(), createDate.value(), deliveredDate.value())).apply();
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
        appendChange(new OrderAdded(order.value())).apply();
    }

    public void removeAnOrder(Order order){
        Objects.requireNonNull(order);
        appendChange(new OrderRemoved(order.value())).apply();
    }

    public void createCompany(CompanyID companyID, Name name, Phone phone){

        appendChange(new CompanyCreated(companyID.value(),name.value(), phone.value())).apply();
    }

    public void addADeliveryman(DeliverymanID deliverymanID, Name name, Phone phone){
        Objects.requireNonNull(deliverymanID);
        Objects.requireNonNull(name);
        Objects.requireNonNull(phone);
        appendChange(new DeliverymanAdded(deliverymanID.value(), name.value(), phone.value())).apply();
    }

    public void removeDeliverymanFromCompany(CompanyID companyID, DeliverymanID deliverymanID){
        Objects.requireNonNull(companyID);
        Objects.requireNonNull(deliverymanID);
        appendChange(new DeliverymanRemovedFromCompany(companyID.value(), deliverymanID.value()));
    }

    public void changePhoneFromCompany(CompanyID companyID, Phone newPhone){
        appendChange(new PhoneChangedFromCompany(companyID.value(), newPhone.value())).apply();
    }

    public void changePhoneFromDeliveryman(DeliverymanID deliverymanID, Phone newPhone){
        Objects.requireNonNull(deliverymanID);
        Objects.requireNonNull(newPhone);
        appendChange(new PhoneChangedFromDeliveryman(deliverymanID.value(), newPhone.value())).apply();
    }
}
