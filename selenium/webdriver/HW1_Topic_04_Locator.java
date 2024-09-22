package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class HW1_Topic_04_Locator {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Test
    public void TC_01_ID() {

        //locate bang ID va input

        driver.get("http://live.techpanda.org/index.php/customer/account/create/");
        driver.findElement(By.id("search")).sendKeys("Samsung");
        driver.findElement(By.id("firstname")).sendKeys("John Kennedy");
    }

    @Test
    public void TC_02_Class() {

        //locate bang classname va input

        driver.get("http://live.techpanda.org/index.php/customer/account/create/");
        driver.findElement(By.className("header-language-background"));
        driver.findElement(By.className("header-language-container"));
    }

    @Test
    public void TC_02_Name() {
        driver.get("http://live.techpanda.org/index.php/customer/account/create/");
        driver.findElement(By.name("q"));
        driver.findElement(By.name("firstname"));
        driver.findElement(By.name("lastname"));
        driver.findElement(By.name("email"));

    }

    @Test
    public void TC_02_Link() {

        // chi dung duoc voi duong link co text (a href)
        // phai truyen het ca chuoi text vao

        driver.get("http://live.techpanda.org/index.php/customer/account/create/");
        driver.findElement(By.linkText("ABOUT US"));
        driver.findElement(By.linkText("CUSTOMER SERVICE"));
    }

    @Test
    public void TC_02_PartialLink() {

        // Truyen 1 phan cuoi texxt vao cung chay duoc
        // lay text co css styling

        driver.get("http://live.techpanda.org/index.php/customer/account/create/");
        driver.findElement(By.partialLinkText("ABOUT"));
        driver.findElement(By.partialLinkText("ABOUT"));
        driver.findElement(By.partialLinkText("US"));

    }

    // thuc te it su dung link, partial link

    @Test
    public void TC_02_Tagname() {
        driver.get("http://live.techpanda.org/index.php/customer/account/create/");

        // tim nhieu element giong nhau

        int linkNumber = driver.findElements(By.tagName("a")).size();
        System.out.println("Tổng số lượng link = " + linkNumber);

        //tag name giong nhau - element khac nhau
        //VD: deu la input: textbox/checkbox/radio/ ... --> th nay dung tagname thi sai
    }

    @Test
    public void TC_02_CSS() {
        driver.get("http://live.techpanda.org/index.php/customer/account/create/");

        //CSS & ID
            //3 cach viet - viet tat voi ID = #
        driver.findElement(By.cssSelector("input[id='search']"));
        driver.findElement(By.cssSelector("#search"));
        driver.findElement(By.cssSelector("input#search"));

        //CSS & Class
        //3 cach viet - viet tat voi class = .
        driver.findElement(By.cssSelector("div[class='header-language-background']"));
        driver.findElement(By.cssSelector("div.header-language-background"));
        driver.findElement(By.cssSelector(".header-language-background"));

            //ngoai le: class co nhieu gia tri ben trong phan cach boi space
            //VD: class="input-text validate-email required-entry" thi khong truyen het data vao vi CSS hieu dau space " " la chuyen sang the khac (???) --> thay bang dau"."
        driver.findElement(By.cssSelector("input.input-text.validate-email.required-entry"));

        //CSS & Name
            //khong viet tat duoc
        driver.findElement(By.cssSelector("input[name='password']"));

        //CSS & Link

        driver.findElement(By.cssSelector("a[href='http://live.techpanda.org/index.php/']"));

        //CSS & partial Link
            //cuoi
        driver.findElement(By.cssSelector("a[href*='index.php/']"));
            //dau
        driver.findElement(By.cssSelector("a[href^='http://live.techpanda.org/']"));
            //giua
        driver.findElement(By.cssSelector("a[href$='about-magento-demo-store/']"));

        //CSS & Tagname
        int linkNumber = driver.findElements(By.cssSelector("a")).size();
        System.out.println("Tổng số lượng link = " + linkNumber);



        int checkboxNumber = driver.findElements(By.cssSelector("input[type='checkbox']")).size();
        System.out.println("Tổng số lượng checkbox = " + checkboxNumber);

    }

    @Test
    public void TC_02_XPath() {
        driver.get("http://live.techpanda.org/index.php/customer/account/create/");
        // khong co viet tat, them // va @ tu CSS nguyen the la xong, TH dac biet voi link,partial link
        //Xpath & ID
        driver.findElement(By.xpath("//input[@id='search']"));

        //Xpath & Class
        driver.findElement(By.xpath("//div[@class='header-language-background']"));

        //Xpath & Name
        driver.findElement(By.xpath("//input[@name='password']"));

        //Xpath & Link
            // dung text trong HTML luon --> rat manh
        driver.findElement(By.xpath("//a[text()='About Us']"));

        //Xpath & Partial LInk
        //cuoi --> chua dung duoc. hien tai xpath da support tuy vay nhieu trang chua ho tro, CSS support
        //driver.findElement(By.xpath("//a[ends-with(@href, 'index.php/')]"));
        //dau
        driver.findElement(By.xpath("//a[starts-with(@href, 'http://live.techpanda.org/')]"));
        //giua
        driver.findElement(By.xpath("//a[contains(@href, 'about-magento-demo-store/')]"));

        //Xpath & tagname
        int linkNumber = driver.findElements(By.xpath("//a")).size();
        System.out.println("Tổng số lượng link = " + linkNumber);
    }

    @AfterClass
    public void afterClass() {
    //    driver.quit();
    }
}
