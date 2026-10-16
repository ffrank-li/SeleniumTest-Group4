import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

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
        WebElement login = driver.findElement(By.cssSelector("input[type='submit'][value='Log in']"));

        username.sendKeys("testusername");
        password.sendKeys("password");

        login.click();

        Thread.sleep(5000);
    }
}
