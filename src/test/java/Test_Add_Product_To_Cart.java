import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

public class Test_Add_Product_To_Cart extends BaseTest{

    HomePage homepage;
    ProductsPage productsPage;
    ProductDetailPage productDetailPage;
    CardPage cardPage;


    @Test
    @Order(1)
    public void search_a_product(){
        homepage = new HomePage(driver);

        //çerezleri kabul et
        homepage.acceptCookies();

        productsPage = new ProductsPage(driver);

//        // laptop araması yap
//        homepage.searchBox().search("laptop");
//
//        // arama tuşuna bas
//        productsPage.clickexpendPromtLocator();
//
//        productsPage.clickFilteredLaptopBrands("Lenovo");
//
//        // kontrol
//        Assertions.assertTrue(productsPage.isOnProductPage() ,
//                "Not on products page");
    }



//    @Test
//    @Order(2)
//    public void select_product(){
//        productDetailPage = new ProductDetailPage(driver);
//        productsPage.selectProduct(1);
//        Assertions.assertTrue(productDetailPage.isOnProductDetailPage(),
//                "Not on product detail page");
//
//    }
//
//    @Test
//    @Order(3)
//    public void add_product_to_card(){
//        productDetailPage.addToCard();
//        Assertions.assertTrue(homepage.isProductCountUp() ,
//                "Product Count did not increase");
//
//    }
//
//    @Test
//    @Order(4)
//    public void go_to_card(){
//        cardPage = new CardPage(driver);
//        homepage.goToCart();
//        Assertions.assertTrue(cardPage.checkIfProductAdded(),
//                "Product was not added to card");
//
//    }
//

}
