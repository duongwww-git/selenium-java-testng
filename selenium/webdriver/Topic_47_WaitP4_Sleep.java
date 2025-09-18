package webdriver;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_47_WaitP4_Sleep {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

    }

    @Test
    public void TC_01_NoImplicitWait() {

        driver.get("https://automationfc.github.io/dynamic-loading/");

//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

        driver.findElement(By.cssSelector("div#start>button")).click();

        Assert.assertTrue(driver.findElement(By.cssSelector("div#loading")).isDisplayed());

        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(),"Hello World!");
    }

    @Test
    public void TC_02_Less() throws InterruptedException {

        driver.get("https://automationfc.github.io/dynamic-loading/");

        driver.findElement(By.cssSelector("div#start>button")).click();

        Assert.assertTrue(driver.findElement(By.cssSelector("div#loading")).isDisplayed());

        Thread.sleep(3000);

        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(),"Hello World!");
    }

    @Test
    public void TC_03_Equal() throws InterruptedException {

        driver.get("https://automationfc.github.io/dynamic-loading/");

        driver.findElement(By.cssSelector("div#start>button")).click();

        Assert.assertTrue(driver.findElement(By.cssSelector("div#loading")).isDisplayed());

        Thread.sleep(5000);

        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(),"Hello World!");
    }

    @Test
    public void TC_04_More() throws InterruptedException {

        driver.get("https://automationfc.github.io/dynamic-loading/");


        driver.findElement(By.cssSelector("div#start>button")).click();

        Assert.assertTrue(driver.findElement(By.cssSelector("div#loading")).isDisplayed());

        Thread.sleep(Duration.ofSeconds(10));

        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(),"Hello World!");
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
