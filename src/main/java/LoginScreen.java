import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginScreen extends BasePage {

    By emailInputLocator = By.id("ap_email");
    By loginButtonLocator = By.id("continue");
    By passwordInputLocator = By.id("ap_password");
    By loginButtonLocator2 = By.id("signInSubmit");
    By loginScreenValidator = By.id("authportal-main-section");

    public LoginScreen(WebDriver driver) {
        super(driver);
    }

    public boolean isOnLoginScreen() {
        return isDisplayed(loginScreenValidator);
    }


    public void loginEmail(String email){
        type(emailInputLocator, email);
        click(emailInputLocator);
        click(loginButtonLocator);
    }

    public void loginPassword(String password){
        type(passwordInputLocator,password);
        click(loginButtonLocator2);
    }


}
