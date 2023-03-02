package org.example.ecommerce.business.commons;

import org.example.ecommerce.generic.DomainEvent;

import java.util.List;

public interface EventsRepository {
    DomainEvent saveEvent(DomainEvent event);

    List<DomainEvent> findByAggregatedRootId(String aggregatedRootId);
}
