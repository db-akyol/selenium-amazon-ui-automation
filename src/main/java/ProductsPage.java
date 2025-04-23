import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static java.awt.SystemColor.text;

public class ProductsPage extends BasePage {

    By shippingOptionLocator = By.cssSelector("[data-component-type='s-search-result']");
    By productNameLocator = By.cssSelector(".a-size-base-plus.a-spacing-none.a-color-base.a-text-normal");
//    By laptopBrandsLocator3 = By.cssSelector("a[aria-label='Sonuçları daraltmak için HP filtresini uygulayın']");

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


}
