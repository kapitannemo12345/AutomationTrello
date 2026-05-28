package pages;

import base.BasePage;
import io.qameta.allure.Allure;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class TopBarPage extends BasePage {

    private final By allInputs = By.tagName("input");

    public static By searchFieldItem(String itemName){
        return By.xpath(String.format(
                "//a[contains(@href,'/b/') and contains(.,'%s')]",
                itemName
        ));
    }

    public TopBarPage(WebDriver driver){
        super(driver);
    }

    public void search(String text) {

        Allure.step("Find visible input safely (handle React re-render)");

        WebElement input = wait.until(driver -> {
            List<WebElement> inputs = driver.findElements(allInputs);

            for (WebElement el : inputs) {
                try {
                    if (el.isDisplayed()) {
                        return el; // return fresh element
                    }
                } catch (StaleElementReferenceException ignored) {
                }
            }
            return null;
        });

        Allure.step("Click + type with retry (anti-stale)");

        // 🔁 retry mechanism (VERY important)
        int attempts = 0;
        while (attempts < 3) {
            try {
                input = driver.findElements(allInputs)
                        .stream()
                        .filter(WebElement::isDisplayed)
                        .findFirst()
                        .orElseThrow();

                input.click();
                input.clear();
                input.sendKeys(text);
                break;

            } catch (StaleElementReferenceException e) {
                attempts++;
            }
        }

        Allure.step("Click search result");

        WebElement resultItem = wait.until(
                ExpectedConditions.visibilityOfElementLocated(searchFieldItem(text))
        );

        resultItem.click();
    }
}
