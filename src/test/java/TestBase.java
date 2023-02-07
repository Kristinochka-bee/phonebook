import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

public class TestBase {   //Этот класс базовый , другие классы могут от него наследоваться *(в этом классе указаны аннотации кот мы можем использовать в др классах)
    WebDriver driver;

    public static Logger logger() {
        return LoggerFactory.getLogger(TestBase.class);
    }

    @BeforeClass
    public static void setUp() {
        WebDriverManager.chromedriver().setup();                      //Здесь можно указывать любой вид драйвера
        logger().info("Setup chrome driver ");    //будет показываться лог с методом

    }

    @BeforeMethod
    public void setupTest() {
        driver = new ChromeDriver();                                //указываем драйвер кот будем использолвать
        driver.get("http://phonebook.telran-edu.de:8080/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        logger().info("Start start");
    }

    public void fillField(String userData, By locator) {   //этот метод можно использовать в тех тестах,где он будет нужен. Заполнение полей
        driver.findElement(locator).click();
        driver.findElement(locator).sendKeys(userData);
    }

    public boolean isElementPresent(By by) {
        try {  //пробуем найти элемент
            driver.findElement(by); //если нашли ,значит трие
            return true;
        } catch (NoSuchElementException exception) { //иначе отлови в переменную exeption *исключение
            exception.printStackTrace(); //в терминале будет выводится ошибка
            return false;
        }
    }

    public boolean isElementClickable(By by) {
        try {  //пробуем найти элемент
            driver.findElement(by).click(); //если нашли ,значит трие
            return true;
        } catch (NoSuchElementException exception) { //иначе отлови в переменную exeption *исключение
            exception.printStackTrace(); //в терминале будет выводится ошибка
            return false;
        }
    }

    public void checkItemText(By locator, String expectedText, String err) {
        String actualErrorMessage = driver.findElement(locator).getText();
        Assert.assertEquals(actualErrorMessage, expectedText, err);
    }

    //after
    @AfterMethod
    public void tearDown() throws InterruptedException {
        Thread.sleep(10000);
        if (driver != null) {
            driver.quit();
        }
        logger().info("Stop test");
    }

}
