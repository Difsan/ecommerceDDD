package org.example.ecommerce.domain.product.command;

import org.example.ecommerce.generic.Command;

public class ChangeStockFromProductCommand extends Command {
    private String productID;
    private Integer newStock;


    public ChangeStockFromProductCommand(String productID, Integer newStock) {
        this.productID = productID;
        this.newStock = newStock;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public Integer getNewStock() {
        return newStock;
    }

    public void setNewStock(Integer newStock) {
        this.newStock = newStock;
    }
}
