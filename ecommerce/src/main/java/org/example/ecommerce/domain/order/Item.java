package org.example.ecommerce.domain.order;

import org.example.ecommerce.domain.order.values.ItemID;
import org.example.ecommerce.domain.order.values.ProductID;
import org.example.ecommerce.domain.order.values.Quantity;
import org.example.ecommerce.domain.order.values.SubTotal;
import org.example.ecommerce.generic.Entity;

import java.util.Objects;

public class Item extends Entity<ItemID> {

    private ItemID id;
    private ProductID productID;
    private Quantity quantity;

    private SubTotal subTotal;

    public Item(ItemID id, ProductID productID, Quantity quantity, SubTotal subTotal) {
        super(id);
        this.productID= Objects.requireNonNull(productID);
        this.quantity= quantity; // depend of the unitprice of productID and here Product is just a VO of that  AR
        this.subTotal = subTotal;
    }

    public void changeQuantity (Quantity quantity){ this.quantity = quantity; }

    public Quantity quantity(){ return quantity; }

    public SubTotal subTotal(){ return subTotal; }

    public ProductID productID(){return productID; }

   public ItemID itemID(){return id; }


}
