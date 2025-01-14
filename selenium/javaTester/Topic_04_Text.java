package javaTester;

import org.testng.Assert;

import java.util.Random;

public class Topic_04_Text {
    public static void main(String[] args) {
        String fullName = "Automation FC";
        System.out.println(fullName);

        fullName = "Automation Testing";
        System.out.println(fullName);

        String textItem = "\n" +
                "      Second Option\n" +
                "      ";

        Assert.assertEquals(textItem.trim(), "Second Option");

    }
}
