package org.example.ecommerce.domain.delivery.events;

import org.example.ecommerce.generic.DomainEvent;

import java.time.LocalDate;

public class DeliveryCreated extends DomainEvent {

    private final String deliveryID;
    private final LocalDate createDate;
    private final LocalDate deliveredDate;

    public DeliveryCreated(String deliveryID, LocalDate createDate,
                           LocalDate deliveredDate) {
        super("org.example.deliveryCreated");
        this.deliveryID = deliveryID;
        this.createDate = createDate;
        this.deliveredDate = deliveredDate;
    }

    public String getDeliveryID() {
        return deliveryID;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public LocalDate getDeliveredDate() {
        return deliveredDate;
    }
}
