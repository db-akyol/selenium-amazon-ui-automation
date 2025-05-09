import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static java.awt.SystemColor.text;

public class ProductsPage extends BasePage {

    By shippingOptionLocator = By.cssSelector("[data-component-type='s-search-result']");
    By productNameLocator = By.cssSelector(".a-size-base-plus.a-spacing-none.a-color-base.a-text-normal");
//  By laptopBrandsLocator3 = By.cssSelector("a[aria-label='Sonuçları daraltmak için HP filtresini uygulayın']");
    By filterPriceLocator = By.id("p_36/range-slider_slider-item_upper-bound-slider");
    By FilterPriceButtonLocator = By.id("a-autoid-25");
    By filterPriceValue = By.cssSelector("label.sf-range-slider-label.sf-upper-bound-label > span");

    By expendPromptLocator = By.className("a-expander-prompt");


    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public boolean isOnProductPage() {
       return isDisplayed(shippingOptionLocator);
    }

    public void selectProduct(int i) {
        getAllProducts().get(i).click();
    }

    private List<WebElement> getAllProducts(){
      return findAll(productNameLocator);
    }

    public void clickFilteredLaptopBrands(String text){
        String selector = "a[aria-label='Sonuçları daraltmak için " + text + " filtresini uygulayın']";
        By laptopBrandsLocator = By.cssSelector(selector);
        click(laptopBrandsLocator);
    }

    public void clickexpendPromtLocator(){
        click(expendPromptLocator);

        try {
            Thread.sleep(3000); // 3 saniye bekler
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void moveSlider() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement slider = wait.until(ExpectedConditions.visibilityOfElementLocated(filterPriceLocator));

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].value = 50;", slider);
        js.executeScript("arguments[0].dispatchEvent(new Event('input'));", slider);

        String count = find(filterPriceValue).getText();
        System.out.println(count);

         click(FilterPriceButtonLocator);


    }



}
