package locators;

import org.openqa.selenium.By;

public class TopBarLocators {

    // search field
    public static final By SEARCH_FIELD =
            By.xpath("//input[@data-testid='cross-product-search-input-skeleton']");


    // item in the search field
    public static By searchFieldItem(String itemName){
        return By.xpath(String.format("//section//a[contains(@href, '/b/')]/div//span[contains(text(), '%s')]",itemName));
    }

//    // Dynamic: find a card inside a list by its name
//    public static By cardByNameInList(String name) {
//        return By.xpath(String.format(".//ol//li//a[contains(text(), '%s')]", name));
//    }

}
