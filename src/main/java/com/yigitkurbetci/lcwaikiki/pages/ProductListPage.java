package com.yigitkurbetci.lcwaikiki.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;
import java.util.List;

public class ProductListPage extends BasePage {

    @FindBy(xpath = "//div[@class='collapsible-filter-container__content-area collapsible-filter-container__content-area--size-filter']//div[4]//div[1]//span[1]")
    private WebElement ageGroup5to6;

    @FindBy(xpath = "//div[@class='collapsible-filter-container__content-area collapsible-filter-container__content-area--size-filter']//div[3]//div[1]//span[1]")
    private WebElement ageGroup6;

    @FindBy(xpath = "//div[@class='collapsible-filter-container__content-area collapsible-filter-container__content-area--size-filter']//div[4]//div[1]//span[1]")
    private WebElement ageGroup6to7;

    @FindBy(xpath = "//span[normalize-space()='BEJ']")
    private WebElement colorBeige;

    @FindBy(xpath = "//button[@class='dropdown-button__button']")
    private WebElement sortDropdown;

    @FindBy(xpath = "(//div[@class='product-card__product-info'])[4]")
    private WebElement fourthProduct;
    //Filtre kısmının scroll'u
    @FindBy(xpath="//div[@class='desktop-filter-area desktop-filter-area--fixed']")
    private WebElement scrollableContainer;
    //Beden kısmının scroll'u
    @FindBy(xpath="//div[@class='collapsible-filter-container__content-area collapsible-filter-container__content-area--size-filter']")
    private WebElement sizeContainer;

    @FindBy(xpath="//div[@class='product-list']")
    private WebElement siteScroll;

    @FindBy(xpath ="//div[@class='collapsible-filter-container__content-area collapsible-filter-container__content-area--color-filter']")
    private  WebElement colorContainer;

    @FindBy(xpath = "//div[4]//a[1]//div[2]//h5[2]")
    private WebElement favoriteProductNameElement;

    private WebDriverWait wait;
    private WebDriver driver;

    // Constructor: WebDriver dışarıdan alınıyor
    public ProductListPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public String getProductNameFromList() {
        return favoriteProductNameElement.getText().trim();
    }

    @Step("6,6-7,5-6 Yaş gruplarının seçimi")
    public void selectAgeGroups() {
        selectSizeOption(scrollableContainer, sizeContainer, ageGroup6);
        waitForProductListUpdate();
        scrollToActivateSizeScroll(sizeContainer, ageGroup6to7);
        clickElementWithJavaScript(ageGroup6to7);
        waitForProductListUpdate();
        scrollToActivateSizeScroll(sizeContainer, ageGroup5to6);
        clickElementWithJavaScript(ageGroup5to6);
        waitForProductListUpdate();
    }

    @Step("'BEJ' Renginin Seçilmesi")
    public void selectColor() {
        // 1. Site scroll'unu aşağı indirerek renk filter container'ını görebilecek şekilde kaydır
        scrollToElement(colorContainer);

        // 2. Bej rengini seç
        waitForElementToBeClickable(colorBeige);  // Renk seçeneğinin tıklanabilir olmasını bekle
        clickElementWithJavaScript(colorBeige);  // Bej rengini seç
        waitForProductListUpdate();
        driver.navigate().refresh();
    }

    @Step("Ürünlerin 'En Çok Satanlar' Filtrelenmesi")
    public void sortByBestSellers() {
        scrollToElement(sortDropdown);
        waitForElementToBeVisible(sortDropdown);
        waitForElementToBeClickable(sortDropdown);
        clickElementWithJavaScript(sortDropdown);

        // Açılan menüden 'En çok satanlar' seçeneğini seç
        WebElement bestSellersOption = driver.findElement(By.xpath("//a[normalize-space()='En çok satanlar']"));
        clickElementWithJavaScript(bestSellersOption);

        // Ürünlerin yenilenmesini bekle
        waitForProductListUpdate();
        driver.navigate().refresh();
    }

