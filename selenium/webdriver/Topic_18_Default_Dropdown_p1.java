package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.sql.Driver;
import java.time.Duration;
import java.util.Random;

//nopCommerce is currently skipped for tests on Mac due to can't pass captcha
public class Topic_18_Default_Dropdown_p1 {
    WebDriver driver;
    Select select;

    @BeforeClass
    public void beforeClass() {
        EdgeOptions edgeOptions = new EdgeOptions();
        edgeOptions.addArguments("--user-data-dir=/Users/duongwww/Library/Application Support/Microsoft Edge/");
        edgeOptions.addArguments("--profile-directory=Profile 1");
        driver = new EdgeDriver(edgeOptions);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();
    }

//    @Test
//    public void TC_01() throws InterruptedException {
//        driver.get("https://egov.danang.gov.vn/reg");
//
//        select = new Select(driver.findElement(By.cssSelector("select#thuongtru_tinhthanh")));
//
////        select.selectByIndex(4);
////        Thread.sleep(4000);
////
////        select.selectByValue(4091);
////        Thread.sleep(4000);
//
//        select.selectByVisibleText("tỉnh Bình Thuận");
//        Thread.sleep(4000);
//
//        // lay ra option vua chon (= first selected option) --> verify
//        Assert.assertEquals(select.getFirstSelectedOption().getText(), "tỉnh Bình Thuận");
//
//        // verify dropdown multiple/single
//        Assert.assertFalse(select.isMultiple());

//        // Lay ra tat ca cac item ben trong drop down quan/ huyen
//        select = new Select(driver.findElement(By.cssSelector("select#thuongtru_quanhuyen")));
//        List<WebElement> districtElements = select.getOptions();
//        List<String> districtText = new ArrayList<String>();
//
//        // lay ra list cac option
//        for (WebElement district: districtElements) {
//            districtText.add(district.getText());
//        }
//
//        // verify noi dung list cac options
//        Assert.assertTrue(districtText.contains("tỉnh Bình Thuận"));
//    }

//    @Test
//    public void TC_02() {
//        driver.get("https://demo.nopcommerce.com/register");
//
//        String firstName = "Joshua";
//        String lastName = "Joshua";
//        String emailAdress = "joshuajoshua" + new Random().nextInt(99999) + "@rakk.com";
//        String companyName = "rakk";
//        String password = "rakk202412";
//        String day = "8";
//        String month = "May";
//        String year = "1980";
//
//        driver.findElement(By.cssSelector("input#gender-male")).click();
//        driver.findElement(By.id("FirstName")).sendKeys(firstName);
//        driver.findElement(By.id("LastName")).sendKeys(lastName);
//        new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthDay']"))).selectByVisibleText(day);
//        new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthMonth']"))).selectByVisibleText(month);
//        new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthYear']"))).selectByVisibleText(year);
//
//        driver.findElement(By.id("Email")).sendKeys(emailAdress);
//        driver.findElement(By.id("Company")).sendKeys(companyName);
//        driver.findElement(By.id("Password")).sendKeys(password);
//        driver.findElement(By.id("ConfirmPassword")).sendKeys(password);
//
//        driver.findElement(By.id("register-button")).click();
//
//        Assert.assertEquals(driver.findElement(By.cssSelector("div.result")).getText(), "Your registration completed");
//
//        driver.findElement(By.cssSelector("a.ico-account")).click();
//
//        Assert.assertEquals(driver.findElement(By.id("FirstName")).getAttribute("value"), firstName);
//        Assert.assertEquals(driver.findElement(By.id("LastName")).getAttribute("value"), lastName);
//        Assert.assertEquals(driver.findElement(By.id("Email")).getAttribute("value"), emailAdress);
//        Assert.assertEquals(new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthDay']"))).getFirstSelectedOption().getText(), day);
//        Assert.assertEquals(new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthMonth']"))).getFirstSelectedOption().getText(), month);
//        Assert.assertEquals(new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthYear']"))).getFirstSelectedOption().getText(), year);
//
//    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
