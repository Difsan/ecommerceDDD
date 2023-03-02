package org.example.ecommerce.domain.order;

import org.example.ecommerce.domain.order.command.RemoveItemFromOrderCommand;
import org.example.ecommerce.domain.order.events.*;
import org.example.ecommerce.domain.order.values.*;
import org.example.ecommerce.generic.EventChange;

import java.util.ArrayList;
import java.util.List;

public class OrderChange extends EventChange {
    public OrderChange(Order order) {
        apply((OrderCreated event) -> {
            order.createDate = new CreateDate(event.getCreateDate());
            order.total = new Total(event.getTotal());
            order.userID = new User(event.getUserID());
            order.items = new ArrayList<>();
        });
        apply((TotalChangedFromOrder event) -> {
            order.total = new Total(event.getNewTotal());
        });
        apply((PaymentCreated event) -> {
            order.payment = new Payment(PaymentID.of(event.getPaymentID()),
                    new Type(event.getType()));
        });
        apply((TypeChangedFromPayment event) -> {
            order.payment.changeType(new Type(event.getType()));
        });
        apply((ItemAdded event) -> {
            Item item = new Item(ItemID.of(event.getItemID()), new Product(event.getProductID()),
                    new Quantity(event.getQuantity()), new SubTotal(event.getSubTotal()));
            order.items.add(item);
        });
        apply((QuantityChangedFromItem event) -> {
            var itemUpdate = order.items.stream().filter(item -> item.itemID().value().equals(event.getItemID()))
                            .findFirst().orElseThrow();
            itemUpdate.changeQuantity(new Quantity(event.getNewQuantity()));
                    // should I change the subtotal also?
            });
        apply((ItemRemovedFromOrder event) ->
                order.items.removeIf(item -> item.itemID().value().equals(event.getItemID())));

    }
}
