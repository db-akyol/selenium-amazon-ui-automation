import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestNew extends BaseTest {
    HomePage homepage;
    ProductsPage productsPage;
    LeftSideBar leftSideBar;
    WebDriverWait wait;

    @Test
    @Order(1)
    public void search_a_product(){
        homepage = new HomePage(driver);
        leftSideBar = new LeftSideBar(driver);

        //çerezleri kabul et
        homepage.acceptCookies();

        productsPage = new ProductsPage(driver);

        homepage.clickLeftBar();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        leftSideBar.goToLaptopsPage();
    }

}
