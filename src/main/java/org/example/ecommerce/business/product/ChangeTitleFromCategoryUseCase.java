package org.example.ecommerce.business.product;

import org.example.ecommerce.business.commons.EventsRepository;
import org.example.ecommerce.business.commons.UseCaseForCommand;
import org.example.ecommerce.domain.product.Product;
import org.example.ecommerce.domain.product.command.ChangeTitleFromCategory;
import org.example.ecommerce.domain.product.values.CategoryID;
import org.example.ecommerce.domain.product.values.ProductID;
import org.example.ecommerce.domain.product.values.Title;
import org.example.ecommerce.generic.DomainEvent;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ChangeTitleFromCategoryUseCase implements
        UseCaseForCommand<ChangeTitleFromCategory> {
    private final EventsRepository eventsRepository;

    public ChangeTitleFromCategoryUseCase(EventsRepository eventsRepository) {
        this.eventsRepository = eventsRepository;
    }

    @Override
    public List<DomainEvent> apply(ChangeTitleFromCategory command) {
        List<DomainEvent> productEvents =  eventsRepository.findByAggregatedRootId(command.getProductID());
        Product product = Product.from(ProductID.of(command.getProductID()), productEvents);
        product.changeTitleFromCategory(CategoryID.of(command.getCategoryID()), new Title(command.getNewTitle()));
        return product.getUncommittedChanges().stream().map(event->eventsRepository.saveEvent(event)).collect(Collectors.toList());
    }
}
