package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_10_WebDriverCommands {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        // tuong tac voi browser thong qua driver
        driver = new FirefoxDriver();
    }

    @Test
    public void TC_01_Browser() {
        // mo ra mot URL bat ki
        driver.get("https://demo.nopcommerce.com/register?returnUrl=/");

        // dong browser
        driver.quit();

        // dong browser?
        driver.close();

        driver.findElement(By.cssSelector(""))

    }


    @AfterClass
    public void afterClass() {

        driver.quit();
    }
}
