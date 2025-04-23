import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class DiscountedProductsPage extends BasePage{

    By allDiscountedPagesLinkLocator = By.xpath("(//span[@class='a-truncate-cut'])[1]");

    public DiscountedProductsPage(WebDriver driver) {
        super(driver);
    }

    public void get_discounted_pages(){
        click(allDiscountedPagesLinkLocator);
    }

}
