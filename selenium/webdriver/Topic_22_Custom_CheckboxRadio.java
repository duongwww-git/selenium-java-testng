package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Topic_22_Custom_CheckboxRadio {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Ubuntu() throws InterruptedException {
        driver.get("https://login.ubuntu.com/+login");
        Thread.sleep(3000);

        // Test: input tag not visible
//        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions
//                .visibilityOfElementLocated(By.cssSelector("input#id_new_user")));

        // Case 1: input tag can't be clicked but can be used for verification (select/deselect)
//        driver.findElement(By.cssSelector("input#id_returning_user")).click();
//        Assert.assertTrue(driver.findElement(By.cssSelector("input#id_returning_user")).isSelected());

        // Case 2: Use another tag (other than input to click) --> can't use for veirification (select/deselect)
//        driver.findElement(By.cssSelector("label.new-user")).click();
//        Assert.assertTrue(driver.findElement(By.cssSelector("input#id_returning_user")).isSelected());

        // Case 3: Use label tag to click & use input tag to verify
        // using 2 different element takes more time to maintain
        driver.findElement(By.cssSelector("label.new-user")).click();
        Assert.assertTrue(driver.findElement(By.cssSelector("input#id_new_user")).isSelected());

        // Case 4: use input tag --> don't use click() from WebElement--> use click from JS
        By registerRadio = By.cssSelector("input#id_new_user");
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();",
                driver.findElement(registerRadio));
        Thread.sleep(2000);
        Assert.assertTrue(driver.findElement(registerRadio).isSelected());

        By termCheckbox = By.cssSelector("input#id_accept_tos");
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();",
                driver.findElement(termCheckbox));
        Thread.sleep(2000);
        Assert.assertTrue(driver.findElement(termCheckbox).isSelected());

    }

    @Test
    public void TC_02_GoogleForm() throws InterruptedException {
        driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");

        Thread.sleep(2000);

    // Click Can Tho Radio
        By canthoRadio = By.cssSelector(("div[aria-label='Cần Thơ']"));

        //Click
        driver.findElement(canthoRadio).click();
        Thread.sleep(2000);

        //Verify
            // 1 - verify visual
        Assert.assertTrue(driver.findElement(By.cssSelector("div[aria-label='Cần Thơ'][aria-checked='true']")).isDisplayed());

            // 2 - get dom attribute
        Assert.assertEquals(driver.findElement(canthoRadio).getDomAttribute("aria-checked"),"true");

    // Click Mi Quang Checkbox
        By miquangCheckbox = By.cssSelector(("div[aria-label='Mì Quảng']"));

        //Click
        driver.findElement(miquangCheckbox).click();
        Thread.sleep(2000);

        //Verify
        // 1 - verify visual
        Assert.assertTrue(driver.findElement(By.cssSelector("div[aria-label='Mì Quảng'][aria-checked='true']")).isDisplayed());

        // 2 - get dom attribute
        Assert.assertEquals(driver.findElement(miquangCheckbox).getDomAttribute("aria-checked"),"true");

    // Select all checkbox
        List<WebElement> allCheckboxes = driver.findElements(By.cssSelector("div[role='checkbox']"));

        for (WebElement checkbox: allCheckboxes) {
            if(!checkbox.getDomAttribute("aria-checked").equals("true")) {
                checkbox.click();
            }
        }
        // Verify all
        for (WebElement checkbox: allCheckboxes) {
            Assert.assertEquals(checkbox.getDomAttribute("aria-checked"),"true");
        }
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
