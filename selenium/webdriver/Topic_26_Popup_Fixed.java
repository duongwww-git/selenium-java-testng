package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_26_Popup_Fixed {
    WebDriver driver;
    Actions action;
    JavascriptExecutor jsExecutor;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();

        // truyen driver vao --> khoi tao doi tuong action
        action = new Actions(driver);
        jsExecutor = (JavascriptExecutor) driver;
    }

    @Test
    public void TC_01_Ngoangu24h_Fixed() throws InterruptedException {
        driver.get("https://ngoaingu24h.vn/");

        Thread.sleep(2000);

        driver.findElement(By.xpath("//button[text()='Đăng nhập']")).click();

        //check popup isdisplayed
        By loginPopup = By.xpath("//div[@role='dialog']");

        //kiem tra popup hien thi
        Assert.assertTrue(driver.findElement(loginPopup).isDisplayed());

        driver.findElement(By.xpath("//input[@autocomplete='username']")).sendKeys("automationfc");
        driver.findElement(By.xpath("//input[@autocomplete='new-password']")).sendKeys("automationfc");
        driver.findElement(By.xpath("//div[@id='custom-dialog']//button[text()='Đăng nhập']")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("div#notistack-snackbar")).getText(), "Bạn đã nhập sai tài khoản hoặc mật khẩu!");

        //close popup
        driver.findElement(By.cssSelector("div#custom-dialog h2 button")).click();
        Thread.sleep(2000);

        //kiem tra popup khong hien thi = findElements()
        //Assert.assertFalse(driver.findElement(loginPopup).isDisplayed());
        Assert.assertEquals((driver.findElements(loginPopup).size()), 0);
    }

    @Test
    public void TC_01b_Tiki_Fixed() throws InterruptedException {
        driver.get("https://tiki.vn/");

        driver.findElement(By.cssSelector("img[alt='close-icon']")).click();

        driver.findElement(By.cssSelector("div[data-view-id='header_header_account_container']")).click();
        Thread.sleep(2000);

        By loginPopup = By.cssSelector("div.ReactModalPortal div[role='dialog']");

        //ktra popup hien thi
        Assert.assertTrue(driver.findElement(loginPopup).isDisplayed());

        driver.findElement(By.cssSelector("button.btn-close")).click();
        Thread.sleep(2000);

        //Assert.assertFalse(driver.findElement(loginPopup).isDisplayed());
        Assert.assertEquals((driver.findElements(loginPopup).size()), 0);

    }


    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
