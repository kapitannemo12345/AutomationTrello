package pages;

import base.CommonTest;
import io.qameta.allure.Allure;
import locators.TopBarLocators;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;


public class TopBarPage extends TopBarLocators {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public TopBarPage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void search(String text) {
        CommonTest.Wait(5000);// 3 seconds
        Allure.step("Input text into search field");
        WebElement searchBox = driver.findElement(TopBarLocators.SEARCH_FIELD);
        searchBox.sendKeys("test");
        CommonTest.Wait(5000);
        WebElement resultItem = wait.until(ExpectedConditions.visibilityOfElementLocated(TopBarLocators.searchFieldItem(text)));
        resultItem.click();
        CommonTest.Wait(5000);
    }

}

