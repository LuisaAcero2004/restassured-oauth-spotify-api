package utilities.driverFactory;

public class ChromeManagerFactory implements DriverManagerFactory {

    public DriverManager createDriver() {
        return new DriverManagerChrome();
    }
}
