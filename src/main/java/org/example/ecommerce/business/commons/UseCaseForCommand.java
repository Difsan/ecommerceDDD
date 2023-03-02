package org.example.ecommerce.business.commons;

import org.example.ecommerce.generic.Command;
import org.example.ecommerce.generic.DomainEvent;

import java.util.List;

public interface UseCaseForCommand <T extends Command>{
    List<DomainEvent> apply(T command);
}
