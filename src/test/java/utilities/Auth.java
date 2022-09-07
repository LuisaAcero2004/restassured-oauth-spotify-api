package utilities;

import org.openqa.selenium.WebDriver;
import pages.AuthPage;
import pages.LoginPage;
import pages.RedirectPage;
import utilities.driverFactory.ChromeManagerFactory;
import utilities.driverFactory.DriverManager;

public class Auth {

    private final DriverManager driverManager = new ChromeManagerFactory().createDriver();
    private final WebDriver driver = driverManager.newDriver();
    private final LoginPage loginPage = new LoginPage(driver);
    private final AuthPage authPage = new AuthPage(driver);
    private final RedirectPage redirectPage = new RedirectPage(driver);

    public String getAuthCode(String username, String password,String clientId){

        String authCodeUrl = "https://accounts.spotify.com/authorize?response_type=code&client_id="+clientId+"&scope=user-read-private%20playlist-read-collaborative%20playlist-modify-public%20playlist-read-private%20playlist-modify-private&redirect_uri=https%3A%2F%2Fdeveloper.spotify.com%2F";

        driver.get(authCodeUrl);
        loginPage.login(username, password);

        try{
            authPage.grantAuthorization();
        }catch(Exception e){
            //Continue if the authorization was previously granted
        }

        redirectPage.getUrl();
        String authCode = redirectPage.getRedirectUri().substring(redirectPage.getRedirectUri().indexOf("=") + 1);
        driver.quit();

        return authCode;
    }

}
