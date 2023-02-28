package org.example.ecommerce.domain.product;

import org.example.ecommerce.domain.product.values.CategoryID;
import org.example.ecommerce.domain.product.values.Title;
import org.example.ecommerce.generic.Entity;

import java.util.Objects;

public class Category extends Entity<CategoryID> {
    private Title title;
    public Category(CategoryID categoryID, Title title) {
        super(categoryID);
        this.title = Objects.requireNonNull(title);
    }

    public Title title(){
        return title;
    }
}
