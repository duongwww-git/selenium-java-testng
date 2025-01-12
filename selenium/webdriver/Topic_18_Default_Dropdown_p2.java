package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;


public class Topic_18_Default_Dropdown_p2 {
    WebDriver driver;
    Select select;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }
    @Test
    public void TC_01_RodeDropdown() {
        driver.get("https://www.rode.com/wheretobuy");

        // define select for specific dropdown
        select = new Select(driver.findElement(By.cssSelector("select#country")));

        //verify multipe select allowed for the dropdown
        Assert.assertFalse(select.isMultiple());

        //select option
        select.selectByVisibleText("Vietnam");

        //sendkey to text box
        driver.findElement(By.cssSelector("input#map_search_query")).sendKeys("HO CHI MINH");

        //click button search
        driver.findElement(By.xpath("//button[text()='Search']")).click();

        //list all dealers
        List<WebElement> dealers = driver.findElements(By.xpath("//h3[text()='Dealers']//following-sibling::div//h4"));
        Assert.assertEquals(dealers.size(),16);

        //print out all dealers
        for (WebElement element : dealers) {
            System.out.println(element.getText());
        }
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
