package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{


    public LoginPage(WebDriver driver){
        super(driver);
    }

    @FindBy(id="login-username")
    private WebElement usernameTxt;

    @FindBy(id="login-password")
    private WebElement passwordTxt;

    @FindBy(id = "login-button")
    private WebElement loginBtn;

    public void setUsername(String username){
        elementClickableWait(usernameTxt);
        usernameTxt.sendKeys(username);
    }

    public void setPassword(String password){
        elementClickableWait(passwordTxt);
        passwordTxt.sendKeys(password);
    }

    public void clickLogin(){
        elementClickableWait(loginBtn);
        loginBtn.click();
    }

    public void login(String username,String password){
        setUsername(username);
        setPassword(password);
        clickLogin();

    }









}
