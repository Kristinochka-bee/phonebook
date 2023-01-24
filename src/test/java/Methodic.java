import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Methodic {


    public class FirstSeleniumTest {
        WebDriver driver;

        //before
        @BeforeClass
        public static void setUp() {
            WebDriverManager.chromedriver().setup();   //устанавливаем Chrome
        }

        @BeforeMethod
        //вызываем драйвер.                                      Если что то кладем в этот метод *переменные , тогда он не может быть статическим
        public void setupTest() {
            driver = new ChromeDriver();
            driver.get("http://phonebook.telran-edu.de:8080/");
            // driver.navigate().to("https://www.google.ru/");
            driver.manage().window().maximize();//manage управление размером окна
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);  //задаем время прогрузки.Время ожидания.Неявное ожидание. Если прошло больше 10 сек и не открылось нужное окно или ожидаемая кнопка, тогда тест падает
        }

        //test
        @Test
        public void loginWithValidData() {
            //driver.findElement(By.name("email")).sendKeys("123");        //By. по какому тиму ищем локатор- Найди по локатору name. sendKeys вводим значения 123
            //driver.findElement(By.cssSelector("[placeholder=\"Password\"]")).sendKeys("test");
            //driver.findElement(By.name("password")).sendKeys("test");    //By. по какому тиму ищем локатор- Найди по локатору name. sendKeys вводим значения 123
            driver.findElement(By.xpath("//input[@name='email']")).sendKeys("test@gmail.com");
            driver.findElement(By.xpath("//input[@name = 'password']")).sendKeys("test@gmail.com");
            //driver.findElement(By.xpath("//button[contains(text(), 'Login')]")).click();
            // driver.findElement(By.xpath("//ul[@class = 'navbar-nav mr-auto']//li[1]")).click(); //выбираем из списка первый элемент
            //driver.findElement(By.xpath("//div[@class='list-group'][1]//button[@class='list-group-item']")).click();
            driver.findElement(By.cssSelector(".btn.btn-info")).click();
/*
            driver.findElement(By.cssSelector("[placeholder=\"Email\"]"));
            driver.findElement(By.xpath("//input[@placeholder='Password']"));
            driver.findElement(By.xpath("//input[@name=\"confirm-password\"]"));
            driver.findElement(By.cssSelector(".btn.btn-info")).click();

 */
        }


        @Test
        public void registerNewUser() {
            String userData = "test67@gmail.com";
            driver.findElement(By.cssSelector("[\"[placeholder=\\\"Email\\\"]")).sendKeys(userData);
            driver.findElement(By.cssSelector("[placeholder=\"Password\"]")).sendKeys(userData);
        }
    }
}
