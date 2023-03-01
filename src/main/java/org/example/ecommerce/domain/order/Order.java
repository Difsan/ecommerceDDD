package org.example.ecommerce.domain.order;

import org.example.ecommerce.domain.order.values.Information;
import org.example.ecommerce.domain.order.values.OrderID;
import org.example.ecommerce.domain.order.values.User;
import org.example.ecommerce.generic.AggregateRoot;

import java.util.List;

public class Order extends AggregateRoot<OrderID> {

    protected Information information;
    protected User userID;
    protected Payment payment;
    protected List<Item> items;

    public Order(OrderID id, Information information, User userID,
                 Payment payment) {
        super(id);
        subscribe(new OrderChange(this));

    }
}
