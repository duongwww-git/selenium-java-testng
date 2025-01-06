package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;

public class Topic_17_TextBox_TextArea_p2 {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Test
    public void TC_02_OrangeHRM() throws InterruptedException {
        String firstName = "Donald";
        String lastName = "Trump";
        String userName = "donald" + new Random().nextInt(99999);
        String passWord = "Dummyw@02";
        String passportNumber = "555-655-777-8888";
        String passportComment = "Automation FC\n Best Tour";

        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        driver.findElement(By.cssSelector("input[name='username']")).sendKeys("Admin");
        driver.findElement(By.cssSelector("input[name='password']")).sendKeys("admin123");
        driver.findElement(By.cssSelector("button.orangehrm-login-button")).click();

        //handle loading icon (ajax loading) - con icon thi data chua hien thi het, chua ready cho interaction
        //tam thoi co the su dung hard sleep
        //hard sleep phai co exception

        Thread.sleep(4000);

        driver.findElement(By.xpath("//span[text()='PIM']")).click();

        Thread.sleep(4000);

        //add employee
        driver.findElement(By.xpath("//a[text()='Add Employee']")).click();
        Thread.sleep(4000);

        driver.findElement(By.cssSelector("input[name='firstName']")).sendKeys(firstName);
        driver.findElement(By.cssSelector("input[name='lastName']")).sendKeys(lastName);

        String employeeID = driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).getAttribute("value");
        driver.findElement(By.xpath("//p[text()='Create Login Details']/following-sibling::div//label")).click();
        Thread.sleep(2000);

        driver.findElement(By.xpath("//label[text()='Username']//parent::div//following-sibling::div//input")).sendKeys(userName);
        driver.findElement(By.xpath("//label[text()='Password']//parent::div//following-sibling::div//input")).sendKeys(passWord);
        driver.findElement(By.xpath("//label[text()='Confirm Password']//parent::div//following-sibling::div//input")).sendKeys(passWord);

        driver.findElement(By.xpath("//button[contains(string(),' Save ')]")).click();
        Thread.sleep(8000);

        //verify
        Assert.assertEquals(driver.findElement(By.cssSelector("input[name='firstName']")).getAttribute("value"), firstName);
        Assert.assertEquals(driver.findElement(By.cssSelector("input[name='lastName']")).getAttribute("value"), lastName);
        Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).getAttribute("value"), employeeID);
        Assert.assertTrue(driver.findElement(
                By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).isEnabled());

        //add immigration
        driver.findElement(By.xpath("//a[text()='Immigration']")).click();
        Thread.sleep(2000);

        driver.findElement(By.xpath("//h6[text()='Assigned Immigration Records']//following-sibling::button[contains(string(),'Add')]")).click();
        driver.findElement(By.xpath("//label[text()='Number']/parent::div/following-sibling::div/input")).sendKeys(passportNumber);
        driver.findElement(By.xpath("//label[text()='Comments']/parent::div/following-sibling::div/textarea")).sendKeys(passportComment);

        // save
        driver.findElement(By.xpath("//button[contains(string(),' Save ')]")).click();
        Thread.sleep(3000);


        driver.findElement(By.cssSelector("i.bi-pencil-fill")).click();
        Thread.sleep(2000);

        //verify 2
        Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Number']/parent::div/following-sibling::div/input")).getAttribute("value"), passportNumber);
        Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Comments']/parent::div/following-sibling::div/textarea")).getAttribute("value"), passportComment);

        //log out
        driver.findElement(By.cssSelector("p.oxd-userdropdown-name")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//a[text()='Logout']")).click();

        //re-login with created account
        driver.findElement(By.cssSelector("input[name='username']")).sendKeys(userName);
        driver.findElement(By.cssSelector("input[name='password']")).sendKeys(passWord);
        driver.findElement(By.cssSelector("button.orangehrm-login-button")).click();

        driver.findElement(By.xpath("//span[text()='My Info']")).click();
        Thread.sleep(3000);

        Assert.assertEquals(driver.findElement(By.cssSelector("input[name='firstName']")).getAttribute("value"), firstName);
        Assert.assertEquals(driver.findElement(By.cssSelector("input[name='lastName']")).getAttribute("value"), lastName);
        Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).getAttribute("value"), employeeID);
        Assert.assertFalse(driver.findElement(
                By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).isEnabled());

        driver.findElement(By.xpath("//a[text()='Immigration']")).click();
        Thread.sleep(2000);

        driver.findElement(By.cssSelector("i.bi-pencil-fill")).click();
        Thread.sleep(2000);

        Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Number']/parent::div/following-sibling::div/input")).getAttribute("value"), passportNumber);
        Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Comments']/parent::div/following-sibling::div/textarea")).getAttribute("value"), passportComment);

    }



    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
