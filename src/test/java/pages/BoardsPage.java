package pages;

import io.qameta.allure.Allure;
import locators.BoardsPageLocators;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BoardsPage extends BoardsPageLocators {

    private final WebDriver driver;
    private final WebDriverWait wait;
    private final String tableTitle = "testTable";

    public BoardsPage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void createBoard(){
        Allure.step("Click create button");
        createButton.click();

        Allure.step("Click create table option");
        selectBoardType.click();

        Allure.step("Enter table name: " + tableTitle);
        tableNameInputField.sendKeys(tableTitle);

        Allure.step("Wait for and click create to be clickable");
        wait.until(ExpectedConditions.elementToBeClickable(createBoard)).click();

        Allure.step("Verify board name is displayed");
        wait.until(ExpectedConditions.visibilityOf(tableName));
        Assert.assertTrue("Table name is not shown", tableName.isDisplayed());
    }
}
