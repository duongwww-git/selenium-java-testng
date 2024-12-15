package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_14_ElementExercise {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().setSize(new Dimension(1366, 768));
    }
    // Kiem tra 1 element co hien thi hay khong
       // Ap dung cho tat ca cac loai element
//        element.isDisplayed(); //**
//
//        // Kiem tra 1 element da duoc chon hay chua
//        // Ap dung checkbox/ radio/ dropdown
//        element.isSelected();
//
//        // Kiem tra 1 element co cho phep thao tac hay khong
//        // VD: cho phep sua du lieu (true = duoc phep chinh sua) (false = khong duoc phep chinh sua)
//        // Test tinh nang ve role n permission
//        element.isEnabled(); //*
    @Test
    public void TC_01_Displayed() {
        // Displayed # Undisplayed
        // User co the nhin thay
        driver.get("https://automationfc.github.io/basic-form/index.html");

        if (driver.findElement(By.cssSelector("input#mail")).isDisplayed()) {
            driver.findElement(By.cssSelector("input#mail")).sendKeys("Automation Testing");
            System.out.println("Email Textbox is displayed");
        } else {
            System.out.println("Email textbox is not displayed");
        }

        if (driver.findElement(By.cssSelector("textarea#edu")).isDisplayed()) {
            driver.findElement(By.cssSelector("textarea#edu")).sendKeys("Automation Testing");
            System.out.println("Text area for Edu is displayed");
        } else {
            System.out.println("Text area for Edu is not displayed");
        }

        if (driver.findElement(By.cssSelector("input#under_18")).isDisplayed()) {
            driver.findElement(By.cssSelector("input#under_18")).click();
            System.out.println("Age under 18 Radio is displayed");
        } else {
            System.out.println("Age under 18 Radio is not displayed");
        }

        if (driver.findElement(By.xpath("//h5[text()='Name: User5']")).isDisplayed()) {
            driver.findElement(By.xpath("//h5[text()='Name: User5']/following-sibling::a")).click();
            System.out.println("Name User 5 is displayed");
        } else {
            System.out.println("Name User 5 is not displayed");
        }

    }

    @Test
    public void TC_02_Enabled() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        // Enabled # Disabled
        // User thao tac duoc --> thuong dung de kiem tra tinh nang role & permission
        if (driver.findElement(By.cssSelector("input#mail")).isEnabled()) {
            System.out.println("Email Textbox is enabled");
        } else {
            System.out.println("Email textbox is disabled");
        }

        if (driver.findElement(By.cssSelector("input#disable_password")).isEnabled()) {
            System.out.println("Password Textbox is enabled");
        } else {
            System.out.println("Password textbox is disabled");
        }

        if (driver.findElement(By.cssSelector("input#slider-2")).isEnabled()) {
            System.out.println("Slider is enabled");
        } else {
            System.out.println("Slider is disabled");
        }
    }

    @Test
    public void TC_03_Selected() {
        // Selected # Unselected
        // User da chon hoac bo chon
        driver.get("https://automationfc.github.io/basic-form/index.html");

        // click chon
        driver.findElement(By.xpath("//input[@value='under_18']")).click();
        driver.findElement(By.xpath("//input[@value='java']")).click();

        // verify selected
        if (driver.findElement(By.xpath("//input[@value='under_18']")).isSelected()) {
            System.out.println("Age Under 18 is Selected");
        } else {
            System.out.println("Age Under 18 is not selected");
        }

        if (driver.findElement(By.xpath("//input[@value='java']")).isSelected()) {
            System.out.println("Java is Selected");
        } else {
            System.out.println("Java is not selected");
        }

        // click bo chon
            // radio click option khac thi bo chon
        driver.findElement(By.xpath("//input[@value='over_18']")).click();
            // check box click lai thi bo chon
        driver.findElement(By.xpath("//input[@value='java']")).click();

        // verify deselected
        if (driver.findElement(By.xpath("//input[@value='under_18']")).isSelected()) {
            System.out.println("Age Under 18 is Selected");
        } else {
            System.out.println("Age Under 18 is not selected");
        }

        if (driver.findElement(By.xpath("//input[@value='java']")).isSelected()) {
            System.out.println("Java is Selected");
        } else {
            System.out.println("Java is not selected");
        }
    }

    @Test
    public void TC_04_ComboMailchimp() throws InterruptedException {
        // Email Mkt
        driver.get("https://login.mailchimp.com/signup/");

        By signupButton = By.cssSelector("button#create-account-enabled");

        Thread.sleep(2000);
        driver.findElement(By.cssSelector("div#onetrust-close-btn-container > button[aria-label='Close']")).click();
        Thread.sleep(2000);

        driver.findElement(By.cssSelector("input#email")).sendKeys("automationtesting@gmail.net");
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(signupButton));
        driver.findElement(signupButton).click();
        Thread.sleep(2000);

        // delay cua java
            // neu fail se throw exception --> phai add exception vao method signature
        Thread.sleep(3000);

        // empty
        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='8-char not-completed'")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.username-check.not-completed")).isDisplayed());

        // lowercase
        driver.findElement(By.cssSelector("input#new_password")).sendKeys("sele");
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(signupButton));
        driver.findElement(signupButton).click();
        Thread.sleep(3000);

        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='8-char not-completed'")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.username-check.completed")).isDisplayed());

        // uppercase
            // clear du lieu cu truoc khi send key lai
        driver.findElement(By.cssSelector("input#new_password")).clear();
        driver.findElement(By.cssSelector("input#new_password")).sendKeys("SELE");
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(signupButton));
        driver.findElement(signupButton).click();
        Thread.sleep(3000);

        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='8-char not-completed'")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.username-check.completed")).isDisplayed());

        // numbercase
        driver.findElement(By.cssSelector("input#new_password")).clear();
        driver.findElement(By.cssSelector("input#new_password")).sendKeys("123456");
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(signupButton));
        driver.findElement(signupButton).click();
        Thread.sleep(3000);

        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='8-char not-completed'")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.username-check.completed")).isDisplayed());

        // specialcase
        driver.findElement(By.cssSelector("input#new_password")).clear();
        driver.findElement(By.cssSelector("input#new_password")).sendKeys("!!@@##");
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(signupButton));
        driver.findElement(signupButton).click();
        Thread.sleep(3000);

        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='8-char not-completed'")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.username-check.completed")).isDisplayed());

        // Username
        driver.findElement(By.cssSelector("input#new_password")).clear();
        driver.findElement(By.cssSelector("input#new_password")).sendKeys("auto");
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(signupButton));
        driver.findElement(signupButton).click();
        Thread.sleep(3000);

        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='8-char not-completed'")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.username-check.not-completed")).isDisplayed());

        // 8 chars
        driver.findElement(By.cssSelector("input#new_password")).clear();
        driver.findElement(By.cssSelector("input#new_password")).sendKeys("AUTOMATION");
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(signupButton));
        driver.findElement(signupButton).click();
        Thread.sleep(3000);

        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='8-char completed'")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.username-check.not-completed")).isDisplayed());

        // full
        driver.findElement(By.cssSelector("input#new_password")).clear();
        driver.findElement(By.cssSelector("input#new_password")).sendKeys("Selenium123###");
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(signupButton));
        driver.findElement(signupButton).click();
        Thread.sleep(3000);

        Assert.assertFalse(driver.findElement(By.cssSelector("li.lowercase-char.completed")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.cssSelector("li.uppercase-char.completed")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.cssSelector("li.number-char.completed")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.cssSelector("li.special-char.completed")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.cssSelector("li[class='8-char completed'")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.cssSelector("li.username-check.completed")).isDisplayed());

        Assert.assertFalse(driver.findElement(By.cssSelector("input#marketing_newsletter")).isSelected());
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
