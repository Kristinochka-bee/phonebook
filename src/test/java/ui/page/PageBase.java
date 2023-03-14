package ui.page;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.Random;

@Getter
public class PageBase {
    protected WebDriver driver;

    public PageBase(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void click(WebElement element) {
        element.click();
    }

    public void getPage(String pageUrl) {
        driver.get(pageUrl);
    }

    public String getText(WebElement element) {
        return element.getText();
    }

    public void selectOption(String optionName, WebElement selectElement) {
        Select select = new Select(selectElement);
        select.selectByVisibleText(optionName);
    }

    public void selectRandomOption(WebElement selectElement) {
        Select select = new Select(selectElement);
        List<WebElement> selectOptionList = select.getAllSelectedOptions();
        Random random = new Random();
        int randomIndex = random.nextInt(selectOptionList.size());
        String option = selectOptionList.get(randomIndex).getText();
        select.selectByVisibleText(option);
    }

    public void inputText(WebElement element, String text) {
        element.clear();
        element.sendKeys(text);
    }
}
