package ui.page.contact;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ui.page.PageBase;

@Getter

public class PhoneTab extends PageBase {
    public PhoneTab(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//button[@id=\"btn-add-phone\"]")
    WebElement addPhoneNumber;

    @FindBy(xpath = "//select[@id=\"cc-select\"]")
    WebElement countryCodeSelect;

    @FindBy(xpath = "//span[@class=\"input-group-text\"]")
    WebElement countryCodeField;

    @FindBy(xpath = "//input[@id=\"selected-cc\"]")
    WebElement phoneNumberInput;

    @FindBy(xpath = "//button[@class=\"btn btn-primary\"]")
    WebElement saveButton;

    @FindBy(xpath = "//ngb-alert[@role=\"alert\"]")
    WebElement alertField;

    @FindBy(xpath = "//td[@class=\"row-table-pn\"]")
    WebElement phoneNumberField;

    //for negative test


}
