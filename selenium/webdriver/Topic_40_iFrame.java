package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chromium.ChromiumDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.Select;

import java.sql.Driver;
import java.time.Duration;

public class Topic_40_iFrame {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
//        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_wordPress() throws InterruptedException {
        driver.get("https://toidicodedao.com/");

//        //scroll to element
//        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.cssSelector("iframe[title*='Facebook Social Plugin']")));
//        Thread.sleep(500);

        Thread.sleep(3000);

        //switch to iframe
        driver.switchTo().frame(driver.findElement(By.cssSelector("iframe[title*='Facebook Social Plugin']")));

        //element in iframe
        Assert.assertEquals(driver.findElement(By.xpath("//a[text()='Tôi đi code dạo']/parent::div/following-sibling::div"))
                .getText(),"395,777 followers");

        //switch back
        driver.switchTo().defaultContent();

        driver.findElement(By.cssSelector("div#content-sidebar input[class= 'search-field']")).sendKeys("Puppeteer");
        driver.findElement(By.cssSelector("div#content-sidebar input[class= 'search-field']")).sendKeys(Keys.ENTER);
    }

    @Test
    public void TC_02() throws InterruptedException {
        driver.get("https://www.formsite.com/templates/education/campus-safety-survey/");

        //#1 click on img
        driver.findElement(By.cssSelector("#imageTemplateContainer>img")).click();

        //#2 switch to frame
        driver.switchTo().frame(driver.findElement(By.cssSelector("div#formTemplateContainer > iframe")));

        //#3 select & submit
        new Select(driver.findElement(By.cssSelector("select#RESULT_RadioButton-2"))).selectByVisibleText("Freshman");
        new Select(driver.findElement(By.cssSelector("select#RESULT_RadioButton-3"))).selectByVisibleText("North Dorm");
        driver.findElement(By.xpath("//label[text()='Female']")).click();
//        driver.findElement(By.cssSelector("input#FSsubmit")).click();

        //switch back
        driver.switchTo().defaultContent();

        //#5 get this form
        driver.findElement(By.xpath("//a[@title='Get this form']")).click();
        Thread.sleep(2000);
        //#6 log in
        driver.findElement(By.xpath("//button[text()='Log in']")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//button[text()='Log in']")).click();
        Thread.sleep(2000);
        Assert.assertEquals(driver.findElement(By.cssSelector("div#message-error")).getText(),"Username and password are both required.");
    }

    @Test
    public void TC_03_Frame() throws InterruptedException{
        driver.get("https://netbanking.hdfcbank.com/netbanking/");

        driver.switchTo().frame("login_page");

        driver.findElement(By.xpath("//div[text()='Customer ID/ User ID']/following-sibling::div/input")).sendKeys("Rando");

        driver.findElement(By.xpath("//a[text()='CONTINUE']")).click();
        Thread.sleep(2000);

        driver.switchTo().defaultContent();

        Assert.assertTrue(driver.findElement(By.xpath("//label[text()=\"Password/IPIN\"]")).isDisplayed());
//        driver.findElement(By.cssSelector("input#liabiltyLoginCustId")).sendKeys("Rando");
        driver.findElement(By.cssSelector("input#keyboard")).sendKeys("Rando");
        driver.findElement(By.cssSelector("a#loginBtn")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("p.error-msg")).getText(),"Customer ID/IPIN (Password) is invalid. Please try again.");
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
