package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SingleBoardPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    @FindBy(xpath = "//button[normalize-space(text())='Dodaj listę']" )
    private WebElement addAnotherListButton;

    @FindBy(xpath = "//div[contains(@class, 'MwwP5nu2toWaoN')]//textarea[@data-testid='list-name-textarea']") // class MwwP5nu2toWaoN is always new item
    private WebElement newListInputNameField;

    @FindBy(xpath = "//button[@data-testid='list-composer-add-list-button']")
    private WebElement addListButton;

    public SingleBoardPage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // 👈 Initialize wait
        PageFactory.initElements(driver, this);
    }

    public void addList(Boolean existingList, String listName){
        if (existingList){
            addAnotherListButton.click();
        }
        newListInputNameField.sendKeys(listName);
        wait.until(ExpectedConditions.attributeToBe(newListInputNameField, "value", listName));
        wait.until(ExpectedConditions.elementToBeClickable(addListButton));
        addListButton.click();
    }


    
    
    
}
