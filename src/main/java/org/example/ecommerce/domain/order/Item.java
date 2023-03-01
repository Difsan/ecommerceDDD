package org.example.ecommerce.domain.order;

import org.example.ecommerce.domain.order.values.Data;
import org.example.ecommerce.domain.order.values.ItemID;
import org.example.ecommerce.domain.order.values.Product;
import org.example.ecommerce.generic.Entity;

import java.util.Objects;

public class Item extends Entity<ItemID> {

    private ItemID id;
    private Product productID;
    private Data data;

    public Item(ItemID id, Product productID, Data data) {
        super(id);
        this.productID= Objects.requireNonNull(productID);
        this.data = data;
        this.id = id;
    }

    public void changeData(Data data){ this.data = data; }

    public Data data(){ return data; }

    public Product productID(){return productID; }

   public ItemID itemID(){return id; }


}
