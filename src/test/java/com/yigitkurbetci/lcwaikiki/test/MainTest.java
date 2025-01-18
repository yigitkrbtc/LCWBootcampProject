package com.yigitkurbetci.lcwaikiki.test;

import com.yigitkurbetci.lcwaikiki.pages.*;
import com.yigitkurbetci.lcwaikiki.utils.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.qameta.allure.*;

import java.time.Duration;
@Epic("LC Waikiki Testi")
@Feature("Tam Senaryo Testleri")
public class MainTest {
    private HomePage homePage;
    private LoginPage loginPage;
    private ProductListPage productListPage;
    private ProductPage productPage;
    private CartPage cartPage;
    private FavoritesPage favoritesPage;

    @BeforeClass
    public void setup() {
        // WebDriver'ı parametre olarak geçiriyoruz
        homePage = new HomePage(DriverManager.getDriver());
        loginPage = new LoginPage(DriverManager.getDriver());
        productListPage = new ProductListPage(DriverManager.getDriver());
        productPage = new ProductPage(DriverManager.getDriver());
        cartPage = new CartPage(DriverManager.getDriver());
        favoritesPage=new FavoritesPage(DriverManager.getDriver(),productListPage);
    }

    @Story("Kategorize Edilmiş Ürünün Sepete Eklenmesi ve Favoriler")
    @Description("Ürün Kategorizasyonu,Ürün Seçimi,Seçilmiş Ürünü Sepete Ekleme, Sepette Ürün İncelenmesi ve Favorilere EKleme, Favorilerim kısmının incelenmesi")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void testEndToEndScenario() {
        // LC Waikiki ana sayfasına git
        homePage.navigateToBaseUrl();
         //Giriş yap sayfasına git
        homePage.navigateToLoginPage();

        // Giriş yap
        loginPage.login("ykurbetci@gmail.com", "5mwVwbpg.BPb4rj");

        //"Çocuk-Bebek" -> "KIZ ÇOCUK" -> "MONT VE KABAN" seçim işlemi.
        homePage.navigateToCategoryPage();
        //Yaş gruplarının seçimi
        productListPage.selectAgeGroups();
        //Rengin seçilmesi
        productListPage.selectColor();
        // "En çok satılanlar" tıklama aksiyonu
        productListPage.sortByBestSellers();
        //Sıralanan ürünlerden 4. ürünün seçilme aksiyonu.
        productListPage.clickFourthProduct();
        //Seçilen ürünün beden seçimi
        productPage.selectSize();
        //Ürün sepete başarılı bir şekilde eklenilmesi ardından "Sepetim" belirtecine tıklanması.
        productPage.openCartAfterAddingProduct();
//        productPage.addToCart();
//        productPage.openCart();

        // Statik doğrulama işlemleri
        cartPage.validateCartWithStaticValues("LC Waikiki Kaban", "Bej", 1);

        // Favorilere ekle
        cartPage.addToFavorites();

        // Ödeme adımına geç
        cartPage.proceedToCheckout();

        // Favoriler sayfasına git
        favoritesPage.goToFavoritesPage();

        // Favorilerdeki ürünü doğrula
        favoritesPage.validateProductInFavorites();

    }

    @AfterClass
    public void teardown() {
        DriverManager.quitDriver();
    }
}
