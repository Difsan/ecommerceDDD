package org.example.ecommerce.domain.order;

import org.example.ecommerce.domain.order.events.*;
import org.example.ecommerce.domain.order.values.*;
import org.example.ecommerce.domain.product.values.ProductID;
import org.example.ecommerce.generic.AggregateRoot;
import org.example.ecommerce.generic.DomainEvent;

import java.util.List;
import java.util.Objects;

public class Order extends AggregateRoot<OrderID> {

    protected CreateDate createDate;

    protected Total total;
    protected User userID;
    protected Payment payment;
    protected List<Item> items;

    public Order(OrderID orderID, CreateDate createDate, Total total, User userID) {
        super(orderID);
        subscribe(new OrderChange(this));
        appendChange(new OrderCreated(createDate.value(), total.value(), userID.value())).apply();
    }

    public Order(OrderID id) {
        super(id);
        subscribe(new OrderChange(this));
    }

    public static Order from(OrderID id, List<DomainEvent> events){
        Order order = new Order(id);
        events.forEach(event -> order.applyEvent(event));
        return order;
    }

    public void changeTotalFromOrder(Total newTotal){
        appendChange(new TotalChangedFromOrder(newTotal.value())).apply();
    }
    public void createPayment(PaymentID paymentID, Type type){

        appendChange(new PaymentCreated(paymentID.value(), type.value())).apply();
    }

    public void changeTypeFromPayment(PaymentID paymentID, Type type){
        appendChange(new TypeChangedFromPayment(paymentID.value(), type.value())).apply();
    }

    public void addAnItem(ItemID itemID, Product productID, Quantity quantity, SubTotal subTotal){
        Objects.requireNonNull(itemID);
        Objects.requireNonNull(productID);
        Objects.requireNonNull(quantity);
        Objects.requireNonNull(subTotal);
        appendChange(new ItemAdded(itemID.value(), productID.value(),
                quantity.value(), subTotal.value())).apply();
    }

    public void removeItemFromOrder(ProductID productID, ItemID itemID){
        Objects.requireNonNull(productID);
        Objects.requireNonNull(itemID);
        appendChange(new ItemRemovedFromOrder(productID.value(), itemID.value())).apply();
    }

    public void changeQuantityFromItem(ItemID itemID, Quantity newQuantity){
        appendChange(new QuantityChangedFromItem(itemID.value(), newQuantity.value())).apply();
    }
}
