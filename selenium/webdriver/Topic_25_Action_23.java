package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Topic_25_Action_23 {
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
        //verify before

        //lay het cac element can chon ra --> luu vao 1 list
        //clickandhold chuot trai chon so dau tien can tim
        //di chuot den so cuoi cung
        //nha chuot trai ra
        List<WebElement> numbers = driver.findElements(By.cssSelector("ol#selectable>li"));

        action.clickAndHold(numbers.get(4)) //click and hold tai element 5
                .pause(Duration.ofSeconds(2))
                .moveToElement(numbers.get(11)) //di chuot den element 12
                .release() // nha chuot
                .perform(); // thuc thi cac action phia tren

        //verify after

        List<WebElement> numberSelected = driver.findElements(By.cssSelector("ol#selectable>li.ui-selected"));
        Assert.assertEquals(numberSelected.size(), 8);
    }

    @Test
    public void TC_02_ClickAndHold_Random() {
        //click chon so ban dau
        //giu phim cmd + click so tiep theo

        driver.get("https://automationfc.github.io/jquery-selectable/");

        List<WebElement> numbers = driver.findElements(By.cssSelector("ol#selectable>li"));

        String osName = System.getProperty("os.name");

        System.out.println(osName);
        Keys keys = null;

        if (osName.contains("Mac")) {
            keys = Keys.COMMAND;
        } else if (osName.contains("Windows")) {
            keys = Keys.CONTROL;
        }

        action.keyDown(keys).perform();

        action.click(numbers.get(2))
                .click(numbers.get(5))
                .click(numbers.get(11))
                .click(numbers.get(13))
                .click(numbers.get(19))
                .perform();

        action.keyUp(keys).perform();

        //expected results
        List<String> expectedSelected = new ArrayList<String>();
        expectedSelected.add("3");
        expectedSelected.add("6");
        expectedSelected.add("12");
        expectedSelected.add("14");
        expectedSelected.add("20");

        //verify actual

        List<WebElement> actualSelected = driver.findElements(By.cssSelector("ol#selectable>li.ui-selected"));
        Assert.assertEquals(actualSelected.size(), 5);

        List<String> actualNumbers = new ArrayList<String>();

        for (WebElement number: actualSelected) {
            actualNumbers.add(number.getText());
        }

        Assert.assertEquals(actualNumbers,expectedSelected);
    }

    @Test
    public void TC_03_DoubleClick() throws InterruptedException{
        driver.get("https://automationfc.github.io/basic-form/index.html");


        WebElement doubleClickButton = driver.findElement(By.xpath("//button[text()='Double click me']"));

        if (driver.toString().contains("Chrome") || driver.toString().contains("Edge")){
            action.scrollToElement(doubleClickButton).perform();
            Thread.sleep(3000);
        }
        else {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", doubleClickButton);
            Thread.sleep(3000);
        }


        Thread.sleep(3000);


        action.doubleClick(driver.findElement(By.xpath("//button[text()='Double click me']")))
                .pause(Duration.ofSeconds(2))
                .perform();

        Assert.assertTrue(driver.findElement(By.xpath("//p[text()='Hello Automation Guys!']")).isDisplayed());
        Assert.assertEquals(driver.findElement(By.cssSelector("p#demo")).getText(),"Hello Automation Guys!");
    }

    @Test
    public void TC_04_RightClick() throws InterruptedException {
        driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");

        Assert.assertFalse(driver.findElement(By.cssSelector("li.context-menu-icon-quit")).isDisplayed());

        action.contextClick(driver.findElement(By.xpath("//span[text()='right click me']"))).pause(Duration.ofSeconds(2)).perform();

        Assert.assertTrue(driver.findElement(By.cssSelector("li.context-menu-icon-quit")).isDisplayed());
        Thread.sleep(2000);

        action.moveToElement(driver.findElement(By.cssSelector("li.context-menu-icon-quit"))).pause(Duration.ofSeconds(2)).perform();
        Thread.sleep(2000);

        Assert.assertTrue(driver.findElement(By.cssSelector("li.context-menu-icon-quit.context-menu-visible.context-menu-hover")).isDisplayed());

        action.click(driver.findElement(By.cssSelector("li.context-menu-icon-quit.context-menu-visible.context-menu-hover"))).perform();

        driver.switchTo().alert().accept();
        Thread.sleep(2000);

        Assert.assertFalse(driver.findElement(By.cssSelector("li.context-menu-icon-quit")).isDisplayed());
    }

    @Test
    public void TC_05_DragnDrop_HTML04() throws InterruptedException {
        driver.get("https://automationfc.github.io/kendo-drag-drop/");

        //verify content b4

        Assert.assertEquals(driver.findElement(By.cssSelector("div#droptarget")).getText(), "Drag the small circle here.");

        //drag n drop
        action.clickAndHold(driver.findElement(By.cssSelector("div#draggable")))
                .moveToElement(driver.findElement(By.cssSelector("div#droptarget")))
                .release()
                .perform();

        Thread.sleep(2000);

        //verify content

        Assert.assertEquals(driver.findElement(By.cssSelector("div#droptarget")).getText(), "You did great!");

        //verify bgcolor

        Assert.assertEquals(Color.fromString(driver.findElement(By.cssSelector("div#droptarget")).getCssValue("background-color")).asHex(), "#03a9f4");

    }

    @Test
    public void TC_05_DragnDrop_HTMl5() throws InterruptedException {
        //HTML5?
        driver.get("https://automationfc.github.io/drag-drop-html5/");
        WebElement columnA =  driver.findElement(By.cssSelector("div#column-a>header"));
        WebElement columnB =  driver.findElement(By.cssSelector("div#column-b>header"));

        action.dragAndDrop(columnA, columnB)
                .pause(Duration.ofSeconds(2))
                .perform();

        //verify b4
        if (columnA.toString().contains("A")) {
            action.dragAndDrop(columnA, columnB)
                    .pause(Duration.ofSeconds(2))
                    .perform();
            //verify after
            // reassign element to deal with stale element reference
            columnA =  driver.findElement(By.cssSelector("div#column-a>header"));
            Assert.assertEquals(columnA.getText(),"B");
            Assert.assertEquals(columnB.getText(),"A");
        } else if (columnA.toString().contains("B")) {
            action.dragAndDrop(columnA, columnB)
                    .pause(Duration.ofSeconds(2))
                    .perform();
            //verify after
            // reassign element to deal with stale element reference
            columnA =  driver.findElement(By.cssSelector("div#column-a>header"));
            Assert.assertEquals(columnA.getText(),"A");
            Assert.assertEquals(columnB.getText(),"B");
        }


    }





    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
