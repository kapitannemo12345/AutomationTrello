package pages;

import io.qameta.allure.Allure;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BoardsPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    @FindBy(xpath = "//*[@id='header']//button//p[normalize-space(text())='Utwórz']" )
    private WebElement createButton;

    @FindBy(xpath = "//*[contains(text(), 'Utwórz tablicę')]")
    private WebElement selectBoarType;

    @FindBy(xpath = "//section//form//div[1]//label//input")
    private WebElement tableNameInputField;

    @FindBy(xpath ="//form//button[normalize-space(text())='Utwórz']")
    private WebElement createBoard;

    @FindBy(xpath ="//h1[normalize-space(text())='testTable']")
    private WebElement tableName;

    String tableTitle = "testTable";

    public BoardsPage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // 👈 Initialize wait
        PageFactory.initElements(driver, this);
    }

    public void createBoard(){
        Allure.step("click create button");
        createButton.click();
        Allure.step("Click create table option");
        selectBoarType.click();
        Allure.step("Enter table name: " + tableTitle);
        tableNameInputField.sendKeys(tableTitle);
        Allure.step("Wait for and click create to be clickable");
        wait.until(ExpectedConditions.elementToBeClickable(createBoard)).click();
        Allure.step("Verify board name is displayed");
        wait.until(ExpectedConditions.visibilityOf(tableName)).isDisplayed();
    }

}
