package org.example.ecommerce.domain.product.command;

import org.example.ecommerce.generic.Command;

public class CreateProductCommand extends Command {

    private String productID;

    private String productTitle;
    private String productBrand;
    private String productDescription;
    private Double productUnitPrice;
    private Integer productStock;

    public CreateProductCommand(String productID, String productTitle, String productBrand,
                                String productDescription, Double productUnitPrice,
                                Integer productStock) {
        this.productID = productID;
        this.productTitle = productTitle;
        this.productBrand = productBrand;
        this.productDescription = productDescription;
        this.productUnitPrice = productUnitPrice;
        this.productStock = productStock;
    }

    public String getProductID() {
        return productID;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public String getProductBrand() {
        return productBrand;
    }

    public void setProductBrand(String productBrand) {
        this.productBrand = productBrand;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public Double getProductUnitPrice() {
        return productUnitPrice;
    }

    public void setProductUnitPrice(Double productUnitPrice) {
        this.productUnitPrice = productUnitPrice;
    }

    public Integer getProductStock() {
        return productStock;
    }

    public void setProductStock(Integer productStock) {
        this.productStock = productStock;
    }
}
