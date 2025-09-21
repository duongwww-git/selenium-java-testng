package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.regex.Pattern;

public class Topic_47_WaitP6ExplicitWait {
    WebDriver driver;
    WebDriverWait explicitWait;
    String uploadFilePath = System.getProperty("user.dir")+"//UploadFile//";
    String oneFile = "one.jpg";
    String twoFile = "two.jpeg";
    String threeFile = "three.jpeg";
    String fourFile = "four.jpg";

    String oneFilePath = uploadFilePath + oneFile;
    String twoFilePath = uploadFilePath + twoFile;
    String threeFilePath = uploadFilePath + threeFile;
    String fourFilePath = uploadFilePath + fourFile;


    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        //luôn khởi tạo sau khi driver được khởi tạo, vì cần truyền driver vào
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void TC_01() {

        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(3));
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(3), Duration.ofMillis(200));

        driver.get("https://automationfc.github.io/dynamic-loading/");

        explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div#start>button"))).click();

        Assert.assertTrue(driver.findElement(By.cssSelector("div#loading")).isDisplayed());

        //invisible
        Assert.assertTrue(explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#loading"))));

        //visible
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#finish>h4"))).click();
        Assert.assertEquals(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#finish>h4"))).getText(),"Hello World!");

    }

    @Test
    public void TC_02_AJAXLoader(){
        driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");

        //cho cho datepicker duoc hien thi
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#ctl00_ContentPlaceholder1_Panel1")));

        //wait text duoc hien thi
        explicitWait.until(ExpectedConditions.textToBe(By.cssSelector("span#ctl00_ContentPlaceholder1_Label1"),"No Selected Dates to display."));

        //wait for element clickable --> click
        explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//td/a[text()='18']"))).click();

        // Wait for loading Icon disappear
        Assert.assertTrue(explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(
                By.cssSelector("div:not([style='display:none;'])>div.raDiv"))));

        //Wait for text updated to page
        Assert.assertTrue(explicitWait.until(ExpectedConditions.textToBe(
                By.cssSelector("span#ctl00_ContentPlaceholder1_Label1"),"Thursday, September 18, 2025")));
    }

    @Test
    public void TC_03_GoFile() {
        driver.get("https://gofile.io/home");
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));

        explicitWait.until(ExpectedConditions.visibilityOfAllElements(
                driver.findElements(By.cssSelector("div.animate-spin"))));

        explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[type='file']"))).sendKeys(oneFilePath +"\n"+ twoFilePath +"\n"+ threeFilePath+"\n"+fourFilePath);

        Assert.assertTrue(explicitWait.until(ExpectedConditions.invisibilityOfAllElements(
                driver.findElements(By.cssSelector("div.file-progressbar")))));

        String uploadUrl = explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a.linkSuccessCard"))).getDomProperty("href");
        driver.get(uploadUrl);

    explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(@class,'tem_open') and text()='"+oneFile+"']")));
    explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(@class,'tem_open') and text()='"+twoFile+"']")));
    explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(@class,'tem_open') and text()='"+threeFile+"']")));
    explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(@class,'tem_open') and text()='"+fourFile+"']")));
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
