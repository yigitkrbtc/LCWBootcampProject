package com.yigitkurbetci.lcwaikiki.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage extends BasePage {
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(name = "emailAndPhone")
    private WebElement emailField;

    @FindBy(xpath = "//button[normalize-space()='Devam Et']")
    private WebElement continueButton; // "Devam Et" butonu

    @FindBy(name = "password")
    private WebElement passwordField;

    @FindBy(xpath = "//button[contains(text(),'Giriş Yap')]")
    private WebElement loginButton;

    // Constructor: WebDriver dışarıdan alınıyor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    /**
     * Email girildikten sonra "Devam Et" butonuna tıklama işlemi.
     * @param email Kullanıcı e-posta adresi
     */
    public void enterEmailAndContinue(String email) {
        emailField.sendKeys(email);
        continueButton.click();
    }

    /**
     * Şifre girme ve "Giriş Yap" butonuna tıklama işlemi.
     * @param password Kullanıcı şifresi
     */
    public void enterPasswordAndLogin(String password) {
        passwordField.sendKeys(password);
        loginButton.click();
    }
    public void login(String email, String password) {
        enterEmailAndContinue(email);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(passwordField));
        wait.until(ExpectedConditions.visibilityOf(loginButton));// Şifre alanı göründü mü kontrol et
        enterPasswordAndLogin(password);
        wait.until(ExpectedConditions.urlContains("https://www.lcw.com/")); // Giriş sonrası doğru URL'yi kontrol et
    }

}
