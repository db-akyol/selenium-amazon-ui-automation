import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class ProductDetailPage extends BasePage {

    By addToCartButtonLocator = By.id("add-to-cart-button");
    By priceLocator = By.cssSelector("span.a-price.a-text-price.sc-product-price.sc-white-space-nowrap.a-size-medium > span.a-offscreen");


    public ProductDetailPage(WebDriver driver) {
        super(driver);
    }

    public boolean isOnProductDetailPage() {
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        return wait.until(ExpectedConditions.presenceOfElementLocated(addToCartButtonLocator)).isDisplayed();
        return isDisplayed(addToCartButtonLocator);
    }

    public void addToCard() {
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        wait.until(ExpectedConditions.elementToBeClickable(addToCartButtonLocator)).click();
        click(addToCartButtonLocator);
    }

}
