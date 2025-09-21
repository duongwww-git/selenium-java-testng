package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Date;

public class Topic_45_WaitP6_Mix {
    WebDriver driver;
    WebDriverWait explicitWait;
    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();

    }

    @Test
    public void TC_01_Element_Found() {

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get("https://demo.nopcommerce.com/");

        System.out.println("start time: "+getDateTimeNow());
        driver.findElement(By.cssSelector("input#small-searchterms")).click();
        System.out.println("end time: "+getDateTimeNow());

        System.out.println("start time: "+getDateTimeNow());
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#small-searchterms")));
        System.out.println("end time: "+getDateTimeNow());
    }

    @Test
    public void TC_02_Element_Not_Found_OnlyImplicit() {

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get("https://demo.nopcommerce.com/");
        System.out.println("start time: "+getDateTimeNow());
        try {
            driver.findElement(By.cssSelector("input#small")).click();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            System.out.println("end time: "+getDateTimeNow());
        }
    }

    @Test
    public void TC_03_Element_Not_Found_OnlyExplicit() {

//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get("https://demo.nopcommerce.com/");
        driver.findElement(By.cssSelector("input#small-searchterms")).click();
        System.out.println("start time: "+getDateTimeNow());
        try {
            explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#small")));
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            System.out.println("end time: "+getDateTimeNow());
        }
    }

    @Test //equal/less than/more than
    public void TC_04_Element_Not_Found_ExplicitImplicit() {

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); //implicit anh huong cac ham explicit  | chay truoc 0.5s so voi explicit
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(5)); //explicit KHONG anh huong cac ham implicit

        driver.get("https://demo.nopcommerce.com/");

        System.out.println("start time: "+getDateTimeNow());
        try {
            explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#small"))); //loi tra ve (5s) khong tuong ung voi thoi gian doi (11s) --> confusing
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            System.out.println("end time: "+getDateTimeNow());
        }
    }

    @Test
    public void TC_05_Element_Not_Found_ExplicitImplicit() {

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3)); //implicit anh huong cac ham explicit  | chay truoc 0.5s so voi explicit
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10)); //explicit KHONG anh huong cac ham implicit

        driver.get("https://demo.nopcommerce.com/");

        System.out.println("start time: "+getDateTimeNow());
        try {
            WebElement notFound = driver.findElement(By.cssSelector("input#small"));
            explicitWait.until(ExpectedConditions.visibilityOf(notFound)); //driver.findElement duoc chay truoc nen se fail implicit truoc khi chay explicit --> nhung ham Explicit expect element thay vi By --> ap dung implicit
            explicitWait.until(ExpectedConditions.elementToBeClickable(notFound));
            explicitWait.until(ExpectedConditions.elementToBeSelected(notFound));
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            System.out.println("end time: "+getDateTimeNow());
        }

        //==> uu tien
        //// chi dung ham explicit--> dung cac ham tra tham so type By
        ?/// dung mix explicit & implicit ==> test se chac chan hon
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    private String getDateTimeNow() {
        return new Date().toString();
    }
}
