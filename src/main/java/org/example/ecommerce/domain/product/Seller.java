package org.example.ecommerce.domain.product;

import org.example.ecommerce.domain.product.values.Data;
import org.example.ecommerce.domain.product.values.SellerID;
import org.example.ecommerce.domain.product.values.Title;
import org.example.ecommerce.generic.Entity;

public class Seller extends Entity<SellerID> {
    private Data data;

    public Seller(SellerID sellerID, Data data) {
        super(sellerID);
        this.data = data;
    }

    public void changeData(Data data){
        this.data= data;
    }
    public Data data(){
        return data;
    }
}
