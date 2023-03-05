package org.example.ecommerce.domain.order.values;

import org.example.ecommerce.domain.product.values.ProductID;
import org.example.ecommerce.generic.Identity;

public class OrderID extends Identity {

    public OrderID(String orderID){
        super(orderID);
    }

    public OrderID() {

    }

    public static  OrderID of(String  orderID) {
        return new  OrderID(orderID);
    }
}
