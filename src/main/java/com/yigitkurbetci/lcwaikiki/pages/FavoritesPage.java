package com.yigitkurbetci.lcwaikiki.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class FavoritesPage extends BasePage {

    private WebDriver driver;
    private WebDriverWait wait;
    private ProductListPage productListPage; // ProductListPage referansı

    // Constructor: WebDriver ve ProductListPage dışarıdan alınıyor
    public FavoritesPage(WebDriver driver, ProductListPage productListPage) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this); // @FindBy ile tanımlanan elementleri başlatır
        this.productListPage = productListPage; // ProductListPage nesnesini atama
    }

    // Favoriler sayfasına gitmek için buton
    @FindBy(xpath = "//span[normalize-space()='Favorilerim']")
    private WebElement favoritesButton;

    // Favorilere eklenen ürünün ismini almak için element
    @FindBy(css = ".product-card__title")
    private WebElement favoriteProductName;

    // Favoriler sayfasına git
    public void goToFavoritesPage() {
        waitForElementToBeClickable(favoritesButton);
        favoritesButton.click();
    }

    // Favorilerdeki ürün ismini al
    public String getFavoriteProductName() {
        waitForElementToBeVisible(favoriteProductName);
        return favoriteProductName.getText().trim();
    }

    // Favorilerdeki ürünü doğrulamak için
    public void validateProductInFavorites() {
        // ProductListPage'den ürün ismini al
        String expectedProductName = productListPage.getProductNameFromList();

        // Favorilerdeki ürün ismini al
        String actualProductName = getFavoriteProductName();

        // Karşılaştırma yap
        if (!actualProductName.equals(expectedProductName)) {
            throw new AssertionError(
                    "Favorilerdeki ürün ismi eşleşmiyor! Beklenen: " + expectedProductName + ", Bulunan: " + actualProductName
            );
        }
    }

    // Elementin görünür olmasını bekle
    private void waitForElementToBeVisible(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    // Elementin tıklanabilir olmasını bekle
    private void waitForElementToBeClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }
}
