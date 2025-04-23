import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class SearchBox extends BasePage{

    By searchBoxLocator = By.cssSelector("#twotabsearchtextbox");
    By submitButtonLocator = By.cssSelector("#nav-search-submit-button");

    public SearchBox(WebDriver driver) {
        super(driver);
    }

    public void search(String text){
        type(searchBoxLocator,text);
        click(submitButtonLocator);
    }
}
