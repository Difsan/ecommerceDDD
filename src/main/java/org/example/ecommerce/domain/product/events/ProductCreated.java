package org.example.ecommerce.domain.product.events;

import org.example.ecommerce.domain.product.values.Features;
import org.example.ecommerce.generic.DomainEvent;

public class ProductCreated extends DomainEvent {
    private String featureTitle;
    private String featureBrand;
    private String featureDescription;
    private Double featureUnitPrice;
    private Integer featureStock;

    private String sellerID;
    private  String sellerName;
    private  String sellerNit;
    private  String sellerEmail;
    private  String sellerAbout;

    private  String categoryID;
    private  String categoryTitle;
    public ProductCreated() {
        super("ecommerce.postcreated");
    }

    public ProductCreated(String featureTitle, String featureBrand,
                          String featureDescription, Double featureUnitPrice,
                          Integer featureStock, String sellerID, String sellerName,
                          String sellerNit, String sellerEmail, String sellerAbout,
                          String categoryID, String categoryTitle) {
        super("ecommerce.postcreated");
        this.featureTitle = featureTitle;
        this.featureBrand = featureBrand;
        this.featureDescription = featureDescription;
        this.featureUnitPrice = featureUnitPrice;
        this.featureStock = featureStock;
        this.sellerID = sellerID;
        this.sellerName = sellerName;
        this.sellerNit = sellerNit;
        this.sellerEmail = sellerEmail;
        this.sellerAbout = sellerAbout;
        this.categoryID = categoryID;
        this.categoryTitle = categoryTitle;
    }

    public String getFeatureTitle() {
        return featureTitle;
    }

    public String getFeatureBrand() {
        return featureBrand;
    }

    public String getFeatureDescription() {
        return featureDescription;
    }

    public Double getFeatureUnitPrice() {
        return featureUnitPrice;
    }

    public Integer getFeatureStock() {
        return featureStock;
    }

    public String getSellerID() {
        return sellerID;
    }

    public String getSellerName() {
        return sellerName;
    }

    public String getSellerNit() {
        return sellerNit;
    }

    public String getSellerEmail() {
        return sellerEmail;
    }

    public String getSellerAbout() {
        return sellerAbout;
    }

    public String getCategoryID() {
        return categoryID;
    }

    public String getCategoryTitle() {
        return categoryTitle;
    }
}
