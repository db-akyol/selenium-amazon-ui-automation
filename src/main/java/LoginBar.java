import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginBar extends BasePage {

    By accountListLocator = By.id("nav-link-accountList");

    public LoginBar(WebDriver driver) {
        super(driver);
    }

    public void go_to_login_screen(){
        click(accountListLocator);
    }
}
