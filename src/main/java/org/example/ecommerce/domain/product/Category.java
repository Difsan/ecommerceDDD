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

    public void changeTitle(Title title){
        this.title = title;
    }
    public Title title(){
        return title;
    }
}
