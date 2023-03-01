package org.example.ecommerce.domain.order;

import org.example.ecommerce.domain.order.events.*;
import org.example.ecommerce.generic.EventChange;

import java.util.ArrayList;

public class OrderChange extends EventChange {
    public OrderChange(Order order){
        apply((OrderCreated event) -> {
            order.information = event.getInformation();
            order.userID = event.getUserID();
            order.items = new ArrayList<>();
        });
        apply((PaymentCreated event) ->{
            order.payment = new Payment(event.getPaymentID(), event.getType());
        });
        apply((TypeChangedFromPayment event) -> {
            order.payment.changeType(event.getType());
        });
        apply((ItemAdded event) ->{
            Item item = new Item(event.getId(), event.getProductID(), event.getData());
            order.items.add(item);
        });
        apply((DataChangedFromItem event) -> {
            order.items.stream().forEach(item ->{
                if (item.itemID().equals(event.getItemID())) item.changeData(event.getData());
            });
        });
    }
}
