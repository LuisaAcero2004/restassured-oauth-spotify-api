package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AuthPage extends BasePage{

    public AuthPage(WebDriver driver){
        super(driver);
    }

    @FindBy(xpath = "//*[@data-testid='auth-accept']")
    private WebElement authBtn;

    public void grantAuthorization(){
        elementClickableWait(authBtn);
        authBtn.click();
    }


}
