package org.example.ecommerce.domain.product.values;

import org.example.ecommerce.generic.Identity;

public class SellerID extends Identity {
    public SellerID(String sellerID){
        super(sellerID);
    }

    public SellerID(){

    }

    public static SellerID of(String sellerID){
        return new SellerID(sellerID);
    }
}
