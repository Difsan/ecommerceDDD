package org.example.ecommerce.business.delivery;

import org.example.ecommerce.business.commons.EventsRepository;
import org.example.ecommerce.business.commons.UseCaseForCommand;
import org.example.ecommerce.domain.delivery.Delivery;
import org.example.ecommerce.domain.delivery.command.AddDeliverymanCommand;
import org.example.ecommerce.domain.delivery.command.AddOrderCommand;
import org.example.ecommerce.domain.delivery.values.*;
import org.example.ecommerce.generic.DomainEvent;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AddOrderUseCase implements UseCaseForCommand<AddOrderCommand> {

    private final EventsRepository eventsRepository;

    public AddOrderUseCase(EventsRepository eventsRepository) {
        this.eventsRepository = eventsRepository;
    }
    @Override
    public List<DomainEvent> apply(AddOrderCommand command) {
        List<DomainEvent> deliveryEvents =  eventsRepository.findByAggregatedRootId(command.getDeliveryID());
        Delivery delivery = Delivery.from(DeliveryID.of(command.getDeliveryID()), deliveryEvents);
        delivery.addAnOrder(new Order(command.getOrder()));
        return delivery.getUncommittedChanges().stream().map(event->eventsRepository.saveEvent(event)).collect(Collectors.toList());
    }
}
