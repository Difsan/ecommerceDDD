package org.example.ecommerce.domain.delivery;

import org.example.ecommerce.domain.delivery.command.RemoveDeliverymanFromCompanyCommand;
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
            if (delivery.ordersIDS==null) {
                delivery.ordersIDS = new ArrayList<>();
            }
            delivery.ordersIDS.add(new Order(event.getOrder()));
        });
        apply((OrderRemoved event) -> {
            delivery.ordersIDS.removeIf(order -> order.value().equals(event.getOrder()));
        });
        apply((CompanyCreated event) -> {
            delivery.company = new Company(CompanyID.of(event.getCompanyID()), new Name(event.getName()),
                    new Phone(event.getPhone()));
        });
        apply((DeliverymanAdded event) -> {
            if (delivery.company!=null){
                delivery.company.deliverymen().add(new Deliveryman(DeliverymanID.of(event.getDeliverymanID()),
                        new Name(event.getName()), new Phone(event.getPhone())));
            }
        });
        apply((PhoneChangedFromCompany event) -> {
            delivery.company.changePhone(new Phone(event.getPhone()));
        } );
        apply((PhoneChangedFromDeliveryman event) -> {
            var deliverymanUpdate = delivery.company.deliverymen().stream().filter(deliveryman
                            -> deliveryman.deliverymanID().value().equals(event.getDeliverymanID()))
                            .findFirst().orElseThrow();
            deliverymanUpdate.changePhone(new Phone(event.getPhone()));
        });
        apply((DeliverymanRemovedFromCompany event) ->
                delivery.company.deliverymen().removeIf(deliveryman -> deliveryman.deliverymanID().value().equals(event.getDeliverymanID())));
    }
}
