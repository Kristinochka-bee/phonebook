package e2e.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class CommonHelpers {

    By errorMessageBlock = By.xpath("//*[text()[contains(.,'Contact save fail')]]");
    //*[text()[contains(.,'Contact save fail')]]
    //app-toasts[@class='ngb-toasts'])[1]
    By errorForm = By.id("form-error-firstName");

    By firstNameFill = By.id("form-name");
    By lastNameFill = By.id("form-lastName");
    By descriptionFill = By.id("form-about");

    WebDriver driver;

    public void openAddNewContact() {
        driver.findElement(By.cssSelector("[href='/contacts']")).click();
        Assert.assertTrue(isElementPresent(By.xpath("//*[@role='dialog']")));
    }

    public void fillAddNewContactForm(String firstName, String lastName, String description) {
        fillField(firstName, By.xpath("//*[@role='dialog']//*[@placeholder='First name']"));
        fillField(lastName, By.xpath("//*[@role='dialog']//*[@placeholder='Last name']"));
        fillField(description, By.xpath("//*[@role='dialog']//*[@placeholder='About']"));
    }

    public void saveNewContact() throws InterruptedException {
        driver.findElement(By.xpath("//button[@class='btn btn-primary']")).click();
        Thread.sleep(1000);
        Assert.assertFalse(isElementPresent(By.xpath("//*[@role='dialog']")));
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
        Number actualCountRow = driver.findElements(By.xpath("//div[@id='contacts-list']")).size();
        Assert.assertEquals(actualCountRow, expectedCountRow);
    }

    public void clickOnInputFields() {
        driver.findElement(firstNameFill).click();
        driver.findElement(lastNameFill).click();
        driver.findElement(descriptionFill).click();
    }

    public void checkErrorMsg(String expectedErrorMessageOfForm) {
        String actualErrorMeassage = driver.findElement(errorForm).getText();
        Assert.assertEquals(actualErrorMeassage, expectedErrorMessageOfForm);
    }

    public CommonHelpers(WebDriver driver) {
        this.driver = driver;
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


}
