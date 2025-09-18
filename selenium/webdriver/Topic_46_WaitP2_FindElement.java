package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Date;
import java.util.List;

public class Topic_46_WaitP2_FindElement {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void TC_01_findElement() {
        //1-nếu như tìm element có duy nhất 1 cái --> ko can chờ hết 10s
        driver.get("https://demo.nopcommerce.com/login?returnUrl=/");
        System.out.println("Start end: "+ getDateTimeNow());
        driver.findElement(By.cssSelector("input#small-searchterms"));
        System.out.println("Start end: "+ getDateTimeNow());

        //2-nếu như tìm element ra > 1 cái --> ko can chờ hết 10s, luôn lấy element đầu tiên
        System.out.println("Start end: "+ getDateTimeNow());
        driver.findElement(By.cssSelector("input[type='email']"));
        System.out.println("Start end: "+ getDateTimeNow());

        //3-nếu như tìm element mà không thấy --> cố gắng tìm đi tìm lại nữa giây/lần --> hết thời gian ko thấy thì show exception + fail test case, không chạy step còn lại
        System.out.println("Start end: "+ getDateTimeNow());
        driver.findElement(By.cssSelector("input#FirstName")).sendKeys("Automation FC");
        System.out.println("Start end: "+ getDateTimeNow());
        //

    }

    @Test
    public void TC_02_findElements() {
        driver.get("https://demo.nopcommerce.com/login?returnUrl=/");
        List<WebElement> elementList = null;

        //1-nếu như tìm element có duy nhất 1 cái
        System.out.println("Start time: "+ getDateTimeNow());
        elementList = driver.findElements(By.cssSelector("input#small-searchterms"));
        System.out.println("Tong so luong element trong list" +elementList.size());
        System.out.println("End time: "+ getDateTimeNow());
        //2-nếu như tìm element ra > 1 cái
        System.out.println("Start time: "+ getDateTimeNow());
        elementList = driver.findElements(By.xpath("//a[@href]"));
        for (WebElement element: elementList){
            System.out.println(element.getDomProperty("href"));
        }
        System.out.println("End time: "+ getDateTimeNow());

        //3-nếu như tìm element mà không thấy --> retry het 10s nhung ko fail test case
        System.out.println("Start end: "+ getDateTimeNow());
        elementList = driver.findElements(By.cssSelector("input#FirstName"));
        System.out.println("Tong so element trong list " + elementList.size());
        System.out.println("Start end: "+ getDateTimeNow());

    }

    @Test
    public void TC_03_UniqueElement(){
        driver.get("https://live.techpanda.org/");

        //dang bi hidden,ko thao tac len dc
        driver.findElement(By.xpath("//a[@title='My account']")).click();
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    private String getDateTimeNow() {
        return new Date().toString();
    }
}
