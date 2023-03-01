package org.example.ecommerce.domain.product.command;

import org.example.ecommerce.generic.Command;

public class CreateCategoryCommand extends Command {

    private String productID;
    private String categoryTitle;

    public CreateCategoryCommand(String productID, String categoryTitle) {
        this.productID = productID;
        this.categoryTitle = categoryTitle;
    }

    public String getProductID() {
        return productID;
    }

    public String getCategoryTitle() {
        return categoryTitle;
    }

    public void setCategoryTitle(String categoryTitle) {
        this.categoryTitle = categoryTitle;
    }
}
