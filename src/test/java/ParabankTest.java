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
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class ParabankTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    public void setUp() {
        //initialise chrome with options
        //hides chromes compromised password notification
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", prefs);
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--incognito");

        driver = new ChromeDriver(options);

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }


    @Test
    public void testCreateValidAccount(){
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
    }

    @Test
    public void testCreateAccountDifferentPasswords(){
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
        confirm.sendKeys("differentpassword");
        register.click();

        WebElement passwordError = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("repeatedPassword.errors")));
        assertEquals("Passwords did not match.", passwordError.getText());
    }

    @Test
    public void testCreateExistingAccount() {
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
        username.sendKeys("john");
        password.sendKeys("password");
        confirm.sendKeys("password");
        register.click();

        //check fails as user already exists
        WebElement usernameError = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("customer.username.errors")));
        assertEquals("This username already exists.", usernameError.getText());
    }


    @Test
    public void testCreateAccountWithBlankFields() {
        driver.get("https://parabank.parasoft.com/parabank/register.htm");

        //Get names of register
        WebElement register = driver.findElement(By.cssSelector("input[type='submit'][value='Register']"));

        // click register
        register.click();

        //check fails as no fields are inputted
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("customer.firstName.errors")));
        assertEquals("First name is required.", driver.findElement(By.id("customer.firstName.errors")).getText());
        assertEquals("Last name is required.", driver.findElement(By.id("customer.lastName.errors")).getText());
        assertEquals("Address is required.", driver.findElement(By.id("customer.address.street.errors")).getText());
        assertEquals("City is required.", driver.findElement(By.id("customer.address.city.errors")).getText());
        assertEquals("State is required.", driver.findElement(By.id("customer.address.state.errors")).getText());
        assertEquals("Zip Code is required.", driver.findElement(By.id("customer.address.zipCode.errors")).getText());
        assertEquals("Social Security Number is required.", driver.findElement(By.id("customer.ssn.errors")).getText());
        assertEquals("Username is required.", driver.findElement(By.id("customer.username.errors")).getText());
        assertEquals("Password is required.", driver.findElement(By.id("customer.password.errors")).getText());
        assertEquals("Password confirmation is required.", driver.findElement(By.id("repeatedPassword.errors")).getText());
    }

    @Test
    public void testCreateAccountWithInvalidSSN() {
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
        snn.sendKeys("ABC-DE-FGHI");
        username.sendKeys("testuser1234");
        password.sendKeys("password");
        confirm.sendKeys("password");
        register.click();

        //check fails as ssn is invalid
        WebElement ssnError = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("customer.ssn.errors")));
        assertEquals("Please enter a valid SSN format.", ssnError.getText());
    }

    @Test
    public void testLogin(){
        driver.get("https://parabank.parasoft.com/parabank/index.htm");

        WebElement username = driver.findElement(By.name("username"));
        WebElement password = driver.findElement(By.name("password"));
        WebElement login = driver.findElement(By.cssSelector("input[type='submit'][value='Log In']"));

        //send user and password
        username.sendKeys("testusername");
        password.sendKeys("password");

        //click login
        login.click();

        //verify accounts can be seen
        WebElement overviewTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h1.title")));
        assertEquals("Accounts Overview", overviewTitle.getText());
    }

    @Test
    public void testIncorrectLogin(){
        driver.get("https://parabank.parasoft.com/parabank/index.htm");

        WebElement username = driver.findElement(By.name("username"));
        WebElement password = driver.findElement(By.name("password"));
        WebElement login = driver.findElement(By.cssSelector("input[type='submit'][value='Log In']"));

        //send user and password
        username.sendKeys("testuser");
        password.sendKeys("wrongPassword");

        //click login
        login.click();

        //verify fail occurs
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("p.error")));
        assertEquals("The username and password could not be verified.", errorMessage.getText());
    }

    @Test
    public void testInvalidUsername(){
        driver.get("https://parabank.parasoft.com/parabank/index.htm");

        WebElement username = driver.findElement(By.name("username"));
        WebElement password = driver.findElement(By.name("password"));
        WebElement login = driver.findElement(By.cssSelector("input[type='submit'][value='Log In']"));

        //send no user and password
        username.clear();
        password.sendKeys("password");

        //click login
        login.click();

        //verify fail occurs
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("p.error")));
        assertEquals("Please enter a username and password.", errorMessage.getText());
    }

    @Test
    public void testInvalidPassword(){
        driver.get("https://parabank.parasoft.com/parabank/index.htm");

        WebElement username = driver.findElement(By.name("username"));
        WebElement password = driver.findElement(By.name("password"));
        WebElement login = driver.findElement(By.cssSelector("input[type='submit'][value='Log In']"));

        //send user and no password
        username.sendKeys("testuser");
        password.clear();

        //click login
        login.click();

        //verify fail occurs
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("p.error")));
        assertEquals("Please enter a username and password.", errorMessage.getText());
    }

    @Test
    public void testLoginAndOpenCheckingAccount(){

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
        wait.until(ExpectedConditions.presenceOfNestedElementLocatedBy(By.id("fromAccountId"), By.tagName("option")));
        Select fromAccountDropdown = new Select(driver.findElement(By.id("fromAccountId")));
        fromAccountDropdown.selectByIndex(0);

        //open account
        WebElement openAccountButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[value='Open New Account']")));
        openAccountButton.click();

        //verify account has been created
        WebElement resultHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#openAccountResult .title")));
        assertEquals("Account Opened!", resultHeader.getText());

    }

    @Test
    public void testLoginAndOpenSavingAccount(){

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
        typeDropdown.selectByVisibleText("SAVINGS");

        //select account number
        wait.until(ExpectedConditions.presenceOfNestedElementLocatedBy(By.id("fromAccountId"), By.tagName("option")));
        Select fromAccountDropdown = new Select(driver.findElement(By.id("fromAccountId")));
        fromAccountDropdown.selectByIndex(0);

        //open account
        WebElement openAccountButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[value='Open New Account']")));
        openAccountButton.click();

        //verify account has been created
        WebElement resultHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#openAccountResult .title")));
        assertEquals("Account Opened!", resultHeader.getText());

    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}