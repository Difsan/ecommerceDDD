package org.example.ecommerce.business.product;

import org.example.ecommerce.business.commons.EventsRepository;
import org.example.ecommerce.business.commons.UseCaseForCommand;
import org.example.ecommerce.domain.product.Product;
import org.example.ecommerce.domain.product.command.ChangeEmailFromSellerCommand;
import org.example.ecommerce.domain.product.values.*;
import org.example.ecommerce.generic.DomainEvent;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ChangeEmailFromSellerUseCase implements UseCaseForCommand<ChangeEmailFromSellerCommand> {
    private final EventsRepository eventsRepository;

    public ChangeEmailFromSellerUseCase(EventsRepository eventsRepository) {
        this.eventsRepository = eventsRepository;
    }

    @Override
    public List<DomainEvent> apply(ChangeEmailFromSellerCommand command) {
        List<DomainEvent> productEvents =  eventsRepository.findByAggregatedRootId(command.getProductID());
        Product product = Product.from(ProductID.of(command.getProductID()), productEvents);
        product.changeEmailFromSeller(SellerID.of(command.getSellerID()), new Email(command.getEmail()));
        return product.getUncommittedChanges().stream().map(event->eventsRepository.saveEvent(event)).collect(Collectors.toList());
    }
}
