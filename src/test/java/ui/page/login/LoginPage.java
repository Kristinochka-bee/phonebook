package ui.page.login;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ui.page.PageBase;

public class LoginPage extends PageBase { //класс описывающий страницу

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "defaultRegisterFormEmail")
    private WebElement emailInput;

    @FindBy(xpath = "//input[@name ='password']")
    private WebElement passwordInput;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement loginButton;

    @FindBy(xpath = "//div[@id=\"error-message\"]")
    private WebElement errorLoginPasswordMessage;

    public void getLogin(String email, String password) {
        emailInput.clear();
        emailInput.sendKeys(email);
        passwordInput.clear();
        passwordInput.sendKeys(password);
        loginButton.click();
    }

    public String getErrorLoginPasswordMessage() {
        return errorLoginPasswordMessage.getText();
    }
}
