package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.regex.Pattern;

public class Topic_47_WaitP5ExplicitWait {
    WebDriver driver;
    WebDriverWait explicitWait;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        //luôn khởi tạo sau khi driver được khởi tạo, vì cần truyền driver vào
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void TC_01() {
        //wait cho den khi thoa man - alert duoc present
        explicitWait.until(ExpectedConditions.alertIsPresent());

        //element visible (cho 1/ cho nhiều/ tham số là gì?)
        WebElement emailTextBox = explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("")));
        emailTextBox.sendKeys("Automation FC");

        explicitWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(""))));


        explicitWait.until(ExpectedConditions.visibilityOfAllElements(
//                driver.findElements(By.cssSelector("input#email")),
//                driver.findElements(By.cssSelector("input#password")),
//                driver.findElements(By.cssSelector("input#name"))
        ));
        explicitWait.until(ExpectedConditions.visibilityOfAllElements(driver.findElements(By.cssSelector("input[type='text']"))));

        //element invisible
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("")));

        //element presence
        explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("")));

        //element staleness
        WebElement emailConfirm = driver.findElement(By.cssSelector(""));
        driver.navigate().refresh();

            //tim element da co truoc do
        explicitWait.until(ExpectedConditions.stalenessOf(emailConfirm));

        //element clickable
        explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("")));

        //element selected
        explicitWait.until(ExpectedConditions.elementToBeSelected(By.cssSelector("")));

        //element number equals (less/equal/more)
        explicitWait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector(""),5));
        explicitWait.until(ExpectedConditions.numberOfElementsToBeLessThan(By.cssSelector(""),5));
        explicitWait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.cssSelector(""),5));

        //combination (AND/OR/NOT)
            //ca 2 deu dung
        explicitWait.until(ExpectedConditions.and(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector("")),
                ExpectedConditions.elementToBeClickable(By.cssSelector(""))));

            //1 trong 2 dung
        explicitWait.until(ExpectedConditions.or(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector("")),
                ExpectedConditions.elementToBeClickable(By.cssSelector(""))));

            //phu dinh dieu kien
        explicitWait.until(ExpectedConditions.not(
                ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(""))));

        //Url/ Title/ Text
        explicitWait.until(ExpectedConditions.urlContains(""));
        explicitWait.until(ExpectedConditions.urlToBe(""));
        explicitWait.until(ExpectedConditions.urlMatches(""));

        explicitWait.until(ExpectedConditions.titleIs(""));
        explicitWait.until(ExpectedConditions.titleContains(""));

        explicitWait.until(ExpectedConditions.textToBe(By.cssSelector(""),"auto"));
        explicitWait.until(ExpectedConditions.textMatches(By.cssSelector(""), Pattern.compile("")));
        explicitWait.until(ExpectedConditions.textToBePresentInElement(driver.findElement(By.cssSelector("")), "auto"));

        //Attribute/ DOM Property/ Frame
        explicitWait.until(ExpectedConditions.attributeContains(By.cssSelector(""),"class","asd"));
        explicitWait.until(ExpectedConditions.attributeToBe(By.cssSelector(""),"aa","ddd"));

        explicitWait.until(ExpectedConditions.domAttributeToBe(driver.findElement(By.cssSelector("")),"id","lolol"));
        explicitWait.until(ExpectedConditions.domPropertyToBe(driver.findElement(By.cssSelector("")),"TextToContent","Hello"));

        explicitWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.cssSelector("")));
        explicitWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver.findElement(By.cssSelector(""))));
        explicitWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(""));


    }

    @Test
    public void TC_02() {

    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
