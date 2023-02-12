package e2e;

import com.google.common.io.Files;
import e2e.helpers.ContactHelpers;
import e2e.helpers.LoginHelpers;
import e2e.helpers.RegisterHelpers;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class AplicationManager {

    public WebDriver driver;

    LoginHelpers login;
    RegisterHelpers register;
    ContactHelpers contact;


    public LoginHelpers getLogin() {
        return login;
    }

    public RegisterHelpers getRegister() {
        return register;
    }

    public ContactHelpers getContact() {
        return contact;
    }


    protected void init() {
        driver = new ChromeDriver();                                //указываем драйвер кот будем использолвать
        driver.get("http://phonebook.telran-edu.de:8080/");
        driver.manage().window();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        login = new LoginHelpers(driver);
        register = new RegisterHelpers(driver);
        contact = new ContactHelpers(driver);
    }

    public String takeScreenshot() throws IOException {
        File tmp = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File sreenshot = new File("reference/screen" + System.currentTimeMillis() + ".png");

        Files.copy(tmp, sreenshot);
        return sreenshot.getAbsolutePath();
    }

    protected void stop() {
        if (driver != null) {
            driver.quit();
        }
    }
}
