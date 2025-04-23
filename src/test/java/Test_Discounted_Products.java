import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.JavascriptExecutor;


public class Test_Discounted_Products extends BaseTest{
    HomePage homepage;
    ProductsPage productsPage;
    ProductDetailPage productDetailPage;
    CardPage cardPage;
    DiscountedProductsPage discountedProductsPage;

    @Test
    @Order(1)
    public void get_discounted_products_page() throws InterruptedException {
        homepage = new HomePage(driver);
        // COOKİES KABUL ET
        homepage.acceptCookies();

        discountedProductsPage = new DiscountedProductsPage(driver);
        // İNDİRİMLİ ÜRÜNLER SAYFASINA GİT
        discountedProductsPage.get_discounted_pages();


        for(int i = 0; i<60;i++){

            try {
                Thread.sleep(500); // 2 saniye bekle
                // JavascriptExecutor ile sayfayı 500 piksel aşağı kaydır
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("window.scrollBy(0,200)");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }





//        //SCROLL'U EN AŞAĞI KAYDIR
//        JavascriptExecutor js = (JavascriptExecutor) driver;
//        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
//
//        // BEKLE
//        try {
//            Thread.sleep(2000); // 2 saniye bekle
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }




    }

}
