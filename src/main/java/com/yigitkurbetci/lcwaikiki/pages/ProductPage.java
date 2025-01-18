package com.yigitkurbetci.lcwaikiki.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductPage extends BasePage {

    @FindBy(xpath = "//button[contains(text(),'5-6 Yaş')]")
    private WebElement size;

    @FindBy(xpath = "//button[normalize-space()='SEPETE EKLE']")
    private WebElement addToCartButton;

    @FindBy(xpath = "//span[normalize-space()='Sepetim']")
    private WebElement cartButton;

    @FindBy(xpath = "//span[@class='badge-circle']")
    private WebElement cartBadge;

    private WebDriverWait wait;
    private WebDriver driver;

    // Constructor: WebDriver dışarıdan alınıyor
    public ProductPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Beden Seçimi
    @Step("Beden Seçimi")
    public void selectSize() {
        waitForElementToBeVisible(size);
        waitForElementToBeClickable(size);
        size.click();
    }

    // Sepete ürün ekle
    @Step("Ürün Sepete Eklenmesi")
    public void addToCart() {
        waitForElementToBeVisible(addToCartButton);

        // Scroll işlemi: "Sepete Ekle" butonunu görünür yap
        scrollToElement(addToCartButton);

        waitForElementToBeClickable(addToCartButton);
        clickElementWithJavaScript(addToCartButton);
    }

    // Sepeti aç
    @Step("Sepetin Açılması")
    public void openCart() {
        waitForElementToBeVisible(cartButton);
        waitForElementToBeClickable(cartButton);
        cartButton.click();
    }

    private void clickElementWithJavaScript(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);
    }

    // Sepete ürün eklenmesi ve "Sepetim" tuşuna tıklama işlemi
    @Step("Sepetim Sayfasına Erişim Sağlanması")
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

    // Scroll işlemi: Bir elementi görünür yapmak için scroll
    private void scrollToElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'nearest'});", element);
    }

    // Bekleme metodları
    private void waitForElementToBeVisible(WebElement element) {
        wait.until(driver -> element.isDisplayed());
    }

    private void waitForElementToBeClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }
}
