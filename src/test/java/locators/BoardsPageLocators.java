package locators;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class BoardsPageLocators {
    @FindBy(xpath = "//*[@id='header']//button//p[normalize-space(text())='Utwórz']")
    protected WebElement createButton;

    @FindBy(xpath = "//*[contains(text(), 'Utwórz tablicę')]")
    protected WebElement selectBoardType;

    @FindBy(xpath = "//section//form//div[1]//label//input")
    protected WebElement tableNameInputField;

    @FindBy(xpath = "//form//button[normalize-space(text())='Utwórz']")
    protected WebElement createBoard;

    @FindBy(xpath = "//h1[normalize-space(text())='testTable']")
    protected WebElement tableName;

}
