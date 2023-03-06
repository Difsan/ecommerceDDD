package org.example.ecommerce.domain.delivery.command;

import org.example.ecommerce.generic.Command;
import org.example.ecommerce.generic.DomainEvent;

import java.time.LocalDate;

public class CreateDeliveryCommand extends Command {

    private String deliveryID;
    private LocalDate createDate;
    private LocalDate deliveredDate;

    public CreateDeliveryCommand(String deliveryID, LocalDate createDate) {
        this.deliveryID = deliveryID;
        this.createDate = createDate;
        this.deliveredDate = this.createDate.plusDays(8);
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

    public void setDeliveryID(String deliveryID) {
        this.deliveryID = deliveryID;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public void setDeliveredDate(LocalDate deliveredDate) {
        this.deliveredDate = deliveredDate;
    }
}
