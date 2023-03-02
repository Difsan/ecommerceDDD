package org.example.ecommerce.domain.order.values;

import org.example.ecommerce.generic.Identity;
import org.example.ecommerce.generic.ValueObject;

import java.util.Objects;

public class UserID extends Identity {

    public UserID(String orderID){
        super(orderID);
    }

    public UserID() {

    }

    public static  UserID of(String  userID) {
        return new  UserID(userID);
    }
}
