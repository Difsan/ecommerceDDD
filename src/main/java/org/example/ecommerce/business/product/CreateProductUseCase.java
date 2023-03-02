package org.example.ecommerce.business.product;

import org.example.ecommerce.business.commons.EventsRepository;
import org.example.ecommerce.business.commons.UseCaseForCommand;
import org.example.ecommerce.domain.product.Product;
import org.example.ecommerce.domain.product.command.CreateProductCommand;
import org.example.ecommerce.domain.product.values.*;
import org.example.ecommerce.generic.DomainEvent;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CreateProductUseCase implements UseCaseForCommand<CreateProductCommand> {
    private final EventsRepository eventsRepository;

    public CreateProductUseCase(EventsRepository eventsRepository) {
        this.eventsRepository = eventsRepository;
    }

    @Override
    public List<DomainEvent> apply(CreateProductCommand command) {
        Product product = new Product(ProductID.of(command.getProductID()), new Title(command.getProductTitle()),
                new Brand(command.getProductBrand()), new Description(command.getProductDescription()),
                new UnitPrice(command.getProductUnitPrice()), new Stock(command.getProductStock()));
        return product.getUncommittedChanges().stream().map(event->eventsRepository.saveEvent(event)).collect(Collectors.toList());
    }
}
