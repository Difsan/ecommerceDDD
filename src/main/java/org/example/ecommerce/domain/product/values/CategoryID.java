package org.example.ecommerce.domain.product.values;

import org.example.ecommerce.generic.Identity;

public class CategoryID extends Identity {

    public  CategoryID(String categoryID){
        super(categoryID);
    }

    public CategoryID(){}

    public static CategoryID of(String categoryID){
        return new CategoryID(categoryID);
    }
}
