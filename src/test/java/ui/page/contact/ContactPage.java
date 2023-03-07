package ui.page.contact;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ui.page.PageBase;

@Getter
public class ContactPage extends PageBase {
    public ContactPage(WebDriver driver) {
        super(driver);
    }

    private final String BASE_PAGE_URL = "http://phonebook.telran-edu.de:8080/contacts/";

    @FindBy(xpath = "//a[@id=\"ngb-nav-2\"]")
    private WebElement infoTab;

    @FindBy(xpath = "//a[@id=\"ngb-nav-3\"]")
    private WebElement phoneTab;

    @FindBy(xpath = "//a[@id=\"ngb-nav-4\"]")
    private WebElement emailTab;

    @FindBy(xpath = "//a[@id=\"ngb-nav-5\"]")
    private WebElement addressTab;

}
