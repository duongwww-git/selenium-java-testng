package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Topic_21_CheckboxRadio {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

        // 1 - browser open but window not maximized --> failed
        // 2 - browser window maximize but loading icon persists --> failed
        // 3 - browser maximize & loading icon disappear but small screen size --> failed
        // 4 - element need interaction already set to selected/deselected by --> verify b4 interact
    }

    @Test
    public void TC_01_KendoUI() throws InterruptedException {
        driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
        Thread.sleep(3000);

        By dualZoneCheckbox = By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::span/input");

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",driver.findElement(By.xpath("//div[@id='demo-runner']")));

        //click select
        // If element is not selected yet --> click & vice versa
        if (!driver.findElement(dualZoneCheckbox).isSelected()) {
            driver.findElement(dualZoneCheckbox).click();
        }
        //verify select successful
        Assert.assertTrue(driver.findElement(dualZoneCheckbox).isSelected());

        //click deselect
        // if element already deslected --> no need clicking
        // if element selected --> click to deselect
        if (driver.findElement(dualZoneCheckbox).isSelected()) {
            driver.findElement(dualZoneCheckbox).click();
        }
        //verify deselect
        Assert.assertFalse(driver.findElement(dualZoneCheckbox).isSelected());
    }

    @Test
    public void TC_02_RadioButton() throws InterruptedException {
        driver.get("https://demos.telerik.com/kendo-ui/radiobutton/index");
        Thread.sleep(3000);

        By twoPetrol = By.xpath("//label[text()='2.0 Petrol, 147kW']//preceding-sibling::span/input");

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//div[@id='demo-runner']")));

        //click select
        // If element is not selected yet --> click & vice versa
        if (!driver.findElement(twoPetrol).isSelected()) {
            driver.findElement(twoPetrol).click();
        }
        //verify select successful
        Assert.assertTrue(driver.findElement(twoPetrol).isSelected());
    }

    @Test
    public void TC_03_CheckboxSelectAll() throws InterruptedException {
        driver.get("https://automationfc.github.io/multiple-fields/");
        // scroll into view
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.cssSelector("li#id_52")));

        List<WebElement> allCheckboxes = driver.findElements(By.cssSelector("div.form-input-wide input[type='checkbox']"));

        // 1 - Select all checkboxes
        for (WebElement checkbox : allCheckboxes) {
            if (!checkbox.isSelected()) {
                checkbox.click();
            }
        }

        // 4 - verify all above
        for (WebElement checkbox : allCheckboxes) {
            Assert.assertTrue(checkbox.isSelected());
        }

        // 2 - Deselect all checkboxes
        for (WebElement checkbox : allCheckboxes) {
            if (checkbox.isSelected()) {
                checkbox.click();
            }
        }

        // 4 - verify all above
        for (WebElement checkbox : allCheckboxes) {
            Assert.assertFalse(checkbox.isSelected());
        }

        // 3 - Select/Deselect 1 in all items
        for (WebElement checkbox : allCheckboxes) {
            if (checkbox.getDomAttribute("value").equals("Fainting Spells") && !checkbox.isSelected()) {
                checkbox.click();
            }
        }

        // 4 - Verify
        for (WebElement checkbox: allCheckboxes) {
        if (checkbox.getDomAttribute("value").equals("Fainting Spells")) {
            Assert.assertTrue(checkbox.isSelected());
            }
        }
    }

    @Test
    public void TC_04_Angular() {
        //1 - click radio button "Summer" & verify
        driver.get("https://material.angular.io/components/radio/examples");
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//label[text()='Pick your favorite season']/parent::radio-ng-model-example")));
        By summerRadio = By.xpath("//label[text()='Summer']/preceding-sibling::div[@class='mdc-radio']//input");
        if (!driver.findElement(summerRadio).isSelected()) {
            driver.findElement(summerRadio).click();
        }
        //2 - click "Checked" & "Indetermined" & verify
        driver.get("https://material.angular.io/components/checkbox/examples");
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//h2[text()='Checkbox configuration']/parent::mat-card-content")));
        List<WebElement> CheckboxConfigOptions = driver.findElements(By.xpath("//h2[text()='Checkbox configuration']/parent::mat-card-content/section[1]/child::mat-checkbox/descendant::input"));

            //2.1 - click if unselected
        for (WebElement checkbox : CheckboxConfigOptions) {
            if (checkbox.getAttribute("id").equals("mat-mdc-checkbox-1-input") && !checkbox.isSelected()) {
                checkbox.click();
            }
        }
        for (WebElement checkbox : CheckboxConfigOptions) {
            if (checkbox.getAttribute("id").equals("mat-mdc-checkbox-0-input") && !checkbox.isSelected()) {
                checkbox.click();
            }
        }
            //2.2 - verify selected
        for (WebElement checkbox : CheckboxConfigOptions) {
            Assert.assertTrue(checkbox.isSelected());
        }
            //2.3 - deselect
        for (WebElement checkbox : CheckboxConfigOptions) {
            if (checkbox.getAttribute("id").equals("mat-mdc-checkbox-1-input") && checkbox.isSelected()) {
                checkbox.click();
            }
        }
        for (WebElement checkbox : CheckboxConfigOptions) {
            if (checkbox.getAttribute("id").equals("mat-mdc-checkbox-0-input") && checkbox.isSelected()) {
                checkbox.click();
            }
        }
            //2.4 - verify deslected
        for (WebElement checkbox : CheckboxConfigOptions) {
            Assert.assertTrue(!checkbox.isSelected());
        }
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}

//HW 2, 3, 4
