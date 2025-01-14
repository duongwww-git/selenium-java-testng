package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;


public class Topic_19_Custom_Dropdown {
    WebDriver driver;
    Select select;

    @BeforeClass
    public void beforeClass() {
        FirefoxOptions option = new FirefoxOptions();
        option.addPreference("geo.enabled", false);
        option.addPreference("geo,provider use_corelocation", false);
        driver = new FirefoxDriver(option);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }
    @Test
    public void TC_01_JQuery() throws InterruptedException {
        driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");

        //Select chi handle defaul duoc --> fail
//        new Select(driver.findElement(By.cssSelector("span#salutation-button"))).selectByVisibleText("Dr.");
//        Thread.sleep(3000);

        ////new knowledge
        //Wait explicit
        //List<Web Element>: Nhieu element
        //Vong lap: for
        //Dieu Kien: If
        //Cau lenh: break
        //Viet Ham: reusable function
            //Muc dich cua viet ham:
            // - tai su dung lại nhiều lần (reusable)
            // - dễ dàng bảo trì (maintainable)
            // - dễ dàng mở rộng (extendable)
            // - dễ dàng dọc code (readable)
        //Tham so truyen vao (parameters)

//        viết function giựa trên hành vi user:
//        - tìm dropdown
//        - click vào
//        - dropdown xổ item ra
//        - click vào item cần chọn

        WebElement salutation = driver.findElement(By.cssSelector("span#salutation-button"));
        salutation.click();
        Thread.sleep(2000);

        // gọi hàm (cho salutation)
        selectItemInSelectableDropdown("span#salutation-button","ul#salutation-menu div","Dr.");
        selectItemInSelectableDropdown("span#salutation-button","ul#salutation-menu div","Mr.");
        selectItemInSelectableDropdown("span#salutation-button","ul#salutation-menu div","Prof.");
        // span#salutation-button
        // ul#salutation-menu div
        // Dr.
        Assert.assertEquals(driver.findElement(By.cssSelector("span#salutation-button span.ui-selectmenu-text")).getText(),"Prof.");

        // gọi hàm (cho speed)
        selectItemInSelectableDropdown("span#speed-button","ul#speed-menu div","Fast");
        Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button span.ui-selectmenu-text")).getText(),"Fast");
    }

    @Test
    public void TC_02_React() throws InterruptedException{
        driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
        // div.dropdown
        // div.item>span.text
        // Christian

        selectItemInSelectableDropdown("div.dropdown","div.item>span.text","Christian" );
        Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Christian");

        selectItemInSelectableDropdown("div.dropdown","div.item>span.text","Elliot Fu" );
        Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Elliot Fu");

    }

    @Test
    public void TC_03_VueJS() throws InterruptedException {
        driver.get("https://mikerodham.github.io/vue-dropdowns/");
        // li.dropdown-toggle
        // ul.dropdown-menu a
        // First Option

        selectItemInSelectableDropdown("li.dropdown-toggle", "ul.dropdown-menu a", "First Option");
        Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(), "First Option");

