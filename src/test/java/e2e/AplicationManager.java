package e2e;

import com.google.common.io.Files;
import e2e.helpers.CreateContactHelpers;
import e2e.helpers.EditeContactHelpers;
import e2e.helpers.LoginHelpers;
import e2e.helpers.RegisterHelpers;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.util.concurrent.TimeUnit;

public class AplicationManager {

    public WebDriver driver;

    LoginHelpers login;
    RegisterHelpers register;

    CreateContactHelpers createContactHelpers;
    EditeContactHelpers editContactHelpers;


    public LoginHelpers getLogin() {
        return login;
    }

    public RegisterHelpers getRegister() {
        return register;
    }


    public CreateContactHelpers getCreateContact() {   //generating Getter for to use it in test
        return createContactHelpers;
    }

    public EditeContactHelpers getEditContact() {
        return editContactHelpers;
    }

    public WebDriver remoteDriverSelenoid() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName("firefox");
        capabilities.setVersion("90.0");
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableLog", true);
        driver = new RemoteWebDriver(
                URI.create("http://127.0.0.1:4444/wd/hub").toURL(),
                capabilities);
        return driver;
    }


    protected void init() throws MalformedURLException {
//        WebDriverManager.chromedriver().setup();
//        driver = new ChromeDriver();
        driver = remoteDriverSelenoid();

        driver.get("http://phonebook.telran-edu.de:8080/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        login = new LoginHelpers(driver);
        register = new RegisterHelpers(driver);
        createContactHelpers = new CreateContactHelpers(driver);
        editContactHelpers = new EditeContactHelpers(driver);
    }

    public String takeScreenshot() throws IOException {
        File tmp = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File sreenshot = new File("reference/screen" + System.currentTimeMillis() + ".png");

        Files.copy(tmp, sreenshot);
        return sreenshot.getAbsolutePath();
    }

    protected void stop() {
        driver.quit();
    }
}
