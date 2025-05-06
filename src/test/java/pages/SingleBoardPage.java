package pages;

import io.qameta.allure.Allure;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import base.CommonTest;
import java.time.Duration;
import java.util.List;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class SingleBoardPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    @FindBy(xpath = "//*[@data-testid='list-composer-button' and contains(text(), 'Dodaj kolejną listę')]" )
    private List<WebElement> addListButton;

    @FindBy(xpath = "//div[contains(@class, 'MwwP5nu2toWaoN')]//textarea[@data-testid='list-name-textarea']") // class MwwP5nu2toWaoN is always new item
    private WebElement newListInputNameField;

    @FindBy(xpath = "//button[@data-testid='list-composer-add-list-button']")
    private WebElement addListButtonConfirm;

    @FindBy(xpath = "//*[contains(@class, \"bxgKMAm3lq5BpA\") and contains(@class, \"SdamsUKjxSBwGb\") and contains(@class, \"SEj5vUdI3VvxDc\") and contains(text(), \"Dodaj Kartę\")]")
    private WebElement addCardButton;

    public SingleBoardPage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // 👈 Initialize wait
        PageFactory.initElements(driver, this);
    }

    public WebElement findList(String listName){
        return driver.findElement(By.xpath("//h2[@data-testid='list-name' and contains(., '" + listName + "')]"));
    }

    public void addList(String listName){
        if (!addListButton.isEmpty()){
            addListButton.getFirst().click();
        }
        Allure.step("Enter list name: " + listName);
        newListInputNameField.sendKeys(listName);
        wait.until(ExpectedConditions.attributeToBe(newListInputNameField, "value", listName));
        wait.until(ExpectedConditions.elementToBeClickable(addListButtonConfirm));
        Allure.step("click confirm button");
        addListButtonConfirm.click();
        WebElement list = getListElementByName(listName);
    }

    public WebElement getListElementByName(String listName) {
        String xpath = String.format("//h2[@data-testid='list-name' and contains(text(), '%s')]", listName);
        return driver.findElement(By.xpath(xpath));
    }

    public WebElement getAddCardButtonForList(WebElement listHeader) {
        return wait.until(ExpectedConditions.elementToBeClickable(
                listHeader.findElement(By.xpath(".//ancestor::li[@data-testid='list-wrapper']//button[@data-testid='list-add-card-button']"))
        ));
    }

    public WebElement getCardTextField() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath(".//ancestor::li[@data-testid='list-wrapper']//form//textarea[@data-testid='list-card-composer-textarea']")
        ));
    }

    public WebElement getListContainer(WebElement listHeader){
        return listHeader.findElement(By.xpath(".//ancestor::li[@data-testid='list-wrapper']"));
    }

    public List<WebElement> getAllListItems(String listName) {
        WebElement listHeader = getListElementByName(listName);
        WebElement listContainer = getListContainer(listHeader);
        String itemXPath = ".//ol//li//a"; // Match all cards inside the list
        return wait.until(ExpectedConditions.visibilityOfAllElements(
                listContainer.findElements(By.xpath(itemXPath))
        ));
    }

    public List<WebElement> getListItemsByName(String listName, String itemName) {
        WebElement listHeader = getListElementByName(listName);
        WebElement listContainer = getListContainer(listHeader);
        String itemXPath = String.format(".//ol//li//a[contains(text(), '%s')]", itemName);
        return wait.until(ExpectedConditions.visibilityOfAllElements(
                listContainer.findElements(By.xpath(itemXPath))
        ));
    }

    public void assertItemExistsInList(String listName, String itemName) {
        List<WebElement> items = getAllListItems(listName);
        Allure.step("Checking if item '" + itemName + "' exists in list '" + listName + "'");
        boolean found = items.stream()
                .anyMatch(item -> item.getText().trim().equals(itemName));
        assertTrue(found, "Item '" + itemName + "' was not found in list '" + listName + "'");
    }

    public void addListItem(String listName, String itemName) {
        Allure.step("Click add list button");
        WebElement listHeader = getListElementByName(listName);
        WebElement add = getAddCardButtonForList(listHeader);
        add = wait.until(ExpectedConditions.elementToBeClickable(add));
        add.click();
        Allure.step("Fill text with list name");
        CommonTest.Wait(500);
        WebElement textField = getCardTextField();
        textField.sendKeys(itemName);
        CommonTest.Wait(500);
        wait.until(ExpectedConditions.attributeToBe(textField, "value", itemName));
        Allure.step("Click add card button");
        addCardButton.click();
        assertItemExistsInList(listName, itemName);
    }

}
