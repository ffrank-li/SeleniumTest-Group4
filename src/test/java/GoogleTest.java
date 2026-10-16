import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class GoogleTest {


    @Test
    public void openGoogle() throws InterruptedException {

        /*Activity 1*/
//        WebDriver driver = new ChromeDriver();
        WebDriver driver = new FirefoxDriver();
        driver.get("https://www.google.com");


        /*Activity 2*/
        // Find the search box
        WebElement searchBox = driver.findElement(By.name("q"));

        // Enter search term and press Enter
        searchBox.sendKeys("cheese");
        searchBox.sendKeys(Keys.ENTER);

        // Verify that the results page contains "cheese"
        String pageText = driver.findElement(By.tagName("body")).getText();

        assertTrue(pageText.toLowerCase().contains("cheese"));

        Thread.sleep(5000);
        driver.quit();

    }
}
