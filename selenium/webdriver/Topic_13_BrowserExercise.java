package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_13_BrowserExercise {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Test
    public void TC_01_Verify_URL() {
        driver.get("https://live.techpanda.org/");

        // Click vao My Account tai footer
        driver.findElement(By.cssSelector("div.footer a[title='My Account']")).click();

        // Cach 1 - Lay URL page hien tai --> luu vao 1 bien --> verify voi assert (dung bien 2 lan tro len)
//        String loginPageUrl = driver.getCurrentUrl();
//        Assert.assertEquals(loginPageUrl,"https://live.techpanda.org/index.php/customer/account/login/");

        // Cach 2 - Lay URL page hien tai va check truc tiep (dung 1 lan)
        Assert.assertEquals(driver.getCurrentUrl(),"https://live.techpanda.org/index.php/customer/account/login/");

        // click chuyen qua trang register
        driver.findElement(By.xpath("//a[@title='Create an Account']")).click();

        Assert.assertEquals(driver.getCurrentUrl(),"https://live.techpanda.org/index.php/customer/account/create/");

    }

    @Test
    public void TC_02_VerifyTitle() {
        driver.get("https://live.techpanda.org/");

        // Click vao My Account tai footer
        driver.findElement(By.cssSelector("div.footer a[title='My Account']")).click();

        // Cach 2 - Lay Title page hien tai va check truc tiep (dung 1 lan)
        Assert.assertEquals(driver.getTitle(),"Customer Login");

        // click chuyen qua trang register
        driver.findElement(By.xpath("//a[@title='Create an Account']")).click();

        Assert.assertEquals(driver.getTitle(),"Create New Customer Account");

    }

    @Test
    public void TC_03_Navigation() {
        driver.get("https://live.techpanda.org/");

        // Click vao My Account tai footer
        driver.findElement(By.cssSelector("div.footer a[title='My Account']")).click();

        // click chuyen qua trang register
        driver.findElement(By.xpath("//a[@title='Create an Account']")).click();

        Assert.assertEquals(driver.getCurrentUrl(),"https://live.techpanda.org/index.php/customer/account/create/");

        // Quay lai trang truoc do
        driver.navigate().back();

        Assert.assertEquals(driver.getCurrentUrl(),"https://live.techpanda.org/index.php/customer/account/login/");

        // Chuyen tiep toi trang reg
        driver.navigate().forward();

        Assert.assertEquals(driver.getTitle(),"Create New Customer Account");

    }

    @Test
    public void TC_04_Page_Source() {
        driver.get("https://live.techpanda.org/");

        // Click vao My Account tai footer
        driver.findElement(By.cssSelector("div.footer a[title='My Account']")).click();

        // Tuyet doi --> equal = bang nhau
        Assert.assertEquals(driver.getTitle(), "Customer Login");

        // Tuong doi --> assert True/ False
        Assert.assertTrue(driver.getPageSource().contains("Login or Create an Account"));

        // click chuyen qua trang register
        driver.findElement(By.xpath("//a[@title='Create an Account']")).click();

        Assert.assertTrue(driver.getPageSource().contains("Create an Account"));

    }
    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
