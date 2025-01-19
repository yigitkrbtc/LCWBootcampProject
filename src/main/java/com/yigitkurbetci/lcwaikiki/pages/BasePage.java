package com.yigitkurbetci.lcwaikiki.pages;
import com.yigitkurbetci.lcwaikiki.utils.DriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.PageFactory;
import io.qameta.allure.Attachment;

import java.io.File;
import java.io.IOException;

public class BasePage {
    protected WebDriver driver;
    protected static final String BASE_URL = "https://www.lcw.com/";
    public BasePage() {
        this.driver = DriverManager.getDriver();
        PageFactory.initElements(driver, this);
    }

    @Attachment(value = "{screenshotName}", type = "image/png")
    public byte[] takeScreenshot(String screenshotName) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    // URL'yi ziyaret etmek için bir metot
    public void navigateToBaseUrl() {
        driver.get(BASE_URL);
    }

}
