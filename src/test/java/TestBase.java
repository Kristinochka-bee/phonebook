import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

public class TestBase {   //Этот класс базовый , другие классы могут от него наследоваться *(в этом классе указаны аннотации кот мы можем использовать в др классах)
    WebDriver driver;

    @BeforeClass
    public static void setUp() {
        WebDriverManager.chromedriver().setup();                      //Здесь можно указывать любой вид драйвера
    }

    @BeforeMethod
    public void setupTest() {
        driver = new ChromeDriver();                                //указываем драйвер кот будем использолвать
        driver.get("http://phonebook.telran-edu.de:8080/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public void fillField(String userData, By locator) {   //этот метод можно использовать в тех тестах,где он будет нужен. Заполнение полей
        driver.findElement(locator).click();
        driver.findElement(locator).sendKeys(userData);
    }

    //after
    @AfterMethod
    public void tearDown() throws InterruptedException {
        Thread.sleep(10000);
        if (driver != null) {
            driver.quit();
        }
    }

}
