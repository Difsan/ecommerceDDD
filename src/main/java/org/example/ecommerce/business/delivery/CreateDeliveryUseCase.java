package org.example.ecommerce.business.delivery;

import org.example.ecommerce.business.commons.EventsRepository;
import org.example.ecommerce.business.commons.UseCaseForCommand;
import org.example.ecommerce.domain.delivery.Delivery;
import org.example.ecommerce.domain.delivery.command.CreateDeliveryCommand;
import org.example.ecommerce.domain.delivery.values.CreateDate;
import org.example.ecommerce.domain.delivery.values.DeliveredDate;
import org.example.ecommerce.domain.delivery.values.DeliveryID;
import org.example.ecommerce.generic.DomainEvent;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CreateDeliveryUseCase implements UseCaseForCommand<CreateDeliveryCommand> {
    private final EventsRepository eventsRepository;

    public CreateDeliveryUseCase(EventsRepository eventsRepository) {
        this.eventsRepository = eventsRepository;
    }

    @Override
    public List<DomainEvent> apply(CreateDeliveryCommand command) {
        Delivery delivery = new Delivery(DeliveryID.of(command.getDeliveryID()), new CreateDate(command.getCreateDate()),
                new DeliveredDate(command.getDeliveredDate()));
        return delivery.getUncommittedChanges().stream().map(event->eventsRepository.saveEvent(event)).collect(Collectors.toList());
    }
}
