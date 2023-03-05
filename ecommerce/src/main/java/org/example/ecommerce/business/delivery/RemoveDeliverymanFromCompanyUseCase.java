package org.example.ecommerce.business.delivery;

import org.example.ecommerce.business.commons.EventsRepository;
import org.example.ecommerce.business.commons.UseCaseForCommand;
import org.example.ecommerce.domain.delivery.Delivery;
import org.example.ecommerce.domain.delivery.command.RemoveDeliverymanFromCompanyCommand;
import org.example.ecommerce.domain.delivery.values.CompanyID;
import org.example.ecommerce.domain.delivery.values.DeliveryID;
import org.example.ecommerce.domain.delivery.values.DeliverymanID;
import org.example.ecommerce.generic.DomainEvent;

import java.util.List;

public class RemoveDeliverymanFromCompanyUseCase implements
        UseCaseForCommand <RemoveDeliverymanFromCompanyCommand> {

    private final EventsRepository eventsRepository;

    public RemoveDeliverymanFromCompanyUseCase(EventsRepository eventsRepository) {
        this.eventsRepository = eventsRepository;
    }

    @Override
    public List<DomainEvent> apply(RemoveDeliverymanFromCompanyCommand command) {
        List<DomainEvent> deliveryEvents =  eventsRepository.findByAggregatedRootId(command.getDeliveryID());
        Delivery delivery = Delivery.from(DeliveryID.of(command.getDeliveryID()), deliveryEvents);
        delivery.removeDeliverymanFromCompany(CompanyID.of(command.getCompanyID()), DeliverymanID.of(command.getDeliverymanID()));
        return null;
    }
}
