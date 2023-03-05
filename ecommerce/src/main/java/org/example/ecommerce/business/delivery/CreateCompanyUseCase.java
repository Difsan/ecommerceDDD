package org.example.ecommerce.business.delivery;

import org.example.ecommerce.business.commons.EventsRepository;
import org.example.ecommerce.business.commons.UseCaseForCommand;
import org.example.ecommerce.domain.delivery.Delivery;
import org.example.ecommerce.domain.delivery.command.CreateCompanyCommand;
import org.example.ecommerce.domain.delivery.values.CompanyID;
import org.example.ecommerce.domain.delivery.values.DeliveryID;
import org.example.ecommerce.domain.delivery.values.Name;
import org.example.ecommerce.domain.delivery.values.Phone;
import org.example.ecommerce.generic.DomainEvent;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CreateCompanyUseCase implements UseCaseForCommand<CreateCompanyCommand> {

    private final EventsRepository eventsRepository;

    public CreateCompanyUseCase(EventsRepository eventsRepository) {
        this.eventsRepository = eventsRepository;
    }

    @Override
    public List<DomainEvent> apply(CreateCompanyCommand command) {
        List<DomainEvent> deliveryEvents =  eventsRepository.findByAggregatedRootId(command.getDeliveryID());
        Delivery delivery = Delivery.from(DeliveryID.of(command.getDeliveryID()), deliveryEvents);
        delivery.createCompany(CompanyID.of(command.getCompanyID()), new Name(command.getName()), new Phone(command.getPhone()));
        return delivery.getUncommittedChanges().stream().map(event->eventsRepository.saveEvent(event)).collect(Collectors.toList());
    }
}
