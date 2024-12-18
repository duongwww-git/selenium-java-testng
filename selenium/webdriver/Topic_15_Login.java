package webdriver;



import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.time.Duration;
import java.util.function.Consumer;

public class Topic_15_Login {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Test
    public void TC_01_Empty() {
        driver.get("https://live.techpanda.org/index.php");

        driver.findElement(By.cssSelector("div.footer a[title='My Account']")).click();
        driver.findElement(By.cssSelector("button#send2")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("div#advice-required-entry-email")).getText(),"This is a required field.");
        Assert.assertEquals(driver.findElement(By.cssSelector("div#advice-required-entry-pass")).getText(),"This is a required field.");

    }

    @Test
    public void TC_02_InvalidEmail() {
        driver.get("https://live.techpanda.org/index.php");

        driver.findElement(By.cssSelector("div.footer a[title='My Account']")).click();

        driver.findElement(By.cssSelector("input#email")).sendKeys("123@123.123");
        driver.findElement(By.cssSelector("input#pass")).sendKeys("123456");

        driver.findElement(By.cssSelector("button#send2")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("div#advice-validate-email-email")).getText(),"Please enter a valid email address. For example johndoe@domain.com.");


    }

    @Test
    public void TC_03_InvalidPassword() {
        driver.get("https://live.techpanda.org/index.php");

        driver.findElement(By.cssSelector("div.footer a[title='My Account']")).click();

        driver.findElement(By.cssSelector("input#email")).sendKeys("john@doe.com");
        driver.findElement(By.cssSelector("input#pass")).sendKeys("123");

        driver.findElement(By.cssSelector("button#send2")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("div#advice-validate-password-pass")).getText(),"Please enter 6 or more characters without leading or trailing spaces.");

    }

//    @Test
//    public void TC_04_IncorrectEmailPassword() {
//  chạy https đang bị vướng alert nên không proceed được. chưa tìm được cách bắt alert và tương tác --> tạm thời đổi sang truy cập với http
//        driver.get("http://live.techpanda.org/index.php");
//
//        driver.findElement(By.cssSelector("div.footer a[title='My Account']")).click();
//
//        driver.findElement(By.cssSelector("input#email")).sendKeys("john@doe.com");
//        driver.findElement(By.cssSelector("input#pass")).sendKeys("123123");
//
//        driver.findElement(By.cssSelector("button#send2")).click();
//        Assert.assertEquals(driver.findElement(By.cssSelector("li.error-msg span")).getText(),"Invalid login or password.");
//
//    }

    @Test
    public void TC_05_IncorrectEmailPasswordhttps() throws InterruptedException {
// giải quyết vấn đề alert, bản cũ (TC04) đặt chưa đúng trình tự nên chưa bắt được alert

        driver.get("https://live.techpanda.org/index.php");

        driver.findElement(By.cssSelector("div.footer a[title='My Account']")).click();

        driver.findElement(By.cssSelector("input#email")).sendKeys("john@doe.com");
        driver.findElement(By.cssSelector("input#pass")).sendKeys("123123");

        driver.findElement(By.cssSelector("button#send2")).click();

        // Switch to the alert
        Alert alert = driver.switchTo().alert();

        // Accept the alert (click OK)
        alert.accept();

        Thread.sleep(2000);

        Assert.assertEquals(driver.findElement(By.cssSelector("li.error-msg span")).getText(),"Invalid login or password.");

    }


    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
