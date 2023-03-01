package org.example.ecommerce.domain.product.events;

import org.example.ecommerce.domain.product.values.CategoryID;
import org.example.ecommerce.domain.product.values.Title;
import org.example.ecommerce.generic.DomainEvent;

public class TitleChangedFromCategory extends DomainEvent {
    private final String categoryID;
    private final String newTitle;


    public TitleChangedFromCategory(String categoryID, String newTitle) {
        super("org.example.titleChangedFromCategory");
        this.categoryID = categoryID;
        this.newTitle = newTitle;
    }

    public String getCategoryID() {
        return categoryID;
    }

    public String getNewTitle() {
        return newTitle;
    }
}
