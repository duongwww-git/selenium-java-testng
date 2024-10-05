package webdriver;

import com.sun.source.tree.AssertTree;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_06_Register {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Test
    public void Register_01_Empty_Data() {
        //Action 1
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
        //Action 2
        driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();
        //Assert 1
        Assert.assertEquals(driver.findElement(By.id("txtFirstname-error")).getText(),"Vui lòng nhập họ tên");
        Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(),"Vui lòng nhập email");
        Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(),"Vui lòng nhập lại địa chỉ email");
        Assert.assertEquals(driver.findElement(By.id("txtPassword-error")).getText(),"Vui lòng nhập mật khẩu");
        Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(),"Vui lòng nhập lại mật khẩu");
        Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(),"Vui lòng nhập số điện thoại.");
    }

    @Test
    public void Register_02_Invalid_Email() {
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");

        driver.findElement(By.name("txtFirstname")).sendKeys("Donald Trump");
        driver.findElement(By.name("txtEmail")).sendKeys("123@123..123");
        driver.findElement(By.name("txtCEmail")).sendKeys("123@123..123");
        driver.findElement(By.name("txtPassword")).sendKeys("1234567");
        driver.findElement(By.name("txtCPassword")).sendKeys("1234567");
        driver.findElement(By.name("txtPhone")).sendKeys("0974729124");

        driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();

        Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(),"Vui lòng nhập email hợp lệ");
        Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(),"Vui lòng nhập lại email hợp lệ");
    }

    @Test
    public void Register_03_Incorrect_Confirm_Email() {
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");

        driver.findElement(By.name("txtFirstname")).sendKeys("Donald Trump");
        driver.findElement(By.name("txtEmail")).sendKeys("donald@trump.co");
        driver.findElement(By.name("txtCEmail")).sendKeys("donald@trump.com");
        driver.findElement(By.name("txtPassword")).sendKeys("1234567");
        driver.findElement(By.name("txtCPassword")).sendKeys("1234567");
        driver.findElement(By.name("txtPhone")).sendKeys("0974729124");

        driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();

        Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(),"Email nhập lại không đúng");

    }

    @Test
    public void Register_04_Invalid_Password() {
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");

        driver.findElement(By.name("txtFirstname")).sendKeys("Donald Trump");
        driver.findElement(By.name("txtEmail")).sendKeys("donald@trump.co");
        driver.findElement(By.name("txtCEmail")).sendKeys("donald@trump.co");
        driver.findElement(By.name("txtPassword")).sendKeys("123");
        driver.findElement(By.name("txtCPassword")).sendKeys("123");
        driver.findElement(By.name("txtPhone")).sendKeys("0974729124");

        driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();

        Assert.assertEquals(driver.findElement(By.id("txtPassword-error")).getText(),"Mật khẩu phải có ít nhất 6 ký tự");
        Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(),"Mật khẩu phải có ít nhất 6 ký tự");

    }

    @Test
    public void Register_05_Incorrect_Confirm_Password() {
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");

        driver.findElement(By.name("txtFirstname")).sendKeys("Donald Trump");
        driver.findElement(By.name("txtEmail")).sendKeys("donald@trump.co");
        driver.findElement(By.name("txtCEmail")).sendKeys("donald@trump.co");
        driver.findElement(By.name("txtPassword")).sendKeys("123");
        driver.findElement(By.name("txtCPassword")).sendKeys("1234");
        driver.findElement(By.name("txtPhone")).sendKeys("0974729124");

        driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();

        Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(),"Mật khẩu phải có ít nhất 6 ký tự");

    }

    @Test
    public void Register_06_Invalid_Phone_Number() {
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
        // Case 1 - less than 10 char
        driver.findElement(By.name("txtFirstname")).sendKeys("Donald Trump");
        driver.findElement(By.name("txtEmail")).sendKeys("donald@trump.co");
        driver.findElement(By.name("txtCEmail")).sendKeys("donald@trump.co");
        driver.findElement(By.name("txtPassword")).sendKeys("1234567");
        driver.findElement(By.name("txtCPassword")).sendKeys("1234567");
        driver.findElement(By.name("txtPhone")).sendKeys("09747291");

        driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();

        Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(),"Số điện thoại phải từ 10-11 số.");

        //Case 2 - More than 11 char
        driver.findElement(By.name("txtPhone")).clear();
        driver.findElement(By.name("txtPhone")).sendKeys("0974729134555");

        driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();

        Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(),"Số điện thoại phải từ 10-11 số.");

        //Case 3 - Contains text
        driver.findElement(By.name("txtPhone")).clear();
        driver.findElement(By.name("txtPhone")).sendKeys("09747291e4");

        driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();

        Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(),"Vui lòng nhập con số");

        //Case 4 - Not start with 0
        driver.findElement(By.name("txtPhone")).clear();
        driver.findElement(By.name("txtPhone")).sendKeys("9974729134");

        driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();

        Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(),"Số điện thoại bắt đầu bằng: 09 - 03 - 012 - 016 - 018 - 019 - 088 - 03 - 05 - 07 - 08");

    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
