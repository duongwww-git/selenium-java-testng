package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class HW2_Topic_05_By_Locator_1_XPath {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Test
    public void TC_01_textBoxes() {
        driver.get("https://demo.nopcommerce.com/register?returnUrl=/");
        By firstName = By.xpath("//input:[@id='FirstName']");
        By lastName = By.xpath("//input:[@id='LastName']");
        By email = By.xpath("//input:[@id='Email']");
        By companyName = By.xpath("//input:[@id='Company']");
        By passWord = By.xpath("//input:[@id='Password']");
        By passWordConfirm = By.xpath("//input[@id='ConfirmPassword']");

        System.out.println(firstName);
        System.out.println(lastName);
        System.out.println(email);
        System.out.println(companyName);
        System.out.println(passWord);
        System.out.println(passWordConfirm);
    }

    @Test
    public void TC_02_radioButton() {
        driver.get("https://demo.nopcommerce.com/register?returnUrl=/");
        By radioGenderMale = By.xpath("//input:[@id='gender-male']");
        By radioGenderFemale = By.xpath("//input:[@id='gender-female']");

        System.out.println(radioGenderFemale);
        System.out.println(radioGenderMale);

    }

    @Test
    public void TC_03_checkboxes() {
        driver.get("https://demo.nopcommerce.com/register?returnUrl=/");
        By newsletter = By.xpath("//input:[@id='Newsletter']");

        System.out.println(newsletter);

    }

    @Test
    public void TC_03_dropDown() {
        driver.get("https://demo.nopcommerce.com/register?returnUrl=/");
        By Birthday = By.xpath("//select:[@name='DateOfBirthDay']");
        By Birthmonth = By.xpath("//select:[@name='DateOfBirthMonth']");
        By Birthyear = By.xpath("//select:[@name='DateOfBirthYear']");

        System.out.println(Birthday);
        System.out.println(Birthmonth);
        System.out.println(Birthyear);

    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
