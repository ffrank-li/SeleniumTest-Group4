import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import org.openqa.selenium.chrome.ChromeOptions;
import java.util.HashMap;
import java.util.Map;

public class ParabankTest {


    @Test
    public void testCreateAccount() throws InterruptedException {

        /*Activity 1*/
        WebDriver driver = new ChromeDriver();
        driver.get("https://parabank.parasoft.com/parabank/register.htm");

        //Get names of all input boxes
        WebElement firstName = driver.findElement(By.name("customer.firstName"));
        WebElement lastName = driver.findElement(By.name("customer.lastName"));
        WebElement address = driver.findElement(By.name("customer.address.street"));
        WebElement city = driver.findElement(By.name("customer.address.city"));
        WebElement state = driver.findElement(By.name("customer.address.state"));
        WebElement zip = driver.findElement(By.name("customer.address.zipCode"));
        WebElement phone = driver.findElement(By.name("customer.phoneNumber"));
        WebElement snn = driver.findElement(By.name("customer.ssn"));
        WebElement username = driver.findElement(By.name("customer.username"));
        WebElement password = driver.findElement(By.name("customer.password"));
        WebElement confirm = driver.findElement(By.name("repeatedPassword"));
        WebElement register = driver.findElement(By.cssSelector("input[type='submit'][value='Register']"));

        // Input fields
        firstName.sendKeys("John");
        lastName.sendKeys("Doe");
        address.sendKeys("123 Sample Street");
        city.sendKeys("Melbourne");
        state.sendKeys("Victoria");
        zip.sendKeys("3000");
        phone.sendKeys("1234567890");
        snn.sendKeys("123-45-6789");
        username.sendKeys("testusername");
        password.sendKeys("password");
        confirm.sendKeys("password");
        register.click();
        Thread.sleep(5000);
        driver.quit();
    }

    @Test
    public void testLogin() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.get("https://parabank.parasoft.com/parabank/index.htm");

        WebElement username = driver.findElement(By.name("username"));
        WebElement password = driver.findElement(By.name("password"));
        WebElement login = driver.findElement(By.cssSelector("input[type='submit'][value='Log In']"));

        username.sendKeys("testusername");
        password.sendKeys("password");

        login.click();

        Thread.sleep(5000);
    }

    @Test
    public void testLoginAndOpenAccount() throws InterruptedException {
        //hides chromes compromised password notification
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", prefs);
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--incognito");

        WebDriver driver = new ChromeDriver(options);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get("https://parabank.parasoft.com/parabank/index.htm");

        //login
        driver.findElement(By.name("username")).sendKeys("testusername");
        driver.findElement(By.name("password")).sendKeys("password");
        driver.findElement(By.cssSelector("input[type='submit'][value='Log In']")).click();

        //open new account page
        WebElement openAccount = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Open New Account")));
        openAccount.click();

        //select type
        WebElement dropdownElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("type")));
        Select typeDropdown = new Select(dropdownElement);
        typeDropdown.selectByVisibleText("CHECKING");

        //select account number
        WebElement fromAccountElement = driver.findElement(By.id("fromAccountId"));
        wait.until(ExpectedConditions.textToBePresentInElement(fromAccountElement, "13344"));
        Select fromAccountDropdown = new Select(fromAccountElement);
        fromAccountDropdown.selectByVisibleText("13344");

        //open account
        WebElement openAccountButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[value='Open New Account']")));
        openAccountButton.click();

        Thread.sleep(5000);
        driver.close();
        driver.quit();
    }
}