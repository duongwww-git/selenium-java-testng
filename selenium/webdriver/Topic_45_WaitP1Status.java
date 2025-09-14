package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.time.Duration;
import java.util.Date;

public class Topic_45_WaitP1Status {
    WebDriver driver;
    WebDriverWait explicitWait;
    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));

        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void TC_01_Visible() {
        driver.get("https://live.techpanda.org/index.php/customer/account/login/");

        driver.findElement(By.cssSelector("button#send2")).click();

        // Dieu kien 1 - Element hien thi UI va co trong DOM
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#advice-required-entry-email")));
    }

    @Test
    public void TC_02_Invisible_In_HTML() {
        driver.get("https://live.techpanda.org/index.php/customer/account/login/");

        driver.findElement(By.cssSelector("button#send2")).click();

        driver.findElement(By.cssSelector("input#email")).sendKeys("jojoj@gmail.com");

        driver.findElement(By.cssSelector("button#send2")).click();

        System.out.println("Start wait at: "+ getDateTimeNow());
        //Dieu kien 2 - Element ko hien thi tren UI va co trong DOM
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#advice-required-entry-email")));
        System.out.println("Stop wait at: "+ getDateTimeNow());
    }

    @Test
    public void TC_02a_Invisible_Not_In_HTML() {
        driver.get("https://live.techpanda.org/index.php/customer/account/login/");

        driver.findElement(By.cssSelector("input#email")).sendKeys("jojoj@gmail.com");

        driver.findElement(By.cssSelector("button#send2")).click();

        System.out.println("Start wait at: "+ getDateTimeNow());
        //Dieu kien 3 - Element ko hien thi tren UI va KO co trong DOM
        //neu element ko co trong HTML --> implicit wait se tim lai den khi co element/expire
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#advice-required-entry-email")));
        System.out.println("Stop wait at: "+ getDateTimeNow());
    }

    @Test
    public void TC_03_Presence() {
        driver.get("https://live.techpanda.org/index.php/customer/account/login/");

        driver.findElement(By.cssSelector("button#send2")).click();

        // Dieu kien 1 - Element hien thi UI va co trong DOM
        explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div#advice-required-entry-email")));

        //Dieu kien 2 - Element ko hien thi tren UI va co trong DOM
        driver.findElement(By.cssSelector("input#email")).sendKeys("jojoj@gmail.com");
        driver.findElement(By.cssSelector("button#send2")).click();

        explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div#advice-required-entry-email")));
    }

    @Test
    public void TC_04_Staleness() {
        driver.get("https://live.techpanda.org/index.php/customer/account/login/");
        driver.findElement(By.cssSelector("button#send2")).click();

        WebElement emailErrorMsg = driver.findElement(By.cssSelector("div#advice-required-entry-email"));

        driver.navigate().refresh();

        //wait for element no longer attached to the DOM --> test thay doi trang thai
        explicitWait.until(ExpectedConditions.stalenessOf(emailErrorMsg));
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    private String getDateTimeNow() {
        return new Date().toString();
    }
}
