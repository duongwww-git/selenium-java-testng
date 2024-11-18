package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.WebDriver.TargetLocator;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chromium.ChromiumDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.swing.*;
import java.net.URL;
import java.time.Duration;

public class Topic_11_WebBrowser_Commands_2 {
    WebDriver driver;

    @BeforeClass
    public void initialBrowser() {
        //TUONG TAC VOI BROWSER THONG QUA BIEN DRIVER
        driver = new FirefoxDriver();

        System.out.println("Driver ID= " + driver.toString());
    }

    @Test
    public void TC_01_Browser() {
        // Mo ra 1 URL bat ki
        driver.get("https://www.facebook.com/login/");

        // Dong browser (ko qtam bao nhieu tabs/ windows)
        // driver.quit();

        // Dong browser - chi dong tab/ windows hien tai
        //Neu chi co 1 tab/ window thi cung tuong tu dong browser
        // driver.close();

        // Tim 1 element voi locator la tham so truyen vao
        // driver.findElement(By.cssSelector(""));

        // Tim nhieu element voi locator la tham so truyen vao
        // driver.findElements(By.cssSelector(""));

        // Lay ra URL cua page hien tai va lưu lai vào bien String homePageUrl
        // String homePageUrl = driver.getCurrentUrl();

        // Su dung luon ko can luu tru
        // Assert.assertEquals(driver.getCurrentUrl(),"https://www.facebook.com/login/");

        // goi bien homePageUrl ra
        // driver.get(homePageUrl);

        // Lay ra title o page hien tai
        driver.getTitle();
        System.out.println("Page Title = " + driver.getTitle());

        // Lay ra Window ID o page hien tai
        driver.getWindowHandle();
        System.out.println("Windows ID = " + driver.getWindowHandle());

        // Lay ra tat ca Window ID cua cac tab/ window
        driver.getWindowHandles();

        // Lay ra source code cua page hien tai
        driver.getPageSource();
        System.out.println("Page Source Code= " + driver.getPageSource());

        WebDriver.TargetLocator switchTo = driver.switchTo();

        // alert - frame/ iframe - window/ tab

        // Alert - tuong tac voi alerts
        driver.switchTo().alert();

        // frame/ iframe
        // switch vao frame/ iframe
        driver.switchTo().frame("");

        // switch tu frame child ra parent page (1 frame)
        driver.switchTo().defaultContent();

        // switch tu frame child ra frame parent (nhieu frame long nhau)
        driver.switchTo().parentFrame();

        // Window
        driver.switchTo().window("");
        driver.switchTo().newWindow(WindowType.TAB).get("https://live.techpanda.org/"); //Selenium version 4
        driver.switchTo().newWindow(WindowType.WINDOW).get("https://live.techpanda.org/"); // Selenium version 4

        // set timeout de tim element (ap dung cho 2 ham findElement & findElements)
        // truong hop khong tim thay --> cho het thoi gian --> show loi
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().timeouts().implicitlyWait(Duration.ofNanos(30));
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(30));

        // get duration da timeout
        driver.manage().timeouts().getImplicitWaitTimeout();

        // set timeout de cho cho page load xong (driver.get da xu li roi, khong can thiet)
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));

        // set time out de cho code JS duoc thuc thi thanh cong
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(30));

        // Cookies
        driver.manage().getCookies();
        //driver.manage().addCookie();

        // browser: full screen, maximize, minimize
        driver.manage().window().maximize();
        driver.manage().window().minimize();
        driver.manage().window().fullscreen();

        // Set browser theo cac kich thuoc khac nhau (test responsive)
        driver.manage().window().setSize(new Dimension(1920,1080));
        driver.manage().window().setSize(new Dimension(1366,768));

        driver.manage().window().getSize();

        // set browser tai vi tri nao tren man hinh
        driver.manage().window().setPosition(new Point(0,0));
        driver.manage().window().getPosition();

        // selenium log: browser/ driver/ network
        driver.manage().logs().get(LogType.BROWSER);
        driver.manage().logs().get(LogType.PERFORMANCE);
        driver.manage().logs().get(LogType.CLIENT);
        driver.manage().logs().get(LogType.SERVER);

        driver.manage().logs().getAvailableLogTypes();

        // quay lai trang truoc do
        driver.navigate().back();
        // chuyen tiep den trang truoc do
        driver.navigate().forward();
        // load lai trang hien tai
        driver.navigate().refresh();

        // mo url khong doi page load, khac voi driver.get
        driver.navigate().to("https://live.techpanda.org/");
        driver.navigate().to(new URL("https://live.techpanda.org/"));




        // step log = log4j
    }

    @Test
    public void TC_02() {

    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
