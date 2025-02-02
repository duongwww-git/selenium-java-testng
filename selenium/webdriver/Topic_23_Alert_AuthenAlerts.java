package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Topic_23_Alert_AuthenAlerts {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Test
    public void TC_01_acceptAlert() {
        driver.get("https://automationfc.github.io/basic-form/index.html");

        // scroll to element
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//legend[text()='JavaScript Alerts']")));

        //click
        driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();

        //wait for alert to appear - "present" --> then switch to alert
        Alert alert = new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.alertIsPresent());

        //neu khong dung wait:
        //Alert alert = driver.switchTo().alert();
            //accept
//        alert.accept();
//            //dismiss
//        alert.dismiss();
//            //gettext
//        alert.getText();
//            //sendkeys
//        alert.sendKeys();

        //verify text

        //verify text in alert
        Assert.assertEquals(alert.getText(),"I am a JS Alert");
        alert.accept();

        //verify text in result
        Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(),"You clicked an alert successfully");

    }

    @Test
    public void TC_02_confirmAlert() {
        driver.get("https://automationfc.github.io/basic-form/index.html");

        //click
        driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();

        //wait for alert to appear - "present" --> then switch to alert
        Alert alert = new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.alertIsPresent());

        Assert.assertEquals(alert.getText(),"I am a JS Confirm");

        alert.dismiss();

        //verify text in result
        Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(),"You clicked: Cancel");
    }

    @Test
    public void TC_03_promptAlert() {
        driver.get("https://automationfc.github.io/basic-form/index.html");

        //click
        driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();

        //wait for alert to appear - "present" --> then switch to alert
        Alert alert = new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.alertIsPresent());

        Assert.assertEquals(alert.getText(),"I am a JS prompt");

        alert.sendKeys("City of stars");

        alert.accept();

        //verify text in result
        Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(),"You entered: City of stars");
    }

    @Test
    public void TC_04_authenAlert() {
    String username = "admin";
    String password = "admin";

    //Opt1: sendkey U/P to URL directly
    driver.get("http://"+username+":"+password+"@"+"the-internet.herokuapp.com/basic_auth");

    Assert.assertEquals(driver.findElement(By.cssSelector("div.example p")),"Congratulations! You must have the proper credentials.");
    }

    @Test
    public void TC_05_authenAlert() {
        String username = "admin";
        String password = "admin";

        //Opt1: sendkey U/P to URL directly
        driver.get("http://the-internet.herokuapp.com");

        String authenLink = driver.findElement(By.xpath("//a[text()='Basic Auth']")).getDomProperty("href");
        System.out.println(authenLink);

        driver.get(passUserToAuthenLink(authenLink, username, password));

        Assert.assertEquals(driver.findElement(By.cssSelector("div.example p")),"Congratulations! You must have the proper credentials.");
    }

    public String passUserToAuthenLink(String authenLink, String username, String password) {
        String[] text = authenLink.split("//");
        return text[0] + "//" + username + ":" + password + "@" + text[1];
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
