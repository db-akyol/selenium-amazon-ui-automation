import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;


public class Test_Login_LogOut extends BaseTest{

    HomePage homepage;
    ProductsPage productsPage;
    ProductDetailPage productDetailPage;
    CardPage cardPage;
    LoginScreen loginScreen;

    @Test
    @Order(1)
    public void go_to_login_screen(){
        homepage = new HomePage(driver);
        homepage.acceptCookies();
        homepage.loginBar.go_to_login_screen();

    }

    @Test
    @Order(2)
    public void login_email(){
        loginScreen = new LoginScreen(driver);
        loginScreen.loginEmail("gs.deniz.21@gmail.com");
        Assertions.assertTrue(loginScreen.isOnLoginScreen(),
                "Not on login screen page");


    }

    @Test
    @Order(3)
    public void login_password(){
        loginScreen.loginPassword("***PAROLA-KALDIRILDI***");
        Assertions.assertTrue(loginScreen.isOnLoginScreen(),
                "Not on password screen page");
    }


}
