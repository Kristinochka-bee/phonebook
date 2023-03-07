package ui.page.contact;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ui.page.PageBase;

public class PhoneTab extends PageBase {
    public PhoneTab(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//button[@id=\"btn-add-phone\"]")
    WebElement addPhoneNumber;

    @FindBy(xpath = "//select[@id=\"cc-select\"]")
    WebElement countryCodeSelect;

}
