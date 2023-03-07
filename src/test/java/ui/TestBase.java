package ui;

import api.tests.ApiBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class TestBase extends ApiBase {
    protected WebDriver driver;
    protected final String EMAIL = "test@gmail.com";
    protected final String PASSWORD = "test@gmail.com";

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("http://phonebook.telran-edu.de:8080/user/login");
    }


    @AfterMethod(alwaysRun = true)
    public void testInformation() {
        driver.quit();
    }
}
