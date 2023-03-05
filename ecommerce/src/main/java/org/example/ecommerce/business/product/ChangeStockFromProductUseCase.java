package org.example.ecommerce.business.product;

import org.example.ecommerce.business.commons.EventsRepository;
import org.example.ecommerce.business.commons.UseCaseForCommand;
import org.example.ecommerce.domain.product.Product;
import org.example.ecommerce.domain.product.command.ChangeStockFromProduct;
import org.example.ecommerce.domain.product.values.ProductID;
import org.example.ecommerce.domain.product.values.Stock;
import org.example.ecommerce.generic.DomainEvent;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ChangeStockFromProductUseCase implements
        UseCaseForCommand<ChangeStockFromProduct> {
    private final EventsRepository eventsRepository;

    public ChangeStockFromProductUseCase(EventsRepository eventsRepository) {
        this.eventsRepository = eventsRepository;
    }
    @Override
    public List<DomainEvent> apply(ChangeStockFromProduct command) {
        List<DomainEvent> productEvents =  eventsRepository.findByAggregatedRootId(command.getProductID());
        Product product = Product.from(ProductID.of(command.getProductID()), productEvents);
        product.changeStockFromProduct(new Stock(command.getNewStock()));
        return product.getUncommittedChanges().stream().map(event->eventsRepository.saveEvent(event)).collect(Collectors.toList());
    }
}
