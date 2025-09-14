package webdriver;

import graphql.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Topic_44_UploadFile {
    WebDriver driver;
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
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Test
    public void TC_01_SingleFile() throws InterruptedException {
        //file để ở đâu?
            // trên máy? --> qua máy khác ko tìm thấy path thì sao?
            // ==> để file cần upload trong source code --> lấy relative path
        driver.get("https://blueimp.github.io/jQuery-File-Upload/");

        //load files
        By uploadBy = By.cssSelector("input[type='file']");

        driver.findElement(uploadBy).sendKeys(oneFilePath);
        driver.findElement(uploadBy).sendKeys(twoFilePath);
        driver.findElement(uploadBy).sendKeys(threeFilePath);
        driver.findElement(uploadBy).sendKeys(fourFilePath);

        //verify file loads successfully
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='"+oneFile+"']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='"+twoFile+"']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='"+threeFile+"']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='"+fourFile+"']")).isDisplayed());

        //upload file
        List<WebElement> startUploadButtons = driver.findElements(By.cssSelector("table button.start"));
        for (WebElement startButton: startUploadButtons){
            startButton.click();
            Thread.sleep(500);
        }

        //Verify upload file
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='"+oneFile+"']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='"+twoFile+"']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='"+threeFile+"']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='"+fourFile+"']")).isDisplayed());
        Thread.sleep(4000);
    }

    @Test
    public void TC_02_MultipleFile() throws InterruptedException {
        //tuỳ vào element có attribute multiple không
        driver.get("https://blueimp.github.io/jQuery-File-Upload/");

        //load files
        By uploadBy = By.cssSelector("input[type='file']");

        driver.findElement(uploadBy).sendKeys(oneFilePath +"\n"+ twoFilePath +"\n"+ threeFilePath+"\n"+fourFilePath);

        //verify file loads successfully
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='"+oneFile+"']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='"+twoFile+"']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='"+threeFile+"']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='"+fourFile+"']")).isDisplayed());

        //upload file
        List<WebElement> startUploadButtons = driver.findElements(By.cssSelector("table button.start"));
        for (WebElement startButton: startUploadButtons){
            startButton.click();
            Thread.sleep(500);
        }

        //Verify upload file
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='"+oneFile+"']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='"+twoFile+"']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='"+threeFile+"']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='"+fourFile+"']")).isDisplayed());
        Thread.sleep(4000);
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
