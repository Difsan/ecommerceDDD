package org.example.ecommerce.domain.product.command;

import org.example.ecommerce.generic.Command;

public class ChangeTitleFromCategoryCommand extends Command {

    private String productID;
    private String categoryID;
    private String newTitle;

    public ChangeTitleFromCategoryCommand(String productID, String categoryID, String newTitle) {
        this.productID = productID;
        this.categoryID = categoryID;
        this.newTitle = newTitle;
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

    public String getNewTitle() {
        return newTitle;
    }

    public void setNewTitle(String newTitle) {
        this.newTitle = newTitle;
    }
}
