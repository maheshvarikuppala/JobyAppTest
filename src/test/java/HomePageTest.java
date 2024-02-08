import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

@Listeners(EBankAppReport.class)

public class HomePageTest {

    public WebDriver driver;

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver",
                "C:\\Users\\MYPC\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://qaebank.ccbp.tech/ebank/login");

        WebElement userIdInput = driver.findElement(By.id("userIdInput"));
        userIdInput.sendKeys("142420");

        WebElement pinInput = driver.findElement(By.id("pinInput"));
        pinInput.sendKeys("231225");

        WebElement loginButton = driver.findElement(By.className("login-button"));
        loginButton.click();

        String expectedUrl = "https://qaebank.ccbp.tech/";

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.urlToBe(expectedUrl));
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test(priority = 1)
    public void testHomepageHeading() {
        WebElement headingEl = driver.findElement(By.className("heading"));
        String actualHeading = headingEl.getText();

        String expectedHeading = "Your Flexibility, Our Excellence";
        Assert.assertEquals(actualHeading, expectedHeading, "Heading text does not match");

    }

    @Test(priority = 2)
    public void testLogoutFunctionality() {
        WebElement logoutBtn = driver.findElement(By.className("logout-button"));
        logoutBtn.click();

        String expectedLoginUrl = "https://qaebank.ccbp.tech/ebank/login";

        String currentUrl = driver.getCurrentUrl();

        Assert.assertEquals(currentUrl, expectedLoginUrl, "Login URL do not match");

    }

}
