package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.Colors;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.awt.*;
import java.time.Duration;

public class Topic_20_Button {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Test
    public void TC_01_Button() throws InterruptedException {
        driver.get("https://www.fahasa.com/customer/account/create");
//        By registerButton = By.cssSelector("button.fhs-btn-register");
//
////        0 - Clickable
////      Wait for an element unclickable in 10s
//        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.not(ExpectedConditions.elementToBeClickable(registerButton)));
//
////        0 - Text hiển thị đúng
//        Assert.assertEquals(driver.findElement(registerButton).getText().trim(),"Đăng ký");
//
////        0 - Background màu gì
//        String backgroundColor = driver.findElement(registerButton).getCssValue("background-color");
//        Assert.assertEquals(backgroundColor,"rgba(0, 0, 0, 0)");
////      Convert qua hexa
//        Assert.assertEquals(Color.fromString(backgroundColor).asHex().toUpperCase(),"#000000");

        driver.findElement(By.cssSelector("li.popup-login-tab-login")).click();
        By loginButton = By.cssSelector("button.fhs-btn-login");

//      verified button disabled
        Assert.assertFalse(driver.findElement(loginButton).isEnabled());

//      verift cikir
        Assert.assertEquals(Color.fromString(driver.findElement(loginButton).getCssValue("background-color")).asHex().toUpperCase(),"#000000");

//      input data
        driver.findElement(By.cssSelector("input#login_username")).sendKeys("autofc@autofc.co");
        driver.findElement(By.cssSelector("input#login_password")).sendKeys("autofc123");

//      verify button disabled
        Thread.sleep(2000);
        Assert.assertTrue(driver.findElement(loginButton).isEnabled());

        Assert.assertEquals(Color.fromString(driver.findElement(loginButton).getCssValue("background-color")).asHex().toUpperCase(),"#C92127");

//      4 - DIsabled/Enabled
//      expect the element is enabled --> verify = assertTrue
        Assert.assertTrue(driver.findElement(loginButton).isEnabled());
    }

    @Test
    public void TC_02() {

    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
