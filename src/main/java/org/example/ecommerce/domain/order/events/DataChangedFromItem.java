package org.example.ecommerce.domain.order.events;

import org.example.ecommerce.domain.order.values.Data;
import org.example.ecommerce.domain.order.values.ItemID;
import org.example.ecommerce.generic.DomainEvent;

public class DataChangedFromItem extends DomainEvent {
    private final ItemID itemID;
    private final Data data;

    public DataChangedFromItem(ItemID itemID, Data data) {
        super("org.example.dataChangedFromItem");
        this.itemID = itemID;
        this.data = data;
    }

    public ItemID getItemID() {
        return itemID;
    }

    public Data getData() {
        return data;
    }
}
