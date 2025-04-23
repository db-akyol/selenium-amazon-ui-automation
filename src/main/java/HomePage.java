import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class HomePage extends BasePage {

    SearchBox searchBox;
    LoginBar loginBar;
    By cartCountLocator = By.cssSelector("#nav-cart-count");
    By cartContainerLocator = By.cssSelector("#nav-cart-count-container");
    By acceptCookiesLocator = By.id("sp-cc-accept");


    public HomePage(WebDriver driver) {
        super(driver);
        searchBox = new SearchBox(driver);
        loginBar = new LoginBar((driver));
    }

    public SearchBox searchBox() {
        return this.searchBox;
    }

    public LoginBar loginSide(){
        return this.loginBar;
    }

    public boolean isProductCountUp() {
        return getCartCount() > 0;
    }

    public void goToCart() {
        click(cartCountLocator);
    }

    private int getCartCount(){
        String count = find(cartCountLocator).getText();
        return Integer.parseInt(count);
    }

    public void acceptCookies(){
        if (isDisplayed(acceptCookiesLocator)){
            click(acceptCookiesLocator);
        }
        else System.out.println("Cookies Yok");
    }
}
