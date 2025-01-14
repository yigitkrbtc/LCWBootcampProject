package com.yigitkurbetci.lcwaikiki.model;

public class ProductInfo {
    private String productName;
    private String productColor;
    private String productSize;
    private double productPrice;

    // Yapıcı metod (Constructor)
    public ProductInfo(String productName, String productColor, String productSize, double productPrice) {
        this.productName = productName;
        this.productColor = productColor;
        this.productSize = productSize;
        this.productPrice = productPrice;
    }

    // Getter ve Setter metodları
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductColor() {
        return productColor;
    }

    public void setProductColor(String productColor) {
        this.productColor = productColor;
    }

    public String getProductSize() {
        return productSize;
    }

    public void setProductSize(String productSize) {
        this.productSize = productSize;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    @Override
    public String toString() {
        return "ProductInfo{" +
                "productName='" + productName + '\'' +
                ", productColor='" + productColor + '\'' +
                ", productSize='" + productSize + '\'' +
                ", productPrice=" + productPrice +
                '}';
    }
}
