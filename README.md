# Selenium Amazon UI Automation

[![Build](https://github.com/db-akyol/selenium-amazon-ui-automation/actions/workflows/build.yml/badge.svg)](https://github.com/db-akyol/selenium-amazon-ui-automation/actions/workflows/build.yml)
![Java](https://img.shields.io/badge/Java-11+-007396?logo=openjdk&logoColor=white)
![Selenium](https://img.shields.io/badge/Selenium-4.27-43B02A?logo=selenium&logoColor=white)
![JUnit5](https://img.shields.io/badge/JUnit-5-25A162?logo=junit5&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-C71A36?logo=apachemaven&logoColor=white)

UI test automation for [Amazon Türkiye](https://www.amazon.com.tr) with **Java, Selenium WebDriver 4 and JUnit 5**, built with the **Page Object Model**.
The tests cover search, brand and price filters, adding a product to the cart, login and category navigation.

## What is tested

| Test class | Scenario | Checks |
|---|---|---|
| `Test_Add_Product_To_Cart` | Search "laptop" → filter by brand (Lenovo) → open a product → add to cart → open cart | Search results page is shown, product page is shown, cart counter increases, product is in the cart |
| `Test_Filter_Price_Laptop` | Search "laptop" → brand filter → move the price slider → apply | Results page is shown after filtering |
| `Test_Login_LogOut` | Open login → enter e-mail → enter password | Login screen steps are shown |
| `Test_Discounted_Products` | Open the deals page and scroll to load more products (lazy load) | Page loads without errors |
| `TestNew` | Hamburger menu → Computers → Laptops | Category link is clickable |

Test methods inside a class run in order (`@Order`) on one browser session, like a real user journey.

## Framework design

- **Page Object Model**: every page or component has its own class (`HomePage`, `SearchBox`, `ProductsPage`, `ProductDetailPage`, `CardPage`, `LoginScreen`, `LeftSideBar`). Tests never use locators directly.
- **BasePage**: shared WebDriver helpers (`find`, `click`, `type`, `isDisplayed`).
- **BaseTest**: driver setup with WebDriverManager, Chrome options, timeouts and teardown.
- **Explicit waits**: `WebDriverWait` + `ExpectedConditions` for dynamic elements.
- **JavaScript executor**: used for the price slider and for scrolling lazy-loaded content.
- **Dynamic locators**: brand filter locator is built from the brand name at runtime.
- **No secrets in code**: login data comes from environment variables.

## Project structure

```
src/
├── main/java/            # Page objects
│   ├── BasePage.java
│   ├── HomePage.java
│   ├── SearchBox.java
│   ├── LoginBar.java
│   ├── LoginScreen.java
│   ├── LeftSideBar.java
│   ├── ProductsPage.java
│   ├── ProductDetailPage.java
│   ├── CardPage.java
│   └── DiscountedProductsPage.java
└── test/java/            # Tests
    ├── BaseTest.java
    ├── Test_Add_Product_To_Cart.java
    ├── Test_Filter_Price_Laptop.java
    ├── Test_Login_LogOut.java
    ├── Test_Discounted_Products.java
    └── TestNew.java
```

## How to run

Requirements: JDK 11+, Maven 3.6+, Google Chrome (the driver is downloaded automatically).

```bash
mvn test                                  # all tests
mvn test -Dtest=Test_Add_Product_To_Cart  # one test class
```

The login test reads the account from environment variables:

```bash
export AMAZON_EMAIL="you@example.com"
export AMAZON_PASSWORD="your-password"
mvn test -Dtest=Test_Login_LogOut
```

Surefire reports are written to `target/surefire-reports/`.

## Notes

- Tests run against the live amazon.com.tr site. If Amazon changes its page structure or shows a bot check, some locators may need an update.
- For this reason the CI pipeline only compiles the project; the UI tests are run locally.

## License

[MIT](LICENSE)
