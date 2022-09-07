package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

    protected WebDriver driver;


    public BasePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public void titleContainsWait(String expectedText){
        WebDriverWait wait  = new WebDriverWait(driver, Duration.ofMillis(10000));
        wait.until(ExpectedConditions.titleContains(expectedText));
    }

    public void elementClickableWait(WebElement element){
        WebDriverWait wait  = new WebDriverWait(driver, Duration.ofMillis(10000));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }


}
