package org.example.ecommerce.domain.order;

import org.example.ecommerce.domain.order.values.ItemID;
import org.example.ecommerce.domain.order.values.Product;
import org.example.ecommerce.domain.order.values.Quantity;
import org.example.ecommerce.domain.order.values.SubTotal;
import org.example.ecommerce.generic.Entity;

import java.util.Objects;

public class Item extends Entity<ItemID> {

    private ItemID id;
    private Product productID;
    private Quantity quantity;

    private SubTotal subTotal;

    public Item(ItemID id, Product productID, Quantity quantity, SubTotal subTotal) {
        super(id);
        this.productID= Objects.requireNonNull(productID);
        this.quantity= quantity;
        this.subTotal = subTotal;
    }

    public void changeQuantity (Quantity quantity){ this.quantity = quantity; }

    public Quantity quantity(){ return quantity; }

    public SubTotal subTotal(){ return subTotal; }

    public Product productID(){return productID; }

   public ItemID itemID(){return id; }


}
