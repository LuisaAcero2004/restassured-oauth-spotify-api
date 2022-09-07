package utilities;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class SetProperties {

    private String clientId;
    private String clientSecret;
    private String tokenUrl;
    private String baseUrl;
    private String username;
    private String password;


    public SetProperties(){

        Properties properties = new Properties();

        try {
            properties.load(new FileReader("src/test/resources/properties.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //get values from properties file
        setClientId(properties.getProperty("clientId"));
        setClientSecret(properties.getProperty("clientSecret"));
        setTokenUrl(properties.getProperty("tokenUrl"));
        setBaseUrl(properties.getProperty("baseUrl"));
        setPassword(properties.getProperty("password"));
        setUsername(properties.getProperty("username"));

    }

    //getters and setters
    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getTokenUrl() {
        return tokenUrl;
    }

    public void setTokenUrl(String tokenUrl) {
        this.tokenUrl = tokenUrl;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
