package ui.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainMenu extends PageBase {

    public MainMenu(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//ul[@class='navbar-nav mr-auto']/li[2]")
    private WebElement addNewContactMenuLink;

    public boolean isAddNewContactMenuLinkDisplayed() {
        return addNewContactMenuLink.isDisplayed();
    }

    public void clickToAddNewContactMenuLink() {
        addNewContactMenuLink.click();
    }
}
