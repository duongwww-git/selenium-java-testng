package tips;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_01_CloudFlare {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        EdgeOptions edgeOptions = new EdgeOptions();
        edgeOptions.addArguments("--user-data-dir=/Users/duongwww/Library/Application Support/Microsoft Edge/");
        edgeOptions.addArguments("--profile-directory=Profile 1");
        driver = new EdgeDriver(edgeOptions);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Test
    public void TC_01_NopCommerce () throws InterruptedException {
        driver.get("https://demo.nopcommerce.com/");;

        driver.findElement(By.cssSelector("a.ico-register")).click();

        Thread.sleep(4000);
    }

    @AfterClass
    public void AfterClass () {
        driver.quit();
    }
}
