package pages;

import base.BasePage;
import io.qameta.allure.Allure;
import locators.BoardsPageLocators;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BoardsPage extends BasePage {

    //private final By createButton = By.xpath("//*[@id='header']//button//p[normalize-space(text())='Utwórz']");
    private final By createButton = By.xpath("//button[@data-testid='header-create-menu-button']");
    private final By selectBoardType = By.xpath("//*[contains(text(), 'Utwórz tablicę')]");
    private final By tableNameInputField = By.xpath("//section//form//div[1]//input");
    private final By createBoard = By.xpath("//form//button[normalize-space(text())='Utwórz']");
    private final By tableName = By.xpath("//h1[normalize-space(text())='testTable']");
    private final By profileAvatar = By.id("header-member-menu-avatar");

    private final String tableTitle = "testTable";

    public BoardsPage(WebDriver driver){
        super(driver);
    }

    public void createBoard(){
        Allure.step("Click create button");
        click(createButton);

        Allure.step("Click create table option");
        click(selectBoardType);

        Allure.step("Enter table name: " + tableTitle);
        type(tableNameInputField, tableTitle);

        Allure.step("Wait for and click create to be clickable");
        click(createBoard);

        Allure.step("Verify board name is displayed");
        isVisible(tableName);
    }

    public boolean isUserLoggedIn(){
        return isVisible(profileAvatar);
    }
}
