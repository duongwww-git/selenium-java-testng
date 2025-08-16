package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Topic_27_shadowDOM {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Test
    public void TC_01_Home_Shop() {
        driver.get("https://shop.polymer-project.org/");

        WebElement shopAppShadowHost = driver.findElement(By.cssSelector("shop-app"));

        SearchContext shopAppShadowRoot = shopAppShadowHost.getShadowRoot();

        WebElement shopHomeShadowHost = shopAppShadowRoot.findElement(By.cssSelector("iron-pages>shop-home")); //shadowDOM chi ho tro css

        SearchContext shopHomeShadowRoot = shopHomeShadowHost.getShadowRoot();

        shopHomeShadowRoot.findElement(By.cssSelector("a[href*='mens_outerwear']~shop-button")).click();
    }

    @Test
    public void TC_02_Nested() {
        driver.get("https://automationfc.github.io/shadow-dom/");
        Assert.assertEquals(driver.findElement(By.xpath("//a")).getText(),"scroll.html");

        WebElement firstShadowHost = driver.findElement(By.cssSelector("div#shadow_host"));
        SearchContext firstShadowRoot = firstShadowHost.getShadowRoot();

        Assert.assertEquals(firstShadowRoot.findElement(By.cssSelector("'span#shadow_content>span")).getText(),"some text");
        Assert.assertEquals(firstShadowRoot.findElements(By.xpath("//a")).size(), 1) ;
        Assert.assertEquals(firstShadowRoot.findElement(By.xpath("//a")).getText(), "nested scroll.html");

        //switch in to another shadowroot
        WebElement secondShadowHost = firstShadowRoot.findElement(By.cssSelector("div#nested_shadow_host"));
        SearchContext secondShadowRoot = secondShadowHost.getShadowRoot();

        //thao tac voi shadowDOM 2nd
        Assert.assertEquals(secondShadowRoot.findElement(By.cssSelector("div#nested_shadow_content>div")).getText(),"nested text");
    }

    @Test
    public void TC03_Book_App() throws InterruptedException {
        driver.get("https://books-pwakit.appspot.com/");

        Thread.sleep(4000);

        //vao shadowroot 1
        WebElement bookAppShadowHost = driver.findElement(By.cssSelector("book-app"));
        SearchContext bookAppShadowRoot = bookAppShadowHost.getShadowRoot();

        bookAppShadowRoot.findElement(By.cssSelector("book-input-decorator>input#input")).sendKeys("Harry Porter");

        //vao shadowroot 2
        WebElement bookDecoratorHost = bookAppShadowRoot.findElement(By.cssSelector("book-input-decorator"));
        SearchContext bookDecoratorRoot = bookDecoratorHost.getShadowRoot();

        bookDecoratorRoot.findElement(By.cssSelector("div.icon")).click();

        Thread.sleep(4000);

        //dung vong lap in ra cacs ket qua tag <li> trong shadowroot
        bookAppShadowHost = driver.findElement(By.cssSelector("book-app"));
        bookAppShadowRoot = bookAppShadowHost.getShadowRoot();

        WebElement bookExplorerShadowHost = bookAppShadowRoot.findElement(By.cssSelector("book-explore"));
        SearchContext bookExplorerShadowRoot = bookExplorerShadowHost.getShadowRoot();

        //section>ul.books>li lay cac item trong list

        List<WebElement> listBookItems = bookExplorerShadowRoot.findElements(By.cssSelector("section>ul.books>li>book-item"));

        //vong lap
        //// book duyệt qua listBooks
        for (WebElement bookItem: listBookItems) {
            SearchContext bookItemShadowRoot = bookItem.getShadowRoot();
            bookItemShadowRoot.findElement(By.cssSelector("div.title-container>h2.title")).getText();
            System.out.println(bookItemShadowRoot.findElement(By.cssSelector("div.title-container>h2.title")).getText());
        }
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
