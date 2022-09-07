package utilities.driverFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.nio.file.Paths;

public class DriverManagerChrome implements DriverManager {


    public WebDriver newDriver() {
        //Set Chrome options for headless execution
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(true);

        //Set properties and create a new Chrome Driver
        String path = Paths.get(System.getProperty("user.dir"),"src/test/resources/drivers/chromedriver.exe").toString();
        System.setProperty("webdriver.chrome.driver",path);

        return new ChromeDriver(options);
    }
}
