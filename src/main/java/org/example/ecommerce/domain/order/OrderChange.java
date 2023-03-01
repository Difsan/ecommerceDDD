package org.example.ecommerce.domain.order;

import org.example.ecommerce.generic.EventChange;

public class OrderChange extends EventChange {
    public OrderChange(Order order){
        /*apply((OrderCreated event) -> {
            order.information = event.getInformation()
        });*/
    }
}
