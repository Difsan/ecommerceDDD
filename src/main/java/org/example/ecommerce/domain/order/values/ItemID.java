package org.example.ecommerce.domain.order.values;

import org.example.ecommerce.generic.Identity;

public class ItemID extends Identity {
    public ItemID(String itemID){
        super(itemID);
    }

    public ItemID() {

    }

    public static  ItemID of(String  itemID) {
        return new  ItemID(itemID);
    }
}
