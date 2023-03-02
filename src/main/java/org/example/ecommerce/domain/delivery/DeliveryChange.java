package org.example.ecommerce.domain.delivery;

import org.example.ecommerce.domain.delivery.events.*;
import org.example.ecommerce.domain.delivery.values.*;
import org.example.ecommerce.generic.EventChange;

import java.util.ArrayList;

public class DeliveryChange extends EventChange {
    public DeliveryChange(Delivery delivery){
        apply((DeliveryCreated event)->{
            delivery.createDate = new CreateDate(event.getCreateDate());
            delivery.deliveredDate = new DeliveredDate(event.getDeliveredDate());
            delivery.ordersIDS = new ArrayList<>();
        });
        apply((OrderAdded event) -> {
                delivery.ordersIDS.add(new Order(event.getOrder()));
        });
        apply((CompanyCreated event) -> {
            delivery.company = new Company(CompanyID.of(event.getCompanyID()), new Name(event.getName()),
                    new Phone(event.getPhone()));
        });
        apply((DeliverymanAdded event) -> {
            delivery.company.addADeliveryman(DeliverymanID.of(event.getDeliverymanID()),
                    new Name(event.getName()), new Phone(event.getPhone()));
        });
        apply((PhoneChangedFromCompany event) -> {
            delivery.company.changePhone(new Phone(event.getPhone()));
        } );
        apply((PhoneChangedFromDeliveryman event) -> {
            delivery.company.deliverymen().stream().forEach(deliveryman -> {
                if (deliveryman.deliverymanID().value().equals(event.getDeliverymanID())) {
                    deliveryman.changePhone(new Phone(event.getPhone()));
                }
            });
        });
    }
}
