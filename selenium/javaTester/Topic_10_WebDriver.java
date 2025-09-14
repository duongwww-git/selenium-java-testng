package javaTester;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

import java.rmi.Remote;

public class Topic_10_WebDriver {
    //1 --> chay duoc nhieu trinh duyet
    WebDriver driver;

    //2
    FirefoxDriver ffDriver;

    //3
    RemoteWebDriver rmDriver;

    AbsClass absClass;

    Capabilities capabilities;

    @Test
    public void testDriver(){

    //1 - khong new Interface duoc

    //2 - new class bthng (mien kp abs class)
        ffDriver = new FirefoxDriver();

    //3 - class is protected --> can only new contractors with params
        rmDriver = new RemoteWebDriver(capabilities);
    }
}
