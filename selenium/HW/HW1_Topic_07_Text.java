package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class HW1_Topic_07_Text {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
    }

    @Test
    public void TC_01_absoluteText() {
        driver.get("https://automationfc.github.io/basic-form/");
        driver.findElement(By.xpath("//p[text()='P.S. You can edit this text from admin panel.']"));
    }

    @Test
    public void TC_02_concat() {
        driver.get("https://automationfc.github.io/basic-form/");
        driver.findElement(By.xpath("//span[text()=concat('Hello \"John\", What',\"'s happened?\")]"));
    }

    @Test
    public void TC_03_contains() {
        driver.get("https://automationfc.github.io/basic-form/");
        driver.findElement(By.xpath("//p[contains(string(),'NOP SOLUTIONS')]"));
        driver.findElement(By.xpath("//p[contains(.,'NY 10001')]"));
        driver.findElement(By.xpath("//p[contains(text(),'your order may be held for up to 10 days after')]"));
    }

    @Test
    public void TC_04_AndOr() {
        driver.get("https://automationfc.github.io/basic-form/");
        driver.findElement(By.xpath("//input[@type='radio' and @id='under_18']"));
        driver.findElement(By.xpath("//input[@id='over_18' or @id='under_18']"));
    }

    @Test
    public void TC_06_InsideOutside() {
        driver.get("https://automationfc.github.io/jquery-selectable/");
        driver.findElement(By.xpath("//li[@class='ui-state-default ui-selectee'][2]"));
        driver.findElement(By.xpath("(//li[@class='ui-state-default ui-selectee'])[11]"));
    }

    @Test
    public void TC_07_Last() {
        driver.get("https://automationfc.github.io/jquery-selectable/");
        driver.findElement(By.xpath("//ol[@id='selectable']/li[position()='5']"));
        driver.findElement(By.xpath("//ol[@id='selectable']/li[last()]"));
        driver.findElement(By.xpath("//ol[@id='selectable']/li[last()-2]"));
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
