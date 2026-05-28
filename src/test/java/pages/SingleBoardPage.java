package pages;

import io.qameta.allure.Allure;
import locators.SingleBoardLocators;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import base.CommonTest;

import java.time.Duration;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class SingleBoardPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By ADD_LIST_BUTTON = By.xpath("//*[@data-testid='list-composer-button' and contains(text(), 'Dodaj kolejną listę')]");

    // Input field for the new list name
    private final By NEW_LIST_INPUT = By.xpath("//div[contains(@class, 'MwwP5nu2toWaoN')]//textarea[@data-testid='list-name-textarea']");

    // Confirm button after entering list name
    private final By CONFIRM_ADD_LIST_BUTTON = By.xpath("//button[@data-testid='list-composer-add-list-button']");

    // Global button to add a card (used after entering card name)
    private final By ADD_CARD_BUTTON_GLOBAL = By.xpath("//*[contains(@class, 'SEj5vUdI3VvxDc') and contains(text(), 'Dodaj Kartę')]");

    // Card input field inside list container
    private final By CARD_TEXT_FIELD = By.xpath(".//ancestor::li[@data-testid='list-wrapper']//form//textarea[@data-testid='list-card-composer-textarea']");

    // The outer container for a list item
    private final By LIST_CONTAINER = By.xpath(".//ancestor::li[@data-testid='list-wrapper']");

    // The drop target area for drag-and-drop (usually the <ol> inside a list)
    private final By LIST_CARD_DROP_TARGET = By.xpath(".//ol");

    // Add-card button within a specific list at the bottom of it
    private final By ADD_CARD_BUTTON_RELATIVE = By.xpath(".//ancestor::li[@data-testid='list-wrapper']//button[@data-testid='list-add-card-button']");

    // Find all card links within a list (for getAllListItems)
    private final By ALL_CARDS_IN_LIST = By.xpath(".//ol//li//a");

    // Dynamic: find a list header by its name
    private By listHeaderByName(String name) {
        return By.xpath(String.format("//h2[@data-testid='list-name' and contains(text(), '%s')]", name));
    }

    // Dynamic: find a card inside a list by its name
    private By cardByNameInList(String name) {
        return By.xpath(String.format(".//ol//li//a[contains(text(), '%s')]", name));
    }

    //3 dots button on top bar
    private final By THREE_DOTS_BUTTON = By.xpath("//div[contains(@class,'board-header u-clearfix js-board-header')]//span[contains(@data-testid, 'OverflowMenuHorizontalIcon')]");

    //3 dots menu close table
    private final By CLOSE_TABLE_BUTTON = By.xpath("//button[.//div[normalize-space()='Zamknij tablicę']]");

    //3 dots menu close conifrm
    private final By CONFIRM_CLOSE_TABLE_BUTTON = By.xpath("//button[contains(@data-testid, 'popover-close-board-confirm')]");

    private final By PERMANENTLY_DELETE_TABLE_BUTTON = By.xpath("//button[contains(@data-testid, 'close-board-delete-board-button')]");


    public SingleBoardPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public WebElement getListElementByName(String listName) {
        return driver.findElement(SingleBoardLocators.listHeaderByName(listName));
    }

    public WebElement getListContainer(WebElement listHeader) {
        return listHeader.findElement(SingleBoardLocators.LIST_CONTAINER);
    }

    public WebElement getAddCardButtonForList(WebElement listHeader) {
        return wait.until(ExpectedConditions.elementToBeClickable(
                listHeader.findElement(SingleBoardLocators.ADD_CARD_BUTTON_RELATIVE)
        ));
    }

    public WebElement getCardTextField() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(SingleBoardLocators.CARD_TEXT_FIELD));
    }

    public List<WebElement> getAllListItems(String listName) {
        WebElement listHeader = getListElementByName(listName);
        WebElement listContainer = getListContainer(listHeader);
        return wait.until(ExpectedConditions.visibilityOfAllElements(
                listContainer.findElements(SingleBoardLocators.ALL_CARDS_IN_LIST)
        ));
    }

    public List<WebElement> getListItemsByName(String listName, String itemName) {
        WebElement listHeader = getListElementByName(listName);
        WebElement listContainer = getListContainer(listHeader);
        return wait.until(ExpectedConditions.visibilityOfAllElements(
                listContainer.findElements(SingleBoardLocators.cardByNameInList(itemName))
        ));
    }

    public void assertItemExistsInList(String listName, String itemName) {
        List<WebElement> items = getAllListItems(listName);
        Allure.step("Checking if item '" + itemName + "' exists in list '" + listName + "'");
        boolean found = items.stream()
                .anyMatch(item -> item.getText().trim().equals(itemName));
        assertTrue(found, "Item '" + itemName + "' was not found in list '" + listName + "'");
    }

    public void addList(String listName) {
        List<WebElement> addListButtons = driver.findElements(SingleBoardLocators.ADD_LIST_BUTTON);
        if (!addListButtons.isEmpty()) {
            addListButtons.getFirst().click();
        }

        Allure.step("Enter list name: " + listName);
        WebElement nameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(SingleBoardLocators.NEW_LIST_INPUT));
        nameInput.sendKeys(listName);
        wait.until(ExpectedConditions.attributeToBe(nameInput, "value", listName));

        WebElement confirmButton = wait.until(ExpectedConditions.elementToBeClickable(SingleBoardLocators.CONFIRM_ADD_LIST_BUTTON));
        Allure.step("Click confirm button");
        confirmButton.click();

        Allure.step("Verify list was added");
        getListElementByName(listName);
    }

    public void addListItem(String listName, String itemName) {
        Allure.step("Click add card button in list");
        WebElement listHeader = getListElementByName(listName);

        CommonTest.Wait(500);
        List<WebElement> elements = listHeader.findElements(SingleBoardLocators.ADD_CARD_BUTTON_RELATIVE);
        if (!elements.isEmpty()) {
            WebElement first = elements.getFirst();
            wait.until(ExpectedConditions.elementToBeClickable(first));
            first.click();
        }

        CommonTest.Wait(500);
        WebElement textField = getCardTextField();
        textField.sendKeys(itemName);
        CommonTest.Wait(500);
        wait.until(ExpectedConditions.attributeToBe(textField, "value", itemName));

        WebElement addCardButton = wait.until(ExpectedConditions.elementToBeClickable(SingleBoardLocators.ADD_CARD_BUTTON_GLOBAL));
        Allure.step("Click confirm card add button");
        addCardButton.click();

        assertItemExistsInList(listName, itemName);
    }

    public void dragAndDropCardItem(String sourceListName, String itemName, String targetListName) {
        WebElement sourceListHeader = getListElementByName(sourceListName);
        WebElement targetListHeader = getListElementByName(targetListName);

        WebElement sourceListContainer = getListContainer(sourceListHeader);
        WebElement targetListContainer = getListContainer(targetListHeader);

        WebElement cardToDrag = sourceListContainer.findElement(SingleBoardLocators.cardByNameInList(itemName));
        WebElement dropTarget = targetListContainer.findElement(SingleBoardLocators.LIST_CARD_DROP_TARGET);

        Allure.step("Dragging card '" + itemName + "' from '" + sourceListName + "' to '" + targetListName + "'");
        new Actions(driver)
                .clickAndHold(cardToDrag)
                .moveToElement(dropTarget)
                .pause(Duration.ofMillis(500))
                .release()
                .build() //generates a composite action containing all actions so far
                .perform(); // executes action

        assertItemExistsInList(targetListName, itemName);
    }

    public void deleteBoard(String boardName){

        TopBarPage topBarPage = new TopBarPage(this.driver);
        topBarPage.search(boardName);
        wait.until(ExpectedConditions.elementToBeClickable(THREE_DOTS_BUTTON)).click();

        CommonTest.Wait(3000);
        System.out.println(driver.getPageSource().contains("Zamknij tablicę"));

        List<WebElement> elements = driver.findElements(
                By.xpath("//*[contains(normalize-space(),'Zamknij tablicę')]")
        );

        WebElement closeBoardButton = wait.until(ExpectedConditions.presenceOfElementLocated(CLOSE_TABLE_BUTTON));

        //((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", closeBoardButton);

        wait.until(ExpectedConditions.elementToBeClickable(closeBoardButton)).click();

        wait.until(ExpectedConditions.elementToBeClickable(CONFIRM_CLOSE_TABLE_BUTTON)).click();

    }


}
