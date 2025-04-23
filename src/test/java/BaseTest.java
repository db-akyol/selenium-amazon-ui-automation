import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BaseTest {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeAll
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.setAcceptInsecureCerts(true);
        options.setPageLoadStrategy(PageLoadStrategy.EAGER);
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.get("https://www.amazon.com.tr/");
        driver.manage().window().maximize();
    }

    @BeforeEach
    public void beforeMethod(){
        // Boş bırakıyoruz çünkü driver zaten setUp'ta başlatılıyor
    }

    @AfterAll
    public void tearDown(){

    }
}
