package org.example.ecommerce.domain.product.events;

import org.example.ecommerce.generic.DomainEvent;

public class StockChangedFromProduct extends DomainEvent {
    private final Integer newStock;


    public StockChangedFromProduct( Integer newStock) {
        super("org.example.stockChangedFromProduct");
        this.newStock = newStock;
    }

    public Integer getNewStock() {
        return newStock;
    }
}
