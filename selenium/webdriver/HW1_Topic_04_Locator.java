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

        driver.get("https://viblo.asia/p/tim-hieu-ve-sonarqube-924lJEPzZPM");
        System.out.println(driver.findElement(By.id("_sonarqube-la-gi-1")));
    }

    @Test
    public void TC_02_Class() {

        //locate bang classname va input

        driver.get("https://viblo.asia/p/tim-hieu-ve-sonarqube-924lJEPzZPM");
        System.out.println(driver.findElement(By.className("sb__input")));
        System.out.println(driver.findElement(By.className("mb-2")));
    }

    @Test
    public void TC_02_Name() {
        driver.get("https://viblo.asia/p/tim-hieu-ve-sonarqube-924lJEPzZPM");
        System.out.println(driver.findElement(By.name("viewport")));


    }

    @Test
    public void TC_02_Link() {

        // chi dung duoc voi duong link co text (a href)
        // phai truyen het ca chuoi text vao

        driver.get("https://viblo.asia/p/tim-hieu-ve-sonarqube-924lJEPzZPM");
        System.out.println(driver.findElement(By.linkText("sonarcloud.io/about/sq")));
        System.out.println(driver.findElement(By.linkText("Tags")));
    }

    @Test
    public void TC_02_PartialLink() {

        // Truyen 1 phan cuoi texxt vao cung chay duoc
        // lay text co css styling

        driver.get("https://viblo.asia/p/tim-hieu-ve-sonarqube-924lJEPzZPM");
        System.out.println(driver.findElement(By.partialLinkText("sonarcloud.io/")));


    }

    // thuc te it su dung link, partial link

    @Test
    public void TC_02_Tagname() {
        driver.get("https://viblo.asia/p/tim-hieu-ve-sonarqube-924lJEPzZPM");

        // tim nhieu element giong nhau

        int linkNumber = driver.findElements(By.tagName("a")).size();
        System.out.println("Tổng số lượng link = " + linkNumber);

        //tag name giong nhau - element khac nhau
        //VD: deu la input: textbox/checkbox/radio/ ... --> th nay dung tagname thi sai
    }

    @Test
    public void TC_02_CSS() {
        driver.get("https://www.w3schools.com/java/default.asp");

        //CSS & ID
            //3 cach viet - viet tat voi ID = #
        System.out.println(driver.findElement(By.cssSelector("input[id='tnb-google-search-input']")));
        System.out.println(driver.findElement(By.cssSelector("input#tnb-google-search-input")));
        System.out.println(driver.findElement(By.cssSelector("#tnb-google-search-input")));

        //CSS & Class
            //3 cach viet - viet tat voi class = .
        System.out.println(driver.findElement(By.cssSelector("div[class='w3-bar-item']")));
        System.out.println(driver.findElement(By.cssSelector("div.w3-bar-item")));
        System.out.println(driver.findElement(By.cssSelector(".w3-bar-item")));

            //ngoai le: class co nhieu gia tri ben trong phan cach boi space
            //VD: class="input-text validate-email required-entry" thi khong truyen het data vao vi CSS hieu dau space " " la chuyen sang the khac (???) --> thay bang dau"."
        // driver.findElement(By.cssSelector("input.input-text.validate-email.required-entry"));

        //CSS & Name
            //khong viet tat duoc
        System.out.println(driver.findElement(By.cssSelector("input[name='ex1']")));

        //CSS & Link

        System.out.println(driver.findElement(By.cssSelector("a[href='/about/about_copyright.asp']")));

        //CSS & partial Link
            //cuoi
        driver.findElement(By.cssSelector("a[href*='copyright.asp']"));
            //dau
        driver.findElement(By.cssSelector("a[href^='/about/']"));
            //giua
        //driver.findElement(By.cssSelector("a[href$='about-magento-demo-store/']"));

        //CSS & Tagname
        int linkNumber = driver.findElements(By.cssSelector("a")).size();
        System.out.println("Tổng số lượng link = " + linkNumber);



        int checkboxNumber = driver.findElements(By.cssSelector("input[type='checkbox']")).size();
        System.out.println("Tổng số lượng checkbox = " + checkboxNumber);

    }

    @Test
    public void TC_02_XPath() {
        driver.get("https://www.w3schools.com/java/default.asp");
        // khong co viet tat, them // va @ tu CSS nguyen the la xong, TH dac biet voi link,partial link
        //Xpath & ID
        System.out.println(driver.findElement(By.xpath("//input[@id='tnb-google-search-input']")));


        //Xpath & Class
        System.out.println(driver.findElement(By.xpath("//div[@class='w3-bar-item']")));

        //Xpath & Name
        System.out.println(driver.findElement(By.xpath("//input[@name='ex1']")));

        //Xpath & Link
            // dung text trong HTML luon --> rat manh
        System.out.println(driver.findElement(By.xpath("//a[text()='HTML Tutorial']")));

        //Xpath & Partial LInk
        //cuoi --> chua dung duoc. hien tai xpath da support tuy vay nhieu trang chua ho tro, CSS support
        //driver.findElement(By.xpath("//a[ends-with(@href, 'index.php/')]"));
        //dau
        driver.findElement(By.xpath("//a[starts-with(@href, '/spaces/')]"));
        //giua
        driver.findElement(By.xpath("//a[contains(@href, '/index')]"));

        //Xpath & tagname
        int linkNumber = driver.findElements(By.xpath("//a")).size();
        System.out.println("Tổng số lượng link = " + linkNumber);
    }

    @AfterClass
    public void afterClass() {
    //    driver.quit();
    }
}
