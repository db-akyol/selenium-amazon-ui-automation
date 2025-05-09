import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LeftSideBar extends BasePage{

    HomePage homePage;

    By bestSellersLocator = By.linkText("Çok Satanlar");
    By topPerformersLocator = By.linkText("En İyi Performans Gösterenler");
    By newReleasesLocator = By.linkText("Yeni Çıkanlar");
    By computersLocator = By.xpath("//*[@id=\"hmenu-content\"]/div[1]/section[2]/ul/li[4]/a/i");




    public LeftSideBar(WebDriver driver) {
        super(driver);
    }

    public void clickBestSellers(){
        click(bestSellersLocator);
    }

    public void clickTopPerformers(){
        click(topPerformersLocator);
    }

    public void clickNewReleases(){
        click(newReleasesLocator);
    }

    public void goToLaptopsPage(){

        click(computersLocator);



        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        WebElement laptopLocator = driver.findElement(By.linkText("Dizüstü Bilgisayarlar"));

        System.out.println(laptopLocator);


        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href*='gp/browse.html']")));
        element.click();




        laptopLocator.click();

    }

}
