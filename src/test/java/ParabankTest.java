import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParabankTest {


    @Test
    public void openParabank() throws InterruptedException {

        /*Activity 1*/
        WebDriver driver = new FirefoxDriver();
        driver.get("https://parabank.parasoft.com/parabank/index.html");

        driver.quit();
    }
}
