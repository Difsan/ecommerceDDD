package org.example.ecommerce.business.delivery;

import org.example.ecommerce.business.commons.EventsRepository;
import org.example.ecommerce.business.commons.UseCaseForCommand;
import org.example.ecommerce.domain.delivery.Delivery;
import org.example.ecommerce.domain.delivery.command.AddDeliverymanCommand;
import org.example.ecommerce.domain.delivery.values.DeliveryID;
import org.example.ecommerce.domain.delivery.values.DeliverymanID;
import org.example.ecommerce.domain.delivery.values.Name;
import org.example.ecommerce.domain.delivery.values.Phone;
import org.example.ecommerce.generic.DomainEvent;
//import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

//@Component
public class AddDeliverymanUseCase implements UseCaseForCommand<AddDeliverymanCommand> {

    private final EventsRepository eventsRepository;

    public AddDeliverymanUseCase(EventsRepository eventsRepository) {
        this.eventsRepository = eventsRepository;
    }
    @Override
    public List<DomainEvent> apply(AddDeliverymanCommand command) {
        List<DomainEvent> deliveryEvents =  eventsRepository.findByAggregatedRootId(command.getDeliveryID());
        Delivery delivery = Delivery.from(DeliveryID.of(command.getDeliveryID()), deliveryEvents);
        delivery.addADeliveryman(DeliverymanID.of(command.getDeliverymanID()), new Name(command.getName()), new Phone(command.getPhone()));
        return delivery.getUncommittedChanges().stream().map(event->eventsRepository.saveEvent(event)).collect(Collectors.toList());
    }
}
