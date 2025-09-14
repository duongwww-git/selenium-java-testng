package javaTester;

public class Topic_09_Property {
    public static void main(String[] args) {
        String osName = System.getProperty("os.name");
        String projectPath = System.getProperty("user.dir");
        System.out.println(osName);
        System.out.println(projectPath);

        String uploadFilePath = System.getProperty("user.dir")+"\\UploadFile\\";
        String oneFile = "one.webp";
        String twoFile = "two.webp";
        String threeFile = "three.webp";

        String oneFilePath = uploadFilePath + oneFile;
        String twoFilePath = uploadFilePath + twoFile;
        String threeFilePath = uploadFilePath + threeFile;

        System.out.println(oneFilePath);
        System.out.println(twoFilePath);
        System.out.println(threeFilePath);
    }
}


