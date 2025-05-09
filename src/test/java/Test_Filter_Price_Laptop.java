import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;



public class Test_Filter_Price_Laptop extends BaseTest{

    HomePage homepage;
    ProductsPage productsPage;
    ProductDetailPage productDetailPage;
    CardPage cardPage;

    @Test
    @Order(1)
    public void search_laptop(){
        homepage = new HomePage(driver);

        //çerezleri kabul et
        homepage.acceptCookies();

        productsPage = new ProductsPage(driver);

        // laptop araması yap
        homepage.searchBox().search("laptop");

        // arama tuşuna basıyor zaten search metodunda ama kod anlaşılabilir olsun diye burada tekrar basıyorum
        productsPage.clickexpendPromtLocator();

        productsPage.clickFilteredLaptopBrands("Lenovo");

        // kontrol
        Assertions.assertTrue(productsPage.isOnProductPage() ,
                "Not on products page");

    }

    @Test
    @Order(2)
    public void filter_price(){
        productsPage.moveSlider();
        productsPage.moveSlider();
    }
}
