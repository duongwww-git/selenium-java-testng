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

public class Topic_07_XpathAxes {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
    }

    @Test
    public void TC_01() {
        driver.get("https://live.techpanda.org/index.php/mobile.html");
        driver.findElement(By.xpath("//a[@title='IPhone']/parent::h2/following-sibling::div[@class='actions']/button"));
        driver.findElement(By.xpath("//*[@id='top']/body/div/div/div[2]/div/div[2]/div[1]/div[3]/ul/li[1]/div/div[3]/button"));
        // 2 options trên đều ra 1 kết quả, tuy vậy option 1 sẽ luôn định vị đúng với thẻ a = 'iphone' kể cả khi thay đổi vị trí
    }

    @Test
    public void TC_02() {
        driver.get("https://live.techpanda.org/index.php/tv.html");
        driver.findElement(By.xpath("//a[@title='Samsung LCD']//parent::h2//following-sibling::div[@class='actions']//a[@class='link-wishlist']"));

    }

    @Test
    public void TC_03() {
        driver.get("https://www.packtpub.com/en-us/search?query=selenium");
        driver.findElement((By.xpath("//div[contains(text(),'Selenium Framework Design in Data-Driven Testing')]//ancestor::a[@class='product-card-v2-content-info']//following-sibling::div//button[contains(@class,'cart-button')]")));
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
