package org.example.ecommerce.business.product;

import org.example.ecommerce.business.commons.EventsRepository;
import org.example.ecommerce.business.commons.UseCaseForCommand;
import org.example.ecommerce.domain.product.Product;
import org.example.ecommerce.domain.product.command.CreateSellerCommand;
import org.example.ecommerce.domain.product.values.*;
import org.example.ecommerce.generic.DomainEvent;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CreateSellerUseCase implements UseCaseForCommand<CreateSellerCommand> {
    private final EventsRepository eventsRepository;

    public CreateSellerUseCase(EventsRepository eventsRepository) {
        this.eventsRepository = eventsRepository;
    }

    @Override
    public List<DomainEvent> apply(CreateSellerCommand command) {
        List<DomainEvent> productEvents =  eventsRepository.findByAggregatedRootId(command.getProductID());
        Product product = Product.from(ProductID.of(command.getProductID()), productEvents);
        product.createSeller(SellerID.of(command.getSellerID()), new Name(command.getSellerName()),
                new Nit(command.getSellerNit()), new Email(command.getSellerEmail()), new Description(command.getSellerDescription()));
        return product.getUncommittedChanges().stream().map(event->eventsRepository.saveEvent(event)).collect(Collectors.toList());
    }
}
