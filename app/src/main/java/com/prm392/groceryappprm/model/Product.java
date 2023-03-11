package com.prm392.groceryappprm.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class Product implements Serializable {
    int productId;
    String productName;
    String productDescription;
    String imageUrl;
    String discount;
    BigDecimal price;
    int rating;
    String productType;

    public Product() {
    }

    public Product(int productId, String productName, String productDescription, String imageUrl, String discount, BigDecimal price, int rating, String productType) {
        this.productId = productId;
        this.productName = productName;
        this.productDescription = productDescription;
        this.imageUrl = imageUrl;
        this.discount = discount;
        this.price = price;
        this.rating = rating;
        this.productType = productType;
    }

    public Product(int productId, String productName, String productDescription, String discount, BigDecimal price, int rating, String productType) {
        this.productId = productId;
        this.productName = productName;
        this.productDescription = productDescription;
        this.discount = discount;
        this.price = price;
        this.rating = rating;
        this.productType = productType;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }
}
