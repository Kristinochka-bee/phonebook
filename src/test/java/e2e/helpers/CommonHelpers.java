package e2e.helpers;

import e2e.utils.Recorder;
import org.monte.media.Format;
import org.monte.media.FormatKeys;
import org.monte.media.math.Rational;
import org.monte.screenrecorder.ScreenRecorder;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import static org.monte.media.FormatKeys.*;
import static org.monte.media.VideoFormatKeys.*;

public class CommonHelpers {
    WebDriver driver;

    public ScreenRecorder screenRecorder;
    public WebDriverWait wait;  //избавляемся от Threadsleep

    public CommonHelpers(WebDriver driver) {
        this.driver = driver;
    }

    //избавляемся от Threadsleep
    public WebDriverWait setWait() {
        wait = new WebDriverWait(driver, 10);
        return wait;
    }

    //запуск видео в корректном формате
    public void startRecording() throws IOException, AWTException {
        File file = new File("records");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); //будет give размер скриншота в  формате -->
        int width = screenSize.width;
        int hight = screenSize.height;

        Rectangle captureSize = new Rectangle(0, 0, width, hight); //screen size
        GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();  // recording with correct configurations

        screenRecorder = new Recorder(gc, captureSize, new Format(MediaTypeKey, FormatKeys.MediaType.FILE, MimeTypeKey, MIME_AVI), new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, ENCODING_AVI_MJPG, CompressorNameKey, ENCODING_AVI_MJPG, DepthKey, 24, FrameRateKey, Rational.valueOf(15), QualityKey, 1.0f, KeyFrameIntervalKey, 15 * 60), new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, "black", FrameRateKey, Rational.valueOf(30)), null, file, "MyVideo");
        screenRecorder.start();
    }

    //остановка записи
    public void stopRecording() throws IOException {
        screenRecorder.stop();
    }


    By errorMessageBlock = By.xpath("//*[text()[contains(.,'Contact save fail')]]");

    By errorForm = By.id("form-error-firstName");

    By firstNameFill = By.id("form-name");
    By lastNameFill = By.id("form-lastName");
    By descriptionFill = By.id("form-about");


    public void clickOnVisibleElement(By locator) {
        Assert.assertTrue(isElementPresent(locator));
        driver.findElement(locator).click();
    }

    public void openDialog(By locator) {
        clickOnVisibleElement(locator);
        setWait().until(ExpectedConditions.visibilityOfElementLocated  //избавляемся от Threadsleep
                (By.xpath("//*[@role='dialog']")));  //проверяем на видимость элемента
    }


    public void checkFieldsOnContactInfoAfterCreatedContact(String firstName, String lastName, String description) {
        checkItemText(By.id("contact-first-name"), firstName, "Actual first name is not equal expected first name");
        checkItemText(By.id("contact-last-name"), lastName, "Actual last name is not equal expected last name");
        checkItemText(By.id("contact-description"), description, "Actual description is not equal expected description");
    }

    public void goToContactPageAndFillFilterField(String firstName) {
        driver.findElement(By.xpath("//a[@class='navbar-brand']//*[name()='svg']")).click();
        fillField(firstName, By.xpath("//*[@placeholder='Search...']"));
    }

    public void checkCountRows(Number expectedCountRow) {
        Number actualCountRow = driver.findElements(By.xpath("//*[@id='contacts-list']//*[@class='list-group']")).size();
        Assert.assertEquals(actualCountRow, expectedCountRow);
    }


    public void checkErrorMsg(String expectedErrorMessageOfForm) {
        String actualErrorMeassage = driver.findElement(errorForm).getText();
        Assert.assertEquals(actualErrorMeassage, expectedErrorMessageOfForm);
    }


    public void fillField(String userData, By locator) {   //этот метод можно использовать в тех тестах,где он будет нужен. Заполнение полей
        driver.findElement(locator).click();
        driver.findElement(locator).clear();
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


}
