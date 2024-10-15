package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_08_XpathCSS {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Test
    public void TC_01() {
        driver.get("https://live.techpanda.org/index.php/customer/account/login/");
        driver.findElement(By.cssSelector("input[title$='Address']"));
    }


    @Test
    public void TC_02() {

    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
