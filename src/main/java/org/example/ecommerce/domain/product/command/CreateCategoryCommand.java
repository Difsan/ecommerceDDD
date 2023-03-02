package org.example.ecommerce.domain.product.command;

import org.example.ecommerce.generic.Command;

public class CreateCategoryCommand extends Command {

    private String productID;

    private String categoryID;
    private String categoryTitle;

    public CreateCategoryCommand(String productID, String categoryID, String categoryTitle) {
        this.productID = productID;
        this.categoryID = categoryID;
        this.categoryTitle = categoryTitle;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }

    public String getCategoryTitle() {
        return categoryTitle;
    }

    public void setCategoryTitle(String categoryTitle) {
        this.categoryTitle = categoryTitle;
    }
}
