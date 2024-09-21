package javaTester;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.ArrayList;
import java.util.List;

public class Topic_01_DataType {
    // bài toán là quản lí 1 cty về IT
    // Thông tin về cty: tên, đchi, MST
    // Tên cty là gì?
    // có bao nhiêu phòng ban?
    // có bao nhiêu nhân viên
    // Thông tin về nhân viên: Tên/ Tuổi/ Địa chỉ/ Giới tính/ MST/ ..
    // Giờ giấc làm việc
    // Lương/ thưởng
    // 25.8tr

    // Tên người có dùng số để đặt tên?
    // Quản lí trường học/ khách sạn/ kho bãi
    // Công ty/ Quán ăn/ bệnh viện/..

    //1 kiểu dữ liệu sẽ được sử dụng cho 1 thông tin/ thuộc tính nào
    // Mỗi ngôn ngữ lập trình sẽ quy ước kiểu dữ liệu khác nhau

    // Java có 2 kiểu dữ liệu
    // 1 - Kiểu dữ liệu nguyên thuỷ (Primitive Type}
    // 8 kiểu đại diện (chia ra gồm 4 nhóm)
        // Kiểu kí tự (đại diện cho 1 kí tự)
        char c = 'B';
        // (kết thúc một đoạn code phải có dấu ";")

        // Kiểu số nguyên (ko có thập phân)
        byte bNumber = 10;
        short sNumber = 100;
        int iNumber = 70000;
        long lNumber = 1000000;

        // Kiểu số thực (có thập phân)
        float fNumber = 10.5f;
        double dNumber = 15.8d;
        // chữ f và d đằng sau để phân biệt float và double

        // Kiểu logic
        boolean sex = true;

    // 2 - Kiểu dữ liệu tham chiếu (Reference Type)
        // kiểu mảng (Array)
        // mảng kiểu string và int
        String[] studentName = {"Nguyễn Văn Nam", "Lê Văn Cầu", "Hoàng Thị Loan"};
        int[] studentAge = {25, 22, 28};

        // Kiểu object (có thể đại diện cho các kiểu dữ liệu khác)
        // Đối tượng => chuyển đổi qua các kiểu dữ liệu khác
        Object studentAddress = "123 PO Box";
        Object employeeAge = 35;
        Object employeeSex = false;

        // kiểu chuỗi (string - tập hợp của kiểu kí tự)
        String name = "Bình";
        String employeeNumber = "123456789";

        // Kiểu class
        FirefoxDriver ffDriver = new FirefoxDriver();
        // public class FirefoxDriver

        // interface
        WebDriver driver = new ChromeDriver();
        // public interface WebDriver

        // collection
        List<WebElement> textboxes = driver.findElements(By.cssSelector(""));

        ArrayList<String> studentCity = new ArrayList<String>();
}
