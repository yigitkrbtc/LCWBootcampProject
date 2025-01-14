package com.yigitkurbetci.lcwaikiki.utils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import io.github.bonigarcia.wdm.WebDriverManager;


public class DriverManager {
    private static WebDriver driver;

    public static WebDriver getDriver() {
        if (driver == null) {
            WebDriverManager.chromedriver().setup();

            // ChromeOptions ile özel ayarları ekleyelim
            ChromeOptions cop = new ChromeOptions();
            cop.addArguments("--disable-notifications");
            cop.addArguments("--remote-allow-origins=*");

            driver = new ChromeDriver(cop); // ChromeDriver, ChromeOptions ile başlatılıyor
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // Bekleme süresi ekleniyor

            System.out.println("The setup process is completed...");
        }
        return driver;
    }

    public static WebDriverWait getWait() {
        return new WebDriverWait(getDriver(), Duration.ofSeconds(10));
    }


    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
