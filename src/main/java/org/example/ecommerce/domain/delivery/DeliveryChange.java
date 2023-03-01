package org.example.ecommerce.domain.delivery;

import org.example.ecommerce.domain.delivery.events.*;
import org.example.ecommerce.domain.order.events.ItemAdded;
import org.example.ecommerce.generic.EventChange;

import java.util.ArrayList;

public class DeliveryChange extends EventChange {
    public DeliveryChange(Delivery delivery){
        apply((DeliveryCreated event)->{
            delivery.dates = event.getDates();
            delivery.ordersIDS = new ArrayList<>();
        });
        apply((OrderAdded event) -> {
            delivery.ordersIDS.add(event.getOrder());
        });
        apply((CompanyCreated event) -> {
            delivery.company = new Company(event.getCompanyID(), event.getData());
        });
        apply((DeliverymanAdded event) -> {
            delivery.company.addADeliveryman(event.getId(), event.getPersonalInfo());
        });
        apply((DataChangedFromCompany event) -> {
            delivery.company.changeData(event.getData());
        } );
        apply((PersonalInfoChangedFromDeliveryman event) -> {
            delivery.company.deliverymen().stream().forEach(deliveryman -> {
                if (deliveryman.deliverymanID().equals(event.getId())) deliveryman.changePersonalInfo(event.getPersonalInfo());
            });
        });
    }
}
