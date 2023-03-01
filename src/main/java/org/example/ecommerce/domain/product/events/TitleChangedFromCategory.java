package org.example.ecommerce.domain.product.events;

import org.example.ecommerce.domain.product.values.CategoryID;
import org.example.ecommerce.domain.product.values.Title;
import org.example.ecommerce.generic.DomainEvent;

public class TitleChangedFromCategory extends DomainEvent {
    private final CategoryID categoryID;
    private final Title title;


    public TitleChangedFromCategory(CategoryID categoryID, Title title) {
        super("org.example.titleChangedFromCategory");
        this.categoryID = categoryID;
        this.title = title;
    }

    public CategoryID getCategoryID() {
        return categoryID;
    }

    public Title getTitle() {
        return title;
    }
}
