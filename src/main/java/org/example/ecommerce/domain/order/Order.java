package org.example.ecommerce.domain.order;

import org.example.ecommerce.domain.order.events.*;
import org.example.ecommerce.domain.order.values.*;
import org.example.ecommerce.domain.product.values.ProductID;
import org.example.ecommerce.generic.AggregateRoot;
import org.example.ecommerce.generic.DomainEvent;

import java.util.List;
import java.util.Objects;

public class Order extends AggregateRoot<OrderID> {

    protected Information information;
    protected User userID;
    protected Payment payment;
    protected List<Item> items;

    public Order(OrderID id, Information information, User userID) {
        super(id);
        subscribe(new OrderChange(this));
        appendChange(new OrderCreated(information, userID)).apply();
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

    public void createPayment(Type type){
        appendChange(new PaymentCreated(new PaymentID(), type)).apply();
    }

    public void changeTypeFromPayment(PaymentID paymentID, Type type){
        appendChange(new TypeChangedFromPayment(paymentID, type)).apply();
    }

    public void addAnItem(ItemID id, Product productID, Data data){
        Objects.requireNonNull(id);
        Objects.requireNonNull(productID);
        Objects.requireNonNull(data);
        appendChange(new ItemAdded(id, productID, data)).apply();
    }

    public void changeDataFromItem(ItemID itemID, Data data){
        appendChange(new DataChangedFromItem(itemID, data)).apply();
    }
}
