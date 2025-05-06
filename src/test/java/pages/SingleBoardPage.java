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
        WebElement add = getAddCardButtonForList(listHeader);
        wait.until(ExpectedConditions.elementToBeClickable(add)).click();

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
                .build()
                .perform();

        assertItemExistsInList(targetListName, itemName);
    }
}
