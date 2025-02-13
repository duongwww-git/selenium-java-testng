package webdriver;

import graphql.AssertException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_24_Action {
    WebDriver driver;
    Actions action;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

        // truyen driver vao --> khoi tao doi tuong action
        action = new Actions(driver);
    }

    @Test
    public void TC_01_HoverJQuery() {
        driver.get("https://automationfc.github.io/jquery-tooltip/");
        action.moveToElement(driver.findElement(By.cssSelector("input#age"))).perform();
        action.pause(Duration.ofSeconds(3)).perform();

        Assert.assertEquals(driver.findElement(By.cssSelector("div.ui-tooltip-content")).getText(),"We ask for your age only for statistical purposes.");
    }

    @Test
    public void TC_02_HoverMyntra() {
        driver.get("https://www.myntra.com/");

        action.moveToElement(driver.findElement(By.xpath("//div[@class='desktop-navLink']/a[text()='Kids']"))).pause(Duration.ofSeconds(1)).perform();

        action.click(driver.findElement(By.xpath("//a[text()='Home & Bath']"))).perform();
        action.pause(Duration.ofSeconds(3)).perform();

        // dùng tạm verify URL vì dính captcha :(

        Assert.assertEquals(driver.getCurrentUrl(),"https://www.myntra.com/kids-home-bath");

    }

    @Test
    public void TC_03_HoverFahasa() throws InterruptedException {
        driver.get("https://www.fahasa.com/");

        action.moveToElement(driver.findElement(By.cssSelector("span.icon_menu"))).pause(Duration.ofSeconds(1)).perform();

        action.moveToElement(driver.findElement(By.xpath("//span[text()='VPP - Dụng Cụ Học Sinh']"))).pause(Duration.ofSeconds(1)).perform();

        action.click(driver.findElement(By.xpath("//div[contains(@class,'fhs_menu_content')]//a[text()='Gọt Bút Chì']"))).perform();

        Thread.sleep(5000);

        Assert.assertEquals(driver.findElement(By.cssSelector("ol.breadcrumb strong")).getText(),"GỌT BÚT CHÌ");


    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