    @Step("Dördüncü ürüne tıklama")
    public void clickFourthProduct() {
        waitForElementToBeVisible(fourthProduct);
        clickElementWithJavaScript(fourthProduct);
        driver.navigate().refresh();
    }

    private void waitForPageToRefresh() {
        // Sayfa yüklenmesinin tamamlanmasını bekliyoruz
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(
                driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete")
        );
    }

    private void scrollToElement(WebElement element) {
        // JavaScript ile sayfayı elemente kaydırma
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
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

    //Filtrasyon yapıldıktan sonra ürünlerin listelenmesi.
    private void waitForProductListUpdate() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='product-grid']")));
    }

    @Step("Spesifik yaş grubunun scroll aracılığı ile bulunup seçilmesi")
    public void selectSizeOption(WebElement filterContainer, WebElement sizeContainer, WebElement targetAgeGroup) {
        // 1. Adım: Sitenin scroll'unu aşağı indir
        scrollToActivateSiteScroll();

        // 2. Adım: Filtre alanını kaydırarak "Beden" kısmına ulaş
        scrollToActivateFilterScroll(filterContainer, sizeContainer);

        scrollToAvoidCookiePopup(targetAgeGroup);

        // 3. Adım: Beden kısmını kaydırarak hedef yaş grubuna ulaş
        scrollToActivateSizeScroll(sizeContainer, targetAgeGroup);

        // 4. Adım: Hedef yaş grubunu seç
        waitForElementToBeClickable(targetAgeGroup);
        targetAgeGroup.click();
    }

    // Adım 1: Sitenin scroll'unu aşağı indir
    private void scrollToActivateSiteScroll() {
        waitForPageToRefresh();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 500);");
        // Sayfanın tamamen yüklenmesini bekle
    }

    // Adım 2: Filtre alanını kaydırarak "Beden" kısmına ulaş
    private void scrollToActivateFilterScroll(WebElement filterContainer, WebElement sizeContainer) {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Filtre alanında scroll yap
        if (isInnerScrollActivated(filterContainer)) {
            js.executeScript(
                    "arguments[0].scrollTop = arguments[1].offsetTop - arguments[0].offsetTop;",
                    filterContainer, sizeContainer
            );
        } else {
            throw new IllegalStateException("Filter container scroll is not activated.");
        }
        waitForElementToBeClickable(sizeContainer);
    }

    // Adım 3: Beden kısmını kaydırarak hedef yaş grubuna ulaş
    private void scrollToActivateSizeScroll(WebElement sizeContainer, WebElement targetAgeGroup) {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Beden kısmında scroll yap
        if (isInnerScrollActivated(sizeContainer)) {
            js.executeScript(
                    "arguments[0].scrollTop = arguments[1].offsetTop - arguments[0].offsetTop;",
                    sizeContainer, targetAgeGroup
            );
        } else {
            throw new IllegalStateException("Size container scroll is not activated.");
        }
    }

    // Scroll'un aktif olup olmadığını kontrol et
    private boolean isInnerScrollActivated(WebElement container) {
        return container.getAttribute("scrollHeight") != null &&
                Integer.parseInt(container.getAttribute("scrollHeight")) >
                        Integer.parseInt(container.getAttribute("clientHeight"));
    }

    @Step("Favorilerdeki ürün isminin kontrol edilmesi")
    public void validateProductInFavorites(String expectedProductName) {
        // Favoriler sayfasındaki ürün ismini al
        String favoriteProductName = favoriteProductNameElement.getText().trim();

        // İki ismin eşleşip eşleşmediğini kontrol et
        if (!favoriteProductName.equals(expectedProductName)) {
            throw new AssertionError("Favorilerdeki ürün ismi eşleşmiyor! Beklenen: " + expectedProductName + ", Bulunan: " + favoriteProductName);
        }
    }


    public void scrollToAvoidCookiePopup(WebElement targetElement) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 50);"); // Çerez bildirimini geçmek için kaydırma
        js.executeScript("arguments[0].scrollIntoView(true);", targetElement);
    }
}
