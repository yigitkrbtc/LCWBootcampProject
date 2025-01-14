package com.yigitkurbetci.lcwaikiki.pages;

import com.yigitkurbetci.lcwaikiki.model.ProductInfo;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductPage extends BasePage {

    @FindBy(xpath = "//button[contains(text(),'7-8 Yaş')]")
    private WebElement size;

    @FindBy(xpath = "//button[normalize-space()='SEPETE EKLE']")
    private WebElement addToCartButton;

    @FindBy(xpath = "//span[normalize-space()='Sepetim']")
    private WebElement cartButton;

    @FindBy(xpath = "//span[@class='badge-circle']")
    private WebElement cartBadge;

//    @FindBy(xpath = "//span[@class='product-name']")
//    private WebElement productName;
//
//    @FindBy(xpath = "//span[@class='product-color']")
//    private WebElement productColor;
//
//    @FindBy(xpath = "//span[@class='product-price']")
//    private WebElement productPrice;
//
//    @FindBy(xpath = "//input[@name='quantity']")
//    private WebElement quantityInput;

    private WebDriverWait wait;
    private WebDriver driver;
    // Constructor: WebDriver dışarıdan alınıyor
    public ProductPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }


    // Beden Seçimi
    public void selectSize() {
        waitForElementToBeVisible(size);
        waitForElementToBeClickable(size);
        size.click();
    }

    // Sepete ürün ekle
    public void addToCart() {
        waitForElementToBeVisible(addToCartButton);
        waitForElementToBeClickable(addToCartButton);
        clickElementWithJavaScript(addToCartButton);

    }

    // Sepeti aç
    public void openCart() {
        waitForElementToBeVisible(cartButton);
        waitForElementToBeClickable(cartButton);
        cartButton.click();
    }

    private void clickElementWithJavaScript(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);
    }

//    // Ürün bilgilerini al
//    public ProductInfo getProductInfo() {
//        String name = productName.getText();
//        String color = productColor.getText();
//        int quantity = Integer.parseInt(quantityInput.getAttribute("value"));
//
//
//        ProductInfo productInfo = new ProductInfo();
//        productInfo.setProductName(name);
//        productInfo.setProductColor(color);
//
//
//
//        return productInfo;
//    }

    // Bekleme metodları
    private void waitForElementToBeVisible(WebElement element) {
        wait.until(driver -> element.isDisplayed());
    }

    private void waitForElementToBeClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    // Sepete ürün eklenmesi ve "Sepetim" tuşuna tıklama işlemi
    public void openCartAfterAddingProduct() {
        addToCart();  // Ürünü sepete ekleme işlemi

        // 1. Sepete ürün eklenmesinin bildirildiği öğenin görünmesini bekleyin
        wait.until(ExpectedConditions.visibilityOf(cartBadge));  // Öğenin görünmesini bekle
        wait.until(ExpectedConditions.elementToBeClickable(cartBadge));  // Öğenin tıklanabilir olmasını bekle

        // 2. Sepetim tuşuna tıklama
        wait.until(ExpectedConditions.visibilityOf(cartButton));  // Sepetim tuşunun görünmesini bekle
        wait.until(ExpectedConditions.elementToBeClickable(cartButton));  // Sepetim tuşunun tıklanabilir olmasını bekle
        cartButton.click();  // Sepetim tuşuna tıklayın
    }






}
