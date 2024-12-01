package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.WebDriver.TargetLocator;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.logging.LogType;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.URL;
import java.time.Duration;

public class Topic_12_WebElements_Commands {
    WebDriver driver;

    @BeforeClass
    public void initialBrowser() {
        //TUONG TAC VOI BROWSER THONG QUA BIEN DRIVER
        driver = new FirefoxDriver();

        System.out.println("Driver ID= " + driver.toString());
    }

    @Test
    public void TC_01_Element() {
        // tuong tac truc tiep len element
        driver.findElement(By.cssSelector(""));

        // tuong tac nhieu lan len 1 element --> khai bao bien (var)
        WebElement firstNameTextbox = driver.findElement(By.cssSelector());

//        // su dung khi da khai bao var
//        firstNameTextbox.clear();
//        firstNameTextbox.sendKeys("Automation FC");
//        firstNameTextbox.isDisplayed();
//
//        // su dung khi chua khai bao var
//        driver.findElement(By.cssSelector()).clear();
//        driver.findElement(By.cssSelector()).sendKeys("Automation FC");
//        driver.findElement(By.cssSelector()).isDisplayed();
        WebElement element = driver.findElement(By.cssSelector("input#firstname"));

        // I - Thao tac len du lieu
        //xoa du lieu trong 1 editable element
            // textbox, text area, dropdown
        element.clear(); //**

        // nhap du lieu vao 1 editable element
        element.sendKeys(""); //**

        // 1- element cha dung 1 loai locator - element con dung 1 loai locator
        element.findElement(By.cssSelector("div.form-fields"))
                        .findElement(By.xpath("//input[@id='FirstName']"));

        // 2- element cha va con dung 1 loai locator
        driver.findElement(By.cssSelector("div.form-fields input#FirstName"));

        // Tim 1 element voi locator la tham so truyen vao
        driver.findElement(By.cssSelector(""));

        // Tim nhieu element voi locator la tham so truyen vao
        driver.findElements(By.cssSelector(""));

        // click len clickable element
            // button/ checkbox/ Radio/ link/ Image/ Dropdown/ Icon
        element.click(); //**

        // tuong duong voi submit thong tin de gui len server
            //register/ log in/ search ... giong nhu send key xong bam enter
        element.submit();

        // II - verify thong tin/ du lieu da action

        // Kiem tra 1 element co hien thi hay khong
        // Ap dung cho tat ca cac loai element
        element.isDisplayed(); //**

        // Kiem tra 1 element da duoc chon hay chua
        // Ap dung checkbox/ radio/ dropdown
        element.isSelected();

        // Kiem tra 1 element co cho phep thao tac hay khong
        // VD: cho phep sua du lieu (true = duoc phep chinh sua) (false = khong duoc phep chinh sua)
        // Test tinh nang ve role n permission
        element.isEnabled(); //*

        // III - Lay du lieu
        // lay chieu rong, chieu cao cua element
        element.getSize();

        // lay ra text cua element (tat ca, ngoai tru placeholder)
        element.getText(); //**

        // lay gia tri attribute (VD: placeholder)
        element.getAttribute(); //*

        // Shadow DOM --> get nhung element trong shadowroot
        element.getShadowRoot(); //*

        // Dev frontend dung de verify
        element.getAriaRole();
        element.getDomProperty("");
        element.getDomAttribute();

        element.getAccessibleName();

        // get font/ background/ font size ... lien quan den css
        element.getCssValue("background-color"); //*

        // IV - lay vi tri --> test giao dien
        // lay ra vi tri cua element (goc tren ben trai) so voi browser
        element.getLocation();

        Rectangle elementRect = element.getRect();

        elementRect.getDimension();

        elementRect.getPoint();

        // V -
        // lay ten the HTML cua element
            // By.id/class/name/css ...
        element.getTagName();

        driver.findElement(By.id("email")).getTagName();
        // input

        // Take Screenshot (chup man hinh loi)
        element.getScreenshotAs(OutputType.FILE);
        element.getScreenshotAs(OutputType.BYTES);
        element.getScreenshotAs(OutputType.BASE64); //*





    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
