package com.yigitkurbetci.lcwaikiki.pages;

import com.yigitkurbetci.lcwaikiki.utils.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage extends BasePage {

    private WebDriver driver;
    private WebDriverWait wait;

    // Web elementlerin tanımları
    @FindBy(xpath = "//body/div[@id='root']/div[@class='page-wrapper']/div[@id='header__container']/header[@class='header header--high']/div[@class='header__middle']/div[@class='header__middle__right']/div[@class='header-section']/span[@class='user-wrapper']/div[1]/a[1]")
    private WebElement myAccountLink;

    @FindBy(xpath = "//span[contains(text(),'Hesabım')]")
    private WebElement newAccountLink;

    @FindBy(xpath = "//a[contains(@class,'cart-action__btn cart-action__btn--bg-blue')]")
    private WebElement loginLink;

    @FindBy(xpath = "//a[normalize-space()='ÇOCUK & BEBEK']")
    private WebElement kidsAndBabyMenu;

    @FindBy(xpath = "(//button[contains(@class,'tab-header')])[4]")
    private WebElement girlChildMenu;

    @FindBy(xpath = "//section[contains(@class,'content-tab')]//a[contains(@class,'')][normalize-space()='Mont ve Kaban']")
    private WebElement coatsLink;

    // Constructor: WebDriver dışarıdan alınıyor
    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Web elementleri tıklamak için kullanılan metod
    private void clickElement(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    // Web elementinin üzerine gelmek için kullanılan metod
    private void hoverOverElement(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
        new Actions(driver).moveToElement(element).perform();
    }

    // 'Mont ve Kaban' kategorisini seçmek için metod
    public void selectMontVeKaban() {
        clickElement(coatsLink);
    }

    // 'Çocuk ve Bebek' menüsünün üzerine gelmek için metod
    public void hoverOverKidsAndBaby() {
        hoverOverElement(kidsAndBabyMenu);
    }

    // 'Hesabım' menüsünün üzerine gelmek için metod
    public void hoverOverMyAccount() {
        hoverOverElement(myAccountLink);
    }

    // Giriş yapma işlemi için metod
    public void clickLogin() {
        clickElement(loginLink);
    }

    // Giriş sayfasına yönlendirme işlemi
    public void navigateToLoginPage() {
        hoverOverMyAccount();
        clickLogin();
    }

    public void navigateToCategoryPage() {
        try {
            System.out.println("Hovering over 'Çocuk ve Bebek' menu...");
            hoverOverKidsAndBaby();


            System.out.println("Hovering over 'Kız Çocuk' menu...");
            wait.until(ExpectedConditions.visibilityOf(girlChildMenu)).click();

            System.out.println("Clicking on 'Mont ve Kaban' category...");
            selectMontVeKaban();
        } catch (Exception e) {
            System.out.println("Error navigating to category page: " + e.getMessage());
            e.printStackTrace();
        }
    }


}
