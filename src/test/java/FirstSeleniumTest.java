import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class FirstSeleniumTest {
    WebDriver driver;
    By emailField = By.cssSelector("[placeholder=\"Email\"]");
    By passwordField = By.cssSelector("[placeholder=\"Password\"]");
    By confirmPassField = By.cssSelector("[placeholder=\"Confirm Password\"]");


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
        // driver.findElement(By.xpath("//input[@name='email']")).sendKeys("test@gmail.com");
        // driver.findElement(By.xpath("//input[@name = 'password']")).sendKeys("test@gmail.com");
        // driver.findElement(By.xpath("//button[contains(text(), 'Login')]")).click();
        // driver.findElement(By.xpath("//ul[@class = 'navbar-nav mr-auto']//li[1]")).click(); //выбираем из списка первый элемент
        // driver.findElement(By.xpath("//div[@class='list-group'][1]//button[@class='list-group-item']")).click();
        //driver.findElement(By.xpath("//input[@id='check-box-remove-contact']")).click();
        // driver.findElement(By.xpath("//button[@id='submit-remove']")).click();
        //driver.findElement(By.xpath("//button[@id = 'cancel-remove-contact']")).click();

        //Add new contact
        // driver.findElement(By.xpath("//a[contains(text(),'Добавить новый контакт')]")).click();
        // driver.findElement(By.xpath("//a[@aria-label=\"Close\"]")).click();  //кнопка закрыть
        /*
        driver.findElement(By.xpath("//input[@id='form-name']")).sendKeys("Gugu");
        driver.findElement(By.xpath("//input[@id='form-lastName']")).sendKeys("Risker");
        driver.findElement(By.xpath("//input[@id='form-about']")).sendKeys("From Ukraine");
        //driver.findElement(By.xpath("//button[@class = 'btn btn-primary']")).click();

        driver.findElement(By.xpath("//button[@class = 'btn btn-secondary mr-2']")).click();

         */

        //Select language
/*
        driver.findElement(By.xpath("//select[@id = \"langSelect\"]")).click();
        driver.findElement(By.xpath("//select//option[@value=\"uk\"]")).click();


 */
        //Account
        //driver.findElement(By.xpath("//button[@ng-reflect-router-link]")).click();

        //Logout
        //driver.findElement(By.xpath("(//button[normalize-space()='Ausloggen'])[1]")).click();    //---


    }

    @Test
    public void registerNewUser() {
        String userData = "test67@gmail.com";
        driver.findElement(By.id("login-form")).isDisplayed(); //проверка того,что этот локатор виден, что форма d[jlf отображается
        driver.findElement(By.cssSelector("[href=\"/user/registration\"]")).click();
        driver.findElement(By.id("registration-form")).isDisplayed(); //проверка того,что этот локатор виден, что форма регистрации отображается

        fillField(userData, emailField);
        fillField(userData, passwordField);
        fillField(userData, confirmPassField);


        driver.findElement(By.name("confirm-password")).sendKeys(userData);
        driver.findElement(By.xpath("//*[@type=\"submit\"]")).click();
    }

    private void fillField(String userData, By cssSelector) {
        driver.findElement(cssSelector).click();
        driver.findElement(cssSelector).sendKeys(userData);

    }

    //after
/*
    @AfterMethod
    public void tearDown() throws InterruptedException {      //закрытие браузера
        Thread.sleep(1000);  //время ожидания. Ничего не будет делать в это время
        if (driver != null) {
            driver.quit(); //покинуть бразузер полностью
            // driver.close();  //закроет вкладку хрома
        }

    }

 */


}
