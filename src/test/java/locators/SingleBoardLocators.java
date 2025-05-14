package locators;

import org.openqa.selenium.By;

public class SingleBoardLocators {

    // Button to add a new list
    public static final By ADD_LIST_BUTTON =
            By.xpath("//*[@data-testid='list-composer-button' and contains(text(), 'Dodaj kolejną listę')]");

    // Input field for the new list name
    public static final By NEW_LIST_INPUT =
            By.xpath("//div[contains(@class, 'MwwP5nu2toWaoN')]//textarea[@data-testid='list-name-textarea']");

    // Confirm button after entering list name
    public static final By CONFIRM_ADD_LIST_BUTTON =
            By.xpath("//button[@data-testid='list-composer-add-list-button']");

    // Global button to add a card (used after entering card name)
    public static final By ADD_CARD_BUTTON_GLOBAL =
            By.xpath("//*[contains(@class, 'SEj5vUdI3VvxDc') and contains(text(), 'Dodaj Kartę')]");

    // Card input field inside list container
    public static final By CARD_TEXT_FIELD =
            By.xpath(".//ancestor::li[@data-testid='list-wrapper']//form//textarea[@data-testid='list-card-composer-textarea']");

    // The outer container for a list item
    public static final By LIST_CONTAINER =
            By.xpath(".//ancestor::li[@data-testid='list-wrapper']");

    // The drop target area for drag-and-drop (usually the <ol> inside a list)
    public static final By LIST_CARD_DROP_TARGET =
            By.xpath(".//ol");

    // Add-card button within a specific list at the bottom of it
    public static final By ADD_CARD_BUTTON_RELATIVE =
            By.xpath(".//ancestor::li[@data-testid='list-wrapper']//button[@data-testid='list-add-card-button']");

    // Find all card links within a list (for getAllListItems)
    public static final By ALL_CARDS_IN_LIST =
            By.xpath(".//ol//li//a");

    // Dynamic: find a list header by its name
    public static By listHeaderByName(String name) {
        return By.xpath(String.format("//h2[@data-testid='list-name' and contains(text(), '%s')]", name));
    }

    // Dynamic: find a card inside a list by its name
    public static By cardByNameInList(String name) {
        return By.xpath(String.format(".//ol//li//a[contains(text(), '%s')]", name));
    }

    //3 dots button on top bar
    public static final By THREE_DOTS_BUTTON =
            By.xpath("//div[contains(@class,'board-header u-clearfix js-board-header')]//span[contains(@data-testid, 'OverflowMenuHorizontalIcon')]");

    //3 dots menu close table
    public static final By CLOSE_TABLE_BUTTON =
            By.xpath("//div[contains(@data-testid, 'board-menu-current-panel')]//div[contains(text(), 'Zamknij tablicę')]");

    //3 dots menu close conifrm
    public static final By CONFIRM_CLOSE_TABLE_BUTTON =
            By.xpath("//button[contains(@data-testid, 'popover-close-board-confirm')]");

    public static final By PERMANENTLY_DELETE_TABLE_BUTTON =
            By.xpath("//button[contains(@data-testid, 'close-board-delete-board-button')]");

}
