package webdriver;

import com.sun.source.tree.AssertTree;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.time.Duration;
import java.util.Set;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Topic_41_WindowsAndTabs {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
//        driver = new FirefoxDriver();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Test
    public void TC_01_Github() {
        driver.get("https://automationfc.github.io/basic-form/index.html");

        // return ID of current Tab
        String githubWindowId = driver.getWindowHandle();

        driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
        SleepInSeconds(3);

        switchToWindowsById(githubWindowId);

        driver.findElement(By.cssSelector("textarea[name='q']")).sendKeys("auto testing");
        driver.findElement(By.cssSelector("textarea[name='q']")).sendKeys(Keys.ENTER);
        SleepInSeconds(3);

        String googleWindowId =  driver.getWindowHandle();

        switchToWindowsById(googleWindowId);

        SleepInSeconds(3);

        driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
        SleepInSeconds(3);

        switchWindowByTitle("Facebook");
        SleepInSeconds(2);

        driver.findElement(By.cssSelector("input#email")).sendKeys("duong@gmail.com");
        driver.findElement(By.cssSelector("input#pass")).sendKeys("Duong@123");

        switchWindowByTitle("Selenium WebDriver");
        SleepInSeconds(3);

        driver.findElement(By.xpath("//a[text()='LAZADA']")).click();
        SleepInSeconds(2);
//
        switchWindowByTitle("Lazada");

        closeAllWindowsWithoutParents(githubWindowId);
    }

    @Test
    public void TC_02_TechPanda() {
        //open listing screen
        driver.get("https://live.techpanda.org/");

        String listingHandle = driver.getWindowHandle();

        driver.findElement(By.xpath("//a[text()='Mobile']")).click();
        SleepInSeconds(2);

        //select to compare

        driver.findElement(By.xpath("//a[@title='Xperia']/following-sibling::div//a[text()='Add to Compare']")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg")).getText(),"The product Sony Xperia has been added to comparison list.");

        driver.findElement(By.xpath("//a[@title='Samsung Galaxy']/following-sibling::div//a[text()='Add to Compare']")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg")).getText(),"The product Samsung Galaxy has been added to comparison list.");

        driver.findElement(By.cssSelector("button[title='Compare']")).click();

        //switch to comparison screen
        switchToWindowsById(listingHandle);
        SleepInSeconds(2);

        Assert.assertEquals(driver.getTitle(),"Products Comparison List - Magento Commerce");
        String compareHandle = driver.getWindowHandle();

        //close tab & sw to listing
        driver.close();
        switchWindowByTitle(compareHandle);

        driver.findElement(By.xpath("//a[text()='Clear All']")).click();
        driver.switchTo().alert().accept();
        SleepInSeconds(3);

        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg")).getText(),"The comparison list was cleared.");
        SleepInSeconds(3);
    }

    @Test
    public void TC_03_Cambridge() {
        driver.get("https://dictionary.cambridge.org/vi/");
        String homeWindowID = driver.getWindowHandle();

        driver.findElement(By.xpath("//header//span[text()='Đăng nhập']")).click();
        SleepInSeconds(2);

        switchWindowByTitle("Login");

        driver.findElement(By.xpath("//input[@value='Log in']")).click();

        Assert.assertTrue(driver.findElement(By.xpath("//input[@aria-label='Email']//following-sibling::span[text()='This field is required']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//input[@aria-label='Password']//following-sibling::span[text()='This field is required']")).isDisplayed());

        closeAllWindowsWithoutParents(homeWindowID);

        switchWindowByTitle("Topics Frame");

        driver.findElement(By.cssSelector("input[aria-label='Tìm kiếm']")).sendKeys("automation");
        driver.findElement(By.cssSelector("button[aria-label='Search']")).click();
        SleepInSeconds(2);

        Assert.assertEquals(driver.findElement(By.xpath("//div[@data-id='cald4']//div[@class='di-title']")).getText(),"automation");

    }

    @Test
    public void TC_04_Havard() {
        driver.get("https://courses.dce.harvard.edu/");
        String homeHarvard = driver.getWindowHandle();

        driver.findElement(By.xpath("//a[@data-action='login']")).click();

        switchToWindowsById(homeHarvard);

        Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='DCE Login Portal']")).isDisplayed());

        closeAllWindowsWithoutParents(homeHarvard);
        switchWindowByTitle("DCE Course Search");
        SleepInSeconds(2);

        Assert.assertTrue(driver.findElement(By.cssSelector("div.activescreen")).isDisplayed());

        driver.findElement(By.cssSelector("button[class*='button--cancel']")).click();
        SleepInSeconds(2);

        driver.findElement(By.cssSelector("input#crit-keyword")).sendKeys("Data Science: An Artificial Ecosystem");
        new Select(driver.findElement(By.cssSelector("select#crit-srcdb"))).selectByVisibleText("Harvard Summer School 2025");
        new Select(driver.findElement(By.cssSelector("select#crit-summer_school"))).selectByVisibleText("Harvard College");
        new Select(driver.findElement(By.cssSelector("select#crit-session"))).selectByVisibleText("Any Part of Term");

        driver.findElement(By.cssSelector("button#search-button")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("span.result__code")).getText(),"STAT S-115");
    }

    @Test
    public void TC_05_Selenium4x() {
        driver.get("https://demo.nopcommerce.com/");

        //tu dong switch qua tab/window roi
        //mo ra 2 windows khac nhau
        driver.switchTo().newWindow(WindowType.TAB).get("https://admin-demo.nopcommerce.com/");

        switchWindowByTitle("Home page title");
    }

    private void SleepInSeconds(long timeInSeconds){
        try {
            Thread.sleep(timeInSeconds*1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void switchToWindowsById(String windowID){
        Set<String> allWindowsID = driver.getWindowHandles();

        // loop through windows ID --> if differente from first ID --> switch
        for (String id : allWindowsID){
            if (!id.equals(windowID)){
                driver.switchTo().window(id);
                break;
            }
        }
    }

    private void switchWindowByTitle(String expectedPageTitle) {
        Set<String> allWindowIDs = driver.getWindowHandles();
        for (String id : allWindowIDs) {
            driver.switchTo().window(id);
            if (driver.getTitle().contains(expectedPageTitle)){
                break;
            }
        }
    }

    private void closeAllWindowsWithoutParents(String windowID){
        Set<String> allWindowsID = driver.getWindowHandles();

        // loop through windows ID --> if differente from first ID --> switch
        for (String id : allWindowsID){
            if (!id.equals(windowID)){
                driver.switchTo().window(id);
                driver.close(); //dong tab/window dang active only
            }
        }
        driver.switchTo().window(windowID);
    }


    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
