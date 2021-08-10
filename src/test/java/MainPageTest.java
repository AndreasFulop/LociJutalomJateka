import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.sonatype.guice.bean.containers.Main;

import java.util.concurrent.TimeUnit;

public class MainPageTest {

    private WebDriver driver;

    @BeforeAll
    public static void Init() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void setDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        options.addArguments("incognito");
        options.addArguments("--disable-gpu", "--ignore-certificate-errors", "--disable-extensions", "--disable-dev-shm-usage");
        options.addArguments("window-size=1200,730");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(12, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void testMembers(){
        MainPage mainPage = new MainPage(driver);
        mainPage.cookieAccept();
        mainPage.MembersWriter("gitár");
    }

    @AfterEach
    public void closing()
     //     throws InterruptedException
    {
        //    Thread.sleep(1000);
        if (driver != null) {
            driver.quit();
        }
    }


}
