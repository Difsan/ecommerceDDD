package org.example.ecommerce.business.order;

import org.example.ecommerce.business.commons.EventsRepository;
import org.example.ecommerce.business.commons.UseCaseForCommand;
import org.example.ecommerce.domain.order.Order;
import org.example.ecommerce.domain.order.command.ChangeTypeFromPaymentCommand;
import org.example.ecommerce.domain.order.values.OrderID;
import org.example.ecommerce.domain.order.values.PaymentID;
import org.example.ecommerce.domain.order.values.Type;
import org.example.ecommerce.generic.DomainEvent;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ChangeTypeFromPaymentUseCase implements
        UseCaseForCommand<ChangeTypeFromPaymentCommand> {
    private final EventsRepository eventsRepository;

    public ChangeTypeFromPaymentUseCase(EventsRepository eventsRepository) {
        this.eventsRepository = eventsRepository;
    }

    @Override
    public List<DomainEvent> apply(ChangeTypeFromPaymentCommand command) {
        List<DomainEvent> orderEvents =  eventsRepository.findByAggregatedRootId(command.getOrderID());
        Order order = Order.from(OrderID.of(command.getOrderID()), orderEvents);
        order.changeTypeFromPayment(PaymentID.of(command.getPaymentID()), new Type(command.getType()));
        return order.getUncommittedChanges().stream().map(event->eventsRepository.saveEvent(event)).collect(Collectors.toList());
    }
}
