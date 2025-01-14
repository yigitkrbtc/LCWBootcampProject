package com.yigitkurbetci.lcwaikiki.pages;
import com.yigitkurbetci.lcwaikiki.utils.DriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.PageFactory;

import java.io.File;
import java.io.IOException;

public class BasePage {
    protected WebDriver driver;

    public BasePage() {
        this.driver = DriverManager.getDriver();
        PageFactory.initElements(driver, this);
    }

    public void takeScreenshot(String fileName) {
        // Ekran görüntüsü al
        TakesScreenshot screenshot = (TakesScreenshot) driver;
        File srcFile = screenshot.getScreenshotAs(OutputType.FILE);

        // Klasör yolu
        String folderPath = "./screenshots";
        File folder = new File(folderPath);

        // Klasör yoksa oluştur
        if (!folder.exists()) {
            folder.mkdirs(); // Alt klasörleriyle birlikte oluştur
        }

        // Ekran görüntüsünü belirlenen bir dosya yoluna kaydet
        try {
            FileHandler.copy(srcFile, new File(folderPath + "/" + fileName + ".png"));
            System.out.println("Screenshot saved: " + fileName);
        } catch (IOException e) {
            System.out.println("Screenshot could not be saved: " + e.getMessage());
        }
    }

}
