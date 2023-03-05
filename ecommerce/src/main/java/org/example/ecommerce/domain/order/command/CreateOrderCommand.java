package org.example.ecommerce.domain.order.command;

import org.example.ecommerce.generic.Command;
import org.example.ecommerce.generic.DomainEvent;

import java.time.LocalDate;

public class CreateOrderCommand extends Command {

    private String orderID;
    private LocalDate createDate;
    private Double total;

    public CreateOrderCommand(String orderID, Double total) {
        this.orderID = orderID;
        this.createDate = createDate;
        this.total = total;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

}

