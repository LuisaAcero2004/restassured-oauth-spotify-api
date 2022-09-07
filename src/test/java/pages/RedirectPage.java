package pages;

import org.openqa.selenium.WebDriver;

public class RedirectPage extends BasePage{

    private String redirectUri;

    public RedirectPage(WebDriver driver){
        super(driver);
    }

    public void getUrl(){
        titleContainsWait("Home | Spotify for Developers");
        setRedirectUri(driver.getCurrentUrl());
    }

    public String getRedirectUri() {
        return redirectUri;
    }

    public void setRedirectUri(String redirectUri) {
        this.redirectUri = redirectUri;
    }
}
