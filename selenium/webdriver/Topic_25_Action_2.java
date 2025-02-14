package webdriver;

import com.google.common.base.Verify;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Topic_25_Action_2 {
    WebDriver driver;
    Actions action;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

        // truyen driver vao --> khoi tao doi tuong action
        action = new Actions(driver);
    }

    @Test
    public void TC_01_ClickAndHold_Fixed() {
        driver.get("https://automationfc.github.io/jquery-selectable/");

        // Mouse down select the first num
        // Move mouse to last num
        // Mouse up
        // Get all numbers selected --> save to a list
        List<WebElement> numbers = driver.findElements(By.cssSelector("ol#selectable>li"));

        // click(): click & release
        // clickAndHold(): click
        action.clickAndHold(numbers.get(4)).pause(Duration.ofSeconds(2)) //clicknhold on element 5
                .moveToElement(numbers.get(11)) // move mouse to element 12
                .release() // release the mouse click
                .perform();

        // Verify change in HTML --> ui-selected
        List<WebElement> numbersSelected = driver.findElements(By.cssSelector("ol#selectable>li.ui-selected"));
        Assert.assertEquals(numbersSelected.size(),8);

    }

    @Test
    public void TC_02_ClickAndHold_Random() {
        driver.get("https://automationfc.github.io/jquery-selectable/");

        List<WebElement> numbers = driver.findElements(By.cssSelector("ol#selectable>li"));

        String osName = System.getProperty("os.name");

        Keys keys = null;

        if (osName.contains("Windows")) {
            keys = Keys.CONTROL;
        } else {
            keys = Keys.COMMAND;
        }

        // Actual Numbers: 3 6 12 14 20
        List<String> actualNum = new ArrayList<String>();
        actualNum.add("3");
        actualNum.add("6");
        actualNum.add("12");
        actualNum.add("14");
        actualNum.add("20");

        // keyDown() press n hold
        action.keyDown(keys).perform();

        for (String number : actualNum) {
            action.click(numbers.get(Integer.parseInt(number)-1)).perform();
        }

//        action.click(numbers.get(2))
//                .click(numbers.get(5))
//                .click(numbers.get(11))
//                .click(numbers.get(13))
//                .click(numbers.get(19))
//                .perform();
//        action.keyUp(keys).perform();

        // Verify change in HTML --> ui-selected
        List<WebElement> numbersSelected = driver.findElements(By.cssSelector("ol#selectable>li.ui-selected"));
        Assert.assertEquals(numbersSelected.size(),5);

        // Expected Numbers: 3 6 12 14 20
        List<String> expectedNum = new ArrayList<String>();

        for (WebElement number: numbersSelected) {
            expectedNum.add(number.getText());
        }

        Assert.assertEquals(actualNum, expectedNum);


    }

    @Test
    public void TC_03_DoubleClick() {
        driver.get("https://automationfc.github.io/basic-form/index.html");

        //only firefox wont auto scroll, edge and chrome auto scroll
        if (driver.toString().contains("Firefox")) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//button[text()='Double click me']")));
         }

        //scrollToElement() only scroll to elements already in the viewport to the top side of the view port
        //action.scrollToElement(driver.findElement(By.xpath("//button[text()='Double click me']")));

        action.doubleClick(driver.findElement(By.xpath("//button[text()='Double click me']")))
                .perform();

        Assert.assertEquals(driver.findElement(By.cssSelector("p#demo")).getText(), "Hello Automation Guys!");
    }

    @Test
    public void TC04_RightClick() throws InterruptedException {
        driver.get("https://swisnl.github.io/jQuery-contextMenu/demo.html");

        Assert.assertFalse(driver.findElement(By.cssSelector("ul.context-menu-list")).isDisplayed());

        action.contextClick(driver.findElement(By.xpath("//span[text()='right click me']"))).perform();

        Assert.assertTrue(driver.findElement(By.cssSelector("ul.context-menu-list")).isDisplayed());

        action.moveToElement(driver.findElement(By.cssSelector("li.context-menu-icon-quit"))).perform();

        Assert.assertTrue(driver.findElement(By.cssSelector("li.context-menu-icon-quit.context-menu-visible.context-menu-hover")).isDisplayed());

        action.click(driver.findElement(By.cssSelector("li.context-menu-icon-quit.context-menu-visible.context-menu-hover"))).perform();

        driver.switchTo().alert().accept();
        Thread.sleep(2000);

        Assert.assertFalse(driver.findElement(By.cssSelector("li.context-menu-icon-quit")).isDisplayed());






    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
