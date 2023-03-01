package org.example.ecommerce.domain.product.events;

import org.example.ecommerce.domain.product.values.CategoryID;
import org.example.ecommerce.domain.product.values.Title;
import org.example.ecommerce.generic.DomainEvent;

public class CategoryCreated extends DomainEvent {
    private final String categoryID;
    private final String categoryTitle;


    public CategoryCreated(String categoryID, String categoryTitle) {
        super("org.example.categoryCreated");
        this.categoryID = categoryID;
        this.categoryTitle = categoryTitle;
    }

    public String getCategoryID() {
        return categoryID;
    }

    public String getCategoryTitle() {
        return categoryTitle;
    }
}
