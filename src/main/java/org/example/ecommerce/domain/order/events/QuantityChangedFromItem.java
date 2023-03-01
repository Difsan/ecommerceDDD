package org.example.ecommerce.domain.order.events;

import org.example.ecommerce.domain.order.values.ItemID;
import org.example.ecommerce.generic.DomainEvent;

public class QuantityChangedFromItem extends DomainEvent {
    private final String itemID;
    private final Integer newQuantity;

    public QuantityChangedFromItem(String itemID, Integer newQuantity) {
        super("org.example.quantityChangedFromItem");
        this.itemID = itemID;
        this.newQuantity = newQuantity;
    }

    public String getItemID() {
        return itemID;
    }

    public Integer getNewQuantity() {
        return newQuantity;
    }
}
