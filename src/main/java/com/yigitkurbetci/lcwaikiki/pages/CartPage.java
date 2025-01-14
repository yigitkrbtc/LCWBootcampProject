package com.yigitkurbetci.lcwaikiki.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CartPage extends BasePage {

    @FindBy(xpath = "//span[@class='rd-cart-item-price mb-15']")
    private WebElement productTotalPriceLeft; // Sol taraf: Ürün kartındaki toplam fiyat

    @FindBy(xpath = "//div[@class='price-info-area']//span[@class='total-grand-box-amount'][normalize-space()='1.499,99 TL']")
    private WebElement totalPriceRight; // Sağ taraf: Ödeme adımına geç alanındaki toplam fiyat

    @FindBy(xpath = "//i[@class='fa fa-heart-o']")
    private WebElement addToFavoritesButton; // Favorilere ekle butonu

    @FindBy(xpath = "(//a[@class='main-button mt-15'][normalize-space()='ÖDEME ADIMINA GEÇ'])[1]")
    private WebElement proceedToCheckoutButton; // Ödeme adımına geç butonu

    @FindBy(xpath = "//span[@class='rd-cart-item-title']")
    private WebElement productBrandElement; // Sepetteki ürün markası

    @FindBy(xpath = "//span[@class='rd-cart-item-code']")
    private WebElement productNameElement; // Sepetteki ürün adı

    @FindBy(xpath = "//strong[normalize-space()='Koyu Bej']")
    private WebElement productColorElement; // Sepetteki ürün rengi

    @FindBy(xpath = "//input[@value='1']")
    private WebElement productQuantityElement; // Sepetteki ürün adedi

    private WebDriverWait wait;
    private WebDriver driver;
    // Constructor: WebDriver dışarıdan alınıyor
    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Sol taraftaki ürün toplam fiyatını al
    public double getProductTotalPriceFromLeft() {
        String priceText = productTotalPriceLeft.getText(); // Örnek: "2.999,98 TL"
        return parsePrice(priceText);
    }

    // Sağ taraftaki toplam fiyatı al
    public double getTotalPriceFromRight() {
        String priceText = totalPriceRight.getText(); // Örnek: "2.999,98 TL"
        return parsePriceForRight(priceText);
    }

    // Favorilere ekle
    public void addToFavorites() {
        waitForElementToBeClickable(addToFavoritesButton);
        clickElementWithJavaScript(addToFavoritesButton);
    }

    // Ödeme adımına geç
    public void proceedToCheckout() {
        waitForElementToBeClickable(proceedToCheckoutButton);
        proceedToCheckoutButton.click();
    }

    // Sepetteki ürün adını ve markasını birleştirerek al
    public String getProductFullName() {
        waitForElementToBeVisible(productBrandElement);
        waitForElementToBeVisible(productNameElement);

        String brand = productBrandElement.getText().trim(); // Örnek: "LC Waikiki"
        String name = productNameElement.getText().trim();   // Örnek: "Mont"

        return brand + " " + name; // Örnek: "LC Waikiki Mont"
    }

    // Sepetteki ürün rengini al
    public String getProductColor() {
        waitForElementToBeVisible(productColorElement);
        return productColorElement.getText().trim();
    }

    // Sepetteki ürün adedini al
    public int getProductQuantity() {
        waitForElementToBeVisible(productQuantityElement);
        String quantityText = productQuantityElement.getAttribute("value"); // Adet input içinden alınır
        return Integer.parseInt(quantityText);
    }

    // Sepetteki ürün bilgilerini verilen statik değerlerle doğrula
    public boolean validateCartItem(String expectedName, String expectedColor, int expectedQuantity) {
        return getProductFullName().equals(expectedName) &&
                getProductColor().equals(expectedColor) &&
                getProductQuantity() == expectedQuantity;
    }

    // Fiyat bilgisini TL formatından double'a çevir
    private double parsePrice(String priceText) {
        priceText = priceText.replace(".", "").replace(",", ".").replace(" TL", "").trim();
        return Double.parseDouble(priceText);
    }

    // Fiyatı parse eden yardımcı metot
    private double parsePriceForRight(String priceText) {
        // "GENEL TOPLAM" ve "TL" gibi metinleri temizle, boşlukları ve satır sonlarını da kaldır
        priceText = priceText.replace("GENEL TOPLAM", "").replace("TL", "").replace(".", "").replace(",", ".").trim();

        // Sayıyı double'a dönüştür
        return Double.parseDouble(priceText);
    }

    private void waitForElementToBeVisible(WebElement element) {
        wait.until(driver -> element.isDisplayed());
    }

    private void waitForElementToBeClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }
    private void clickElementWithJavaScript(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);
    }

    // Statik verilerle doğrulama y
    public void validateCartWithStaticValues(String expectedName, String expectedColor, int expectedQuantity) {
        // Ürün bilgilerini doğrula
        boolean isValid = validateCartItem(expectedName, expectedColor, expectedQuantity);
        if (!isValid) {
            throw new AssertionError("Sepetteki ürün bilgileri statik değerlerle eşleşmiyor!");
        }

        // Fiyat bilgilerini doğrula
        double leftPrice = getProductTotalPriceFromLeft();
        double rightPrice = getTotalPriceFromRight();
        if (leftPrice != rightPrice) {
            throw new AssertionError("Sepetteki sol ve sağ fiyatlar eşleşmiyor!");
        }
    }
}
