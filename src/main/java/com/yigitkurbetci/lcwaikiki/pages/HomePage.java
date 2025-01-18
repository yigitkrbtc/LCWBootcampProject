package com.yigitkurbetci.lcwaikiki.pages;

import com.yigitkurbetci.lcwaikiki.utils.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.qameta.allure.Step;

import java.time.Duration;

public class HomePage extends BasePage {

    private WebDriver driver;
    private WebDriverWait wait;

    // Web elementlerin tanımları
    @FindBy(xpath = "//body/div[@id='root']/div[@class='page-wrapper']/div[@id='header__container']/header[@class='header header--high']/div[@class='header__middle']/div[@class='header__middle__right']/div[@class='header-section']/span[@class='user-wrapper']/div[1]/a[1]")
    private WebElement firstLoginLink;

    @FindBy(xpath = "//span[contains(text(),'Hesabım')]")
    private WebElement newAccountLink;

    @FindBy(xpath = "//a[contains(@class,'cart-action__btn cart-action__btn--bg-blue')]")
    private WebElement loginLink;

    @FindBy(xpath = "//input[@id='username']")  // Username input elementi
    private WebElement usernameInput;

    @FindBy(xpath = "//input[@id='password']")  // Password input elementi
    private WebElement passwordInput;

    @FindBy(xpath = "//button[@type='submit']")  // Login butonu
    private WebElement loginButton;


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
    @Step("Clicking on 'Mont ve Kaban' category")
    public void selectMontVeKaban() {
        clickElement(coatsLink);
    }

    // 'Çocuk ve Bebek' menüsünün üzerine gelmek için metod
    @Step("Hovering over 'Çocuk ve Bebek' menu")
    public void hoverOverKidsAndBaby() {
        hoverOverElement(kidsAndBabyMenu);
    }

    // 'Hesabım' menüsünün üzerine gelmek için metod
    public void hoverOverLogin() {
        hoverOverElement(firstLoginLink);
    }

    // Giriş yapma işlemi için metod
    @Step("Clicking on Login link")
    public void clickLogin() {
        clickElement(loginLink);
    }

    // Giriş sayfasına yönlendirme işlemi
    @Step("Navigating to Login Page")
    public void navigateToLoginPage() {
        hoverOverLogin();
        clickLogin();
    }

    // Login işlemi için metod
    @Step("Entering username")
    public void enterUsername(String username) {
        wait.until(ExpectedConditions.visibilityOf(usernameInput)).sendKeys(username);
    }

    @Step("Entering password")
    public void enterPassword(String password) {
        wait.until(ExpectedConditions.visibilityOf(passwordInput)).sendKeys(password);
    }

    @Step("Clicking on the Login button")
    public void clickLoginButton() {
        clickElement(loginButton);
    }

    // Login işlemini tamamlamak için metod
    @Step("Logging in with username and password")
    public void login(String username, String password) {
        navigateToLoginPage();
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
    }


    // Web element görünür olduğunda tıklama işlemi
    @Step("Clicking on 'Kız Çocuk' menu")
    public void clickElementWhenVisible(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element)).click();
    }

    public void navigateToCategoryPage() {
        try {
            System.out.println("Hovering over 'Çocuk ve Bebek' menu...");
            hoverOverKidsAndBaby();

            System.out.println("Hovering over 'Kız Çocuk' menu...");
            clickElementWhenVisible(girlChildMenu);  // Burada yeni metodu kullanıyoruz

            System.out.println("Clicking on 'Mont ve Kaban' category...");
            selectMontVeKaban();
        } catch (Exception e) {
            System.out.println("Error navigating to category page: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
