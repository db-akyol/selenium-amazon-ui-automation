# Amazon.com.tr Selenium UI Test Otomasyonu

Amazon Türkiye (`https://www.amazon.com.tr`) web arayüzünü uçtan uca doğrulayan, **Page Object Model (POM)** mimarisiyle kurgulanmış bir UI test otomasyon projesidir. Ürün arama, marka/fiyat filtreleme, sepete ekleme, oturum açma ve indirimli ürünler akışları **Selenium WebDriver 4** ile sürülür; testler **JUnit 5** üzerinde sıralı senaryolar hâlinde koşar ve **Maven Surefire** ile raporlanır.

![Java](https://img.shields.io/badge/Java-11-007396?style=flat-square&logo=openjdk&logoColor=white)
![Selenium](https://img.shields.io/badge/Selenium-4.27.0-43B02A?style=flat-square&logo=selenium&logoColor=white)
![JUnit5](https://img.shields.io/badge/JUnit-5.10.2-25A162?style=flat-square&logo=junit5&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-Surefire%203.1.2-C71A36?style=flat-square&logo=apachemaven&logoColor=white)
![WebDriverManager](https://img.shields.io/badge/WebDriverManager-5.6.2-FF6F00?style=flat-square)
![License](https://img.shields.io/badge/License-MIT-yellow?style=flat-square)

---

## Özellikler

- **Page Object Model mimarisi** — Her sayfa/bileşen (`HomePage`, `ProductsPage`, `ProductDetailPage`, `CardPage`, `LoginScreen`, `LeftSideBar`) ayrı bir sınıfta modellenir; locator'lar test kodundan tamamen izole edilmiştir.
- **Ortak `BasePage` soyutlaması** — `find`, `findAll`, `click`, `type`, `isDisplayed` gibi tekrar eden WebDriver çağrıları tek noktada toplanmıştır. Tüm sayfa sınıfları bu tabandan türer ve constructor üzerinden `WebDriver` enjeksiyonu alır.
- **Bileşen kompozisyonu** — `HomePage`, `SearchBox` ve `LoginBar` bileşenlerini kendi içinde barındırıp `searchBox()` / `loginSide()` erişimcileriyle sunar; böylece testler zincirlenebilir ve okunabilir kalır.
- **Merkezî `BaseTest` yaşam döngüsü** — `@BeforeAll` içinde WebDriverManager ile sürücü hazırlanır, `ChromeOptions` yapılandırılır ve tarayıcı tam ekran açılır. Tüm test sınıfları bu tabandan miras alır.
- **Akıllı bekleme stratejisi** — `implicitlyWait` (10 sn), `pageLoadTimeout` (30 sn) ve dinamik elementler için `WebDriverWait` + `ExpectedConditions` (`elementToBeClickable`, `visibilityOfElementLocated`) birlikte kullanılır.
- **Performans odaklı sürücü ayarları** — `PageLoadStrategy.EAGER` ile sayfa tam yüklenmeden teste devam edilir; `setAcceptInsecureCerts(true)` ile sertifika kaynaklı kesintiler engellenir.
- **JavaScript enjeksiyonu** — Selenium'un doğrudan sürükleyemediği fiyat aralığı slider'ı `JavascriptExecutor` ile değer ataması + `input` event tetikleme yöntemiyle hareket ettirilir; indirimli ürünler sayfasında lazy-load içeriği `window.scrollBy` döngüsüyle yüklenir.
- **Sıralı senaryo akışı** — `@TestInstance(PER_CLASS)` + `@TestMethodOrder(OrderAnnotation)` sayesinde tek bir tarayıcı oturumu üzerinde, birbirini takip eden adımlar (`@Order(1..4)`) hâlinde gerçek kullanıcı yolculuğu simüle edilir.
- **Dinamik locator üretimi** — `clickFilteredLaptopBrands(String)` metodu marka adını parametre alarak `aria-label` tabanlı CSS seçicisini çalışma zamanında oluşturur; her marka için ayrı locator yazma ihtiyacını ortadan kaldırır.
- **Çerez diyaloğu toleransı** — `acceptCookies()` çerez banner'ı varsa kapatır, yoksa akışı bozmadan devam eder.
- **Anlamlı assertion mesajları** — Her `Assertions.assertTrue` çağrısı hata ayıklamayı kolaylaştıran açıklayıcı bir mesajla birlikte gelir.

---

## Teknolojiler

| Teknoloji | Versiyon | Kullanım Amacı |
|---|---|---|
| **Java** | 11 (`maven.compiler.source/target`) | Ana programlama dili |
| **Selenium Java** | 4.27.0 | WebDriver API, `By` locator'ları, `Actions`, `JavascriptExecutor` |
| **Selenium Chrome Driver** | 4.27.0 | Chrome tarayıcı sürücü entegrasyonu |
| **WebDriverManager** (io.github.bonigarcia) | 5.6.2 | ChromeDriver binary'sinin otomatik indirilmesi ve sürüm eşlemesi |
| **JUnit Jupiter** | 5.10.2 | Test iskeleti, yaşam döngüsü anotasyonları, assertion API |
| **JUnit Jupiter Engine** | 5.10.2 | JUnit 5 test çalıştırma motoru |
| **Apache HttpClient 5** | 5.4 | Selenium HTTP taşıma katmanı bağımlılığı |
| **Maven Surefire Plugin** | 3.1.2 | Testlerin build sürecinde koşturulması ve raporlanması |
| **Maven** | — | Bağımlılık yönetimi ve derleme (`jar` packaging, UTF-8) |

---

## Proje Yapısı

```
amazon-test-2/
├── pom.xml                                  # Bağımlılıklar, Java 11, Surefire yapılandırması
├── LICENSE                                  # MIT
├── .gitignore
└── src/
    ├── main/java/                           # PAGE OBJECT KATMANI
    │   ├── BasePage.java                    # Ortak WebDriver sarmalayıcıları (find/click/type/isDisplayed)
    │   ├── HomePage.java                    # Ana sayfa: çerez, sepet sayacı, hamburger menü, bileşen erişimi
    │   ├── SearchBox.java                   # Arama kutusu bileşeni (arama + submit)
    │   ├── LoginBar.java                    # Üst bardaki "Hesap & Listeler" girişi
    │   ├── LoginScreen.java                 # E-posta / şifre adımları ve giriş ekranı doğrulaması
    │   ├── LeftSideBar.java                 # Hamburger menü: Çok Satanlar, Yeni Çıkanlar, Bilgisayarlar → Dizüstü
    │   ├── ProductsPage.java                # Arama sonuçları: marka filtresi, fiyat slider'ı, ürün seçimi
    │   ├── ProductDetailPage.java           # Ürün detay: sepete ekle, sayfa doğrulama
    │   ├── CardPage.java                    # Sepet sayfası: ürünün eklendiğinin doğrulanması
    │   ├── DiscountedProductsPage.java      # İndirimli ürünler sayfasına geçiş
    │   └── org/example/App.java             # Maven arketip giriş sınıfı
    │
    └── test/java/                           # TEST KATMANI
        ├── BaseTest.java                    # Driver kurulumu, timeout'lar, ChromeOptions, yaşam döngüsü
        ├── Test_Add_Product_To_Cart.java    # Arama → filtre → detay → sepete ekle → sepet doğrulama
        ├── Test_Filter_Price_Laptop.java    # Marka filtresi + fiyat aralığı slider testi
        ├── Test_Login_LogOut.java           # Giriş ekranı akışı (e-posta → şifre)
        ├── Test_Discounted_Products.java    # İndirimli ürünler + kademeli scroll ile lazy-load
        ├── TestNew.java                     # Hamburger menü üzerinden kategori navigasyonu
        └── org/example/AppTest.java         # Altyapı doğrulama (smoke) testi
```

---

## Test Senaryoları

### 1. `Test_Add_Product_To_Cart` — Sepete Ürün Ekleme (uçtan uca, 4 adım)

| Sıra | Adım | Doğrulama |
|---|---|---|
| `@Order(1)` | Çerezler kabul edilir, "laptop" araması yapılır, sonuç listesi genişletilir ve **Lenovo** marka filtresi uygulanır | Arama sonuçları sayfasında olunduğu (`s-search-result`) doğrulanır |
| `@Order(2)` | Listeden ikinci ürün seçilir | Ürün detay sayfasının "Sepete Ekle" butonu görünür |
| `@Order(3)` | Ürün sepete eklenir | Header'daki sepet sayacının **0'dan büyük** olduğu doğrulanır |
| `@Order(4)` | Sepet sayfasına gidilir | Sepette en az bir ürün kartı listelendiği doğrulanır |

### 2. `Test_Filter_Price_Laptop` — Fiyat Aralığı Filtresi

- "laptop" araması yapılır, sonuç paneli genişletilir, **Lenovo** filtresi uygulanır ve sonuç sayfasında olunduğu assert edilir.
- Fiyat aralığı slider'ı `JavascriptExecutor` ile `value = 50` atanıp `input` event'i tetiklenerek hareket ettirilir; güncel üst sınır etiketi okunup konsola yazılır ve filtre uygulama butonuna basılır. İşlem iki kez tekrarlanarak filtrenin yeniden uygulanabilirliği denenir.

### 3. `Test_Login_LogOut` — Oturum Açma Akışı

| Sıra | Adım | Doğrulama |
|---|---|---|
| `@Order(1)` | Çerezler kabul edilir, `LoginBar` üzerinden giriş ekranına gidilir | — |
| `@Order(2)` | E-posta girilir ve "Devam Et" tıklanır | Giriş ekranı konteyneri (`authportal-main-section`) görünür |
| `@Order(3)` | Şifre girilir ve "Giriş Yap" tıklanır | Şifre ekranının render edildiği doğrulanır |

> Not: Test verisi (e-posta/şifre) şu anda test sınıfı içinde sabit kodlanmıştır. Üretim ortamı için harici bir konfigürasyon/ortam değişkeni kaynağına taşınması önerilir.

### 4. `Test_Discounted_Products` — İndirimli Ürünler & Lazy-Load

- Çerezler kabul edilir ve ana sayfadaki indirim bannerı üzerinden indirimli ürünler sayfasına geçilir.
- Sayfa, 500 ms aralıklarla **60 iterasyon** boyunca 200'er piksel kaydırılarak (`window.scrollBy`) tembel yüklenen (lazy-loaded) ürün içeriğinin render edilmesi tetiklenir.

### 5. `TestNew` — Kategori Navigasyonu (Hamburger Menü)

- Çerezler kabul edilir, sol menü (`nav-hamburger-menu`) açılır.
- `LeftSideBar` üzerinden **Bilgisayarlar** kategorisi genişletilir ve `WebDriverWait` ile tıklanabilir hâle gelen **Dizüstü Bilgisayarlar** bağlantısına geçilir.

### 6. `org.example.AppTest` — Smoke Testi

- Maven/JUnit altyapısının doğru kurulduğunu doğrulayan, tarayıcı gerektirmeyen temel kontrol testi.

---

## Kurulum

### Gereksinimler

- **JDK 11** veya üzeri
- **Apache Maven 3.6+**
- **Google Chrome** (güncel sürüm — sürücü WebDriverManager tarafından otomatik indirilir, manuel `chromedriver` kurulumuna gerek yoktur)

### Adımlar

```bash
# 1. Depoyu klonlayın
git clone <repo-url>
cd amazon-test-2

# 2. Bağımlılıkları indirin ve projeyi derleyin
mvn clean install -DskipTests

# 3. Kurulumu doğrulayın
mvn -version
java -version
```

---

## Testleri Çalıştırma

```bash
# Tüm testleri çalıştır
mvn test

# Temiz derleme + tüm testler
mvn clean test

# Tek bir test sınıfını çalıştır
mvn test -Dtest=Test_Add_Product_To_Cart
mvn test -Dtest=Test_Filter_Price_Laptop
mvn test -Dtest=Test_Login_LogOut
mvn test -Dtest=Test_Discounted_Products
mvn test -Dtest=TestNew

# Tek bir test metodunu çalıştır
mvn test -Dtest=Test_Add_Product_To_Cart#add_product_to_card

# Birden fazla sınıfı birlikte çalıştır
mvn test -Dtest=Test_Add_Product_To_Cart+Test_Filter_Price_Laptop

# Bir test başarısız olsa bile tüm sınıfları koştur
mvn test -Dmaven.test.failure.ignore=true

# Sadece derleme (test koşturmadan)
mvn clean package -DskipTests
```

Surefire raporları çalıştırma sonrası `target/surefire-reports/` dizininde `.txt` ve `.xml` formatında üretilir.

> **Uyarı:** Testler canlı `amazon.com.tr` ortamına karşı koşar. Amazon'un DOM yapısı, bot koruması veya kampanya bannerları değiştiğinde locator'ların güncellenmesi gerekebilir.

---

## Lisans

Bu proje **MIT Lisansı** ile lisanslanmıştır. Detaylar için [LICENSE](LICENSE) dosyasına bakınız.

```
Copyright (c) 2025 Deniz Akyol
```
