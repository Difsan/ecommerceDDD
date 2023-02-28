package org.example.ecommerce.domain.product;

import org.example.ecommerce.domain.product.values.CategoryID;
import org.example.ecommerce.generic.Entity;

public class Category extends Entity<CategoryID> {
    public Category(CategoryID id) {
        super(id);
    }
}
