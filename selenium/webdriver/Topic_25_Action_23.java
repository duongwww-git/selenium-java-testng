package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.event.InputEvent;
import java.io.*;
import java.nio.charset.Charset;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Topic_25_Action_23 {
    WebDriver driver;
    Actions action;
    JavascriptExecutor jsExecutor;
    String dragDropFilePath = System.getProperty("user.dir") + "/dragDrop/drag_and_drop_helper.js";

    @BeforeClass
    public void beforeClass() {
        driver = new EdgeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();

        // truyen driver vao --> khoi tao doi tuong action
        action = new Actions(driver);
        jsExecutor = (JavascriptExecutor) driver;
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
    public void TC_05_DragnDrop_HTML5_action() throws InterruptedException {
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

    @Test
    public void TC_06_DragnDrop_HTML5_Jquery() throws IOException, InterruptedException {
        //HTML5?
        driver.get("https://automationfc.github.io/drag-drop-html5/");

        String jqueryContent = getContentFile(dragDropFilePath);

        String sourceCss = "div#column-a";
        String targetCss = "div#column-b";

        // Drag and Drop
        jqueryContent = jqueryContent + "$(\"" + sourceCss + "\").simulateDragDrop({ dropTarget: \"" + targetCss + "\"});";
        jsExecutor.executeScript(jqueryContent);
        Thread.sleep(3000);

        // after drag and drop
        Assert.assertEquals(driver.findElement(By.cssSelector("div#column-a>header")).getText(),"B");
        Assert.assertEquals(driver.findElement(By.cssSelector("div#column-b>header")).getText(),"A");

        // Drag and Drop
        jsExecutor.executeScript(jqueryContent);
        Thread.sleep(3000);

        // after drag and drop
        Assert.assertEquals(driver.findElement(By.cssSelector("div#column-a>header")).getText(),"A");
        Assert.assertEquals(driver.findElement(By.cssSelector("div#column-b>header")).getText(),"B");
    }

    @Test
    public void TC_06_DragnDrop_HTML5_JavaRobot() throws AWTException, InterruptedException {
        //HTML5?
        driver.get("https://automationfc.github.io/drag-drop-html5/");

        // Drag and Drop
        dragAndDropHTML5ByXpath("//div[@id='column-a']","//div[@id='column-b']");
        Thread.sleep(3000);

        // after drag and drop
        Assert.assertEquals(driver.findElement(By.cssSelector("div#column-a>header")).getText(),"B");
        Assert.assertEquals(driver.findElement(By.cssSelector("div#column-b>header")).getText(),"A");

        // Drag and Drop
        dragAndDropHTML5ByXpath("//div[@id='column-a']","//div[@id='column-b']");
        Thread.sleep(3000);

        // after drag and drop
        Assert.assertEquals(driver.findElement(By.cssSelector("div#column-a>header")).getText(),"A");
        Assert.assertEquals(driver.findElement(By.cssSelector("div#column-b>header")).getText(),"B");
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    public String getContentFile(String filePath) throws IOException, FileNotFoundException {
        Charset cs = Charset.forName("UTF-8");
        FileInputStream stream = new FileInputStream(filePath);
        try {
            Reader reader = new BufferedReader(new InputStreamReader(stream, cs));
            StringBuilder builder = new StringBuilder();
            char[] buffer = new char[8192];
            int read;
            while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
                builder.append(buffer, 0, read);
            }
            return builder.toString();
        } finally {
            stream.close();
        }
    }

//    public void dragAndDropHTML5ByXpath(String sourceLocator, String targetLocator) throws AWTException {
//
//        WebElement source = driver.findElement(By.xpath(sourceLocator));
//        WebElement target = driver.findElement(By.xpath(targetLocator));
//
//        // Setup robot
//        Robot robot = new Robot();
//        robot.setAutoDelay(500);
//
//        // Get size of elements
//        Dimension sourceSize = source.getSize();
//        Dimension targetSize = target.getSize();
//
//        // Get center distance
//        int xCentreSource = sourceSize.width / 2;
//        int yCentreSource = sourceSize.height / 2;
//        int xCentreTarget = targetSize.width / 2;
//        int yCentreTarget = targetSize.height / 2;
//
//        Point sourceLocation = source.getLocation();
//        Point targetLocation = target.getLocation();
//
//        // Make Mouse coordinate center of element
//        sourceLocation.x += 20 + xCentreSource;
//        sourceLocation.y += 110 + yCentreSource;
//        targetLocation.x += 20 + xCentreTarget;
//        targetLocation.y += 110 + yCentreTarget;
//
//        // Move mouse to drag from location
//        robot.mouseMove(sourceLocation.x, sourceLocation.y);
//
//        // Click and drag
//        robot.mousePress(InputEvent.BUTTON1_MASK);
//        robot.mousePress(InputEvent.BUTTON1_MASK);
//        robot.mouseMove(((sourceLocation.x - targetLocation.x) / 2) + targetLocation.x, ((sourceLocation.y - targetLocation.y) / 2) + targetLocation.y);
//
//        // Move to final position
//        robot.mouseMove(targetLocation.x, targetLocation.y);
//
//        // Drop
//        robot.mouseRelease(InputEvent.BUTTON1_MASK);
//    }
}