        selectItemInSelectableDropdown("li.dropdown-toggle", "ul.dropdown-menu a", "Second Option");
        Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(), "Second Option");

        selectItemInSelectableDropdown("li.dropdown-toggle", "ul.dropdown-menu a", "Third Option");
        Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(), "Third Option");
    }

    @Test
    public void TC_04_Editable() throws InterruptedException {
        driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");

        selectItemInSelectableDropdown("div.dropdown","div.item>span.text","Algeria" );
        Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Algeria");

        selectItemInSelectableDropdown("div.dropdown","div.item>span.text","Benin" );
        Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Benin");

        selectItemInEditableDropdown("input.search","div.item>span.text","Algeria");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Algeria");

        selectItemInEditableDropdown("input.search","div.item>span.text","Bahrain");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Bahrain");
    }

    @Test
    public void TC_05_Huawei() throws InterruptedException {
        driver.get("https://id5.cloud.huawei.com/CAS/portal/userRegister/regbyemail.html");
        //div.hwid-ctryDropdown
        selectItemInHuaweiDropdown("div.hwid-ctryDropdown","input[ht='input_emailregister_search']","ul.hwid-alpla-list span","Vietnam");
        Assert.assertEquals(driver.findElement(By.cssSelector("div[ht='input_emailregister_dropdown']>span")).getText(), "Vietnam");

        selectItemInHuaweiDropdown("div.hwid-ctryDropdown","input[ht='input_emailregister_search']","ul.hwid-alpla-list span","Bangladesh");
        Assert.assertEquals(driver.findElement(By.cssSelector("div[ht='input_emailregister_dropdown']>span")).getText(), "Bangladesh");
    }

    // mot hàm có thể sử dụng nhiều lần
    // mỗi lần dùng, có thể sử dụng các biến & tham số (param) tương ứng
    private void selectItemInSelectableDropdown(String parentLocator, String childLocator, String textItem) throws InterruptedException {
        WebElement salutation = driver.findElement(By.cssSelector(parentLocator));
        salutation.click();
        Thread.sleep(2000);

        //Wait for all items to load --> explicit wait
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions
                .presenceOfAllElementsLocatedBy(By.cssSelector(childLocator)));

        //list all element - all items và lưu vào 1 biến (kiểu dư liệu list)
        List<WebElement> allItems = driver.findElements(By.cssSelector(childLocator));

        // duyệt qua từng element để kiểm tra
        for (WebElement item: allItems) {
            // Kiểm tra điều kiện: nếu text của item lấy ra = expected --> click)
            if (item.getText().trim().equals(textItem)){
                item.click();

                Thread.sleep(2000);

                //click xong thoat vong lap
                break;
            }
        }
    }

    private void selectItemInEditableDropdown(String parentLocator, String childLocator, String textItem) throws InterruptedException {
        WebElement salutation = driver.findElement(By.cssSelector(parentLocator));
        salutation.clear();
        salutation.sendKeys(textItem);
        Thread.sleep(2000);

        //Wait for all items to load --> explicit wait
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions
                .presenceOfAllElementsLocatedBy(By.cssSelector(childLocator)));

        //list all element - all items và lưu vào 1 biến (kiểu dư liệu list)
        List<WebElement> allItems = driver.findElements(By.cssSelector(childLocator));

        // duyệt qua từng element để kiểm tra
        for (WebElement item: allItems) {
            // Kiểm tra điều kiện: nếu text của item lấy ra = expected --> click)
            if (item.getText().trim().equals(textItem)){
                item.click();

                Thread.sleep(2000);

                //click xong thoat vong lap
                break;
            }
        }
    }

    private void selectItemInHuaweiDropdown(String editableLocator, String parentLocator, String childLocator, String textItem) throws InterruptedException {
        new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.elementToBeClickable(By.cssSelector(editableLocator)));
        driver.findElement(By.cssSelector(editableLocator)).click();
        Thread.sleep(1000);

        driver.findElement(By.cssSelector(parentLocator)).clear();
        driver.findElement(By.cssSelector(parentLocator)).sendKeys(textItem);
        Thread.sleep(1000);

        //Wait for all items to load --> explicit wait
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions
                .presenceOfAllElementsLocatedBy(By.cssSelector(childLocator)));

        //list all element - all items và lưu vào 1 biến (kiểu dư liệu list)
        List<WebElement> allItems = driver.findElements(By.cssSelector(childLocator));

        // duyệt qua từng element để kiểm tra
        for (WebElement item: allItems) {
            // Kiểm tra điều kiện: nếu text của item lấy ra = expected --> click)
            if (item.getText().trim().equals(textItem)){
                item.click();

                Thread.sleep(2000);

                //click xong thoat vong lap
                break;
            }
        }
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
