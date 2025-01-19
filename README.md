1.Adım
İlk olarak test başlangıcında LC Waikiki web sitesine gidilir. hoverOverLogin() fonksiyonu ile görüntüde gösterilen “Üye Ol” ve “Giriş Yap” butonlarının olduğu kısım görüntülenir. clickLogin() metodu ile “Giriş Yap” butonuna tıklanır. Bahsi geçen hoverOverLogin()  ve clickLogin() fonksiyonları navigateToLoginPage() fonksiyonu içerisinde bir araya gelmiştir.

![giriş hover](https://github.com/user-attachments/assets/eed85495-c85c-4ee5-b0df-fe723930f195)


2.Adım
enterEmailAndContinue(email) fonksiyonu,e-mail girilecek alana parametre olarak girilen e-maili yapıştırır ve “Devam Et” butonuna tıklar.

![girş](https://github.com/user-attachments/assets/0682e972-278a-4839-babe-76b4adc32b82)



3.Adım
Bu kısımda enterPasswordAndLogin(password) fonksiyonu parametre olarak girilen şifreyi şifre kısmına yapıştırır ve “Giriş Yap” butonuna tıklar. 2. ve 3. Adımı içeren fonksiyonlar login(email,password) adlı fonksiyonda toplanmıştır. Ancak giriş kısmı sms onayında dolayı pas geçildi. Sadece gösterim olarak yer alıyor.
![giriş 2](https://github.com/user-attachments/assets/b464bd9d-f251-401c-8dfa-3f80c8a1d0a6)


4.Adım
İlk olarak anasayfada hoverOverKidsAndBaby()fonksiyonu ile “Çocuk & Bebek” kısmına hover işlemi yapılır.clickElementWhenVisible(girlChildMenu)fonksiyonu ile “KIZ ÇOCUK” seçeneğinin görünebilir olması beklenir ve ardından tıklanır.
selectMontVeKaban()fonksiyonu ile “Mont ve Kaban” kısmına tıklama aksiyonu gerçekleşir.


![mont ve kaban click](https://github.com/user-attachments/assets/a1df37a8-4084-4f4e-bb59-f68ff7c37134)


5.Adım
selectAgeGroups() adlı fonksiyonun içerisinde yer alan selectSizeOption(WebElement filterContainer, WebElement sizeContainer, WebElement targetAgeGroup) ilk olarak filtre scroll’unun aktif olması için sitenin scroll’unu aşağıya indiriyor.Filtre scroll’unun aktif olmasının ardından filtre scroll’u “Beden” container’ını bulacak şekilde aşağıya iner ve “6 Yaş” bedeni görünür ve tıklanabilir olduğu zaman tıklama aksiyonu gerçekleşir.scrollToActivateSizeScroll(WebElement sizeContainer, WebElement targetAgeGroup)metodu ile “Beden” container’ında “6-7 Yaş” ksımı scroll ile bulunur ve clickElementWithJavaScript(WebElement element)ile tıklanır. Aynı aksiyonlar “5-6 Yaş” için de gerçekleşir.waitForProductListUpdate() ile ürünlerin listelenmesinin yenilenmesi beklenir.

![yaş seçimi](https://github.com/user-attachments/assets/c4274fbe-2087-4f3c-8b4d-428091c8b133)


6.Adım
selectColor() metodu içerisinde yer alan scrollToElement(WebElement element)metodu “Renk” container’ının bulunmasını sağlar.Ardından
“Bej” rengine tıklama işlemi gerçekleştirilir.waitForProductListUpdate() ile ürünlerin listelenmesinin yenilenmesi beklenir.


![bej rengi seçimi](https://github.com/user-attachments/assets/0ad389b3-5b2c-4f1f-a7a6-2f249d2880f7)

7.Adım
sortByBestSellers() metodu ile ilk olarak dropdown’a  tıklanır. Dropdown içerisinde yer alan “En Çok Satanlar” kısmına tıklanır.waitForProductListUpdate() ile ürünlerin listelenmesinin yenilenmesi beklenir. Ardından clickFourthProduct() metodu ile 4. ürüne tıklama işlemi gerçekleştirilir.

![rün listesi](https://github.com/user-attachments/assets/9b8f2952-c016-4ea5-954d-428c76be228f)


8.Adım
selectSize() metodu ile “5-6 Yaş” bedeni tıklanır. Ardından openCartAfterAddingProduct()metodu ile içerisinde yer alan addToCart()metodu ile “Sepete Ekle” butonua tıklanır. “Sepetim” kısmına ürün eklendikten sonra oluşacak badge aksiyonu beklenir ve ardından “Sepetim” linkine tıklanır.

![sepete eklendi](https://github.com/user-attachments/assets/293d648e-ebc2-43aa-92b4-28c1bfeb1d53)
![sepetim](https://github.com/user-attachments/assets/7752f6fa-d37b-4768-82d4-72965d485f6b)


9.Adım
Sepete eklenen ürünün ismi,rengi ve adeti kontrol edildikten sonra “Kalp” ikonuna addToFavorites() metodu ile tıklanır. Ardından proceedToCheckout() metodu ile “Ödeme Adımına Geç” butonuna tıklanır ve ödeme adımına geçilir. Ancak testte giriş yapılmadığı için ödeme kısmı pas geçilmiştir.
![sepetim2](https://github.com/user-attachments/assets/32af3837-e72d-4fec-a231-229562cc308a)

![favorilerre kel](https://github.com/user-attachments/assets/bd9e13de-f866-4e40-b093-6ca13be1592b)

10.Adım
“Favoriler” kısmına basıldıktan sonra kullancının karşısına çıkan ürün,validateProductInFavorites()metodu ile
 ürün listelerinden çekilen ismi ile bu sayfadaki ürünün ismi eşleştirilir. Eğer eşleşme doğru ise test başarılı bir şekilde tamamlanır.

![favorilerim](https://github.com/user-attachments/assets/5e052437-aaa6-482e-9bbe-21b811470169)
