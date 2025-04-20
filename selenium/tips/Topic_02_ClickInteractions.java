package tips;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.interactions.Action;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.openqa.selenium.interactions.Actions;
import java.time.Duration;

public class Topic_02_ClickInteractions {
    WebDriver driver;
    Actions action;
    JavascriptExecutor jsExecutor;

    @BeforeClass
    public void beforeClass() {
        EdgeOptions edgeOptions = new EdgeOptions();
        edgeOptions.addArguments("--user-data-dir=/Users/duongwww/Library/Application Support/Microsoft Edge/");
        edgeOptions.addArguments("--profile-directory=Profile 1");
        driver = new EdgeDriver(edgeOptions);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

        action = new Actions(driver);
    }

    @Test
    public void TC_01_typesofClicks (){
        //Web Element
        driver.findElement(By.cssSelector("")).click();
        //Action
        action.click(driver.findElement(By.cssSelector(""))).perform();
        //JS
        jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.cssSelector(" ")));
    }

    @AfterClass
    public void AfterClass () {
        driver.quit();
    }
}
