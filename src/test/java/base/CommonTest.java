package base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.Duration;

public class CommonTest {

    //force waits if its needed
    public static void Wait(int milliseconds ) {
        try {
            Thread.sleep(milliseconds); // 1000 milliseconds = 1 second
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void findElement(WebDriver driver, String xpath) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        By searchFieldLocator = By.xpath(xpath);

        try {
            WebElement searchInput = wait.until(ExpectedConditions.visibilityOfElementLocated(searchFieldLocator));
            System.out.println("Element found: " + searchInput);
        } catch (Exception e) {
            System.err.println("Could not locate the element for XPath: " + xpath);
            dumpPageSource(driver, "search_input_not_found.html");
            throw e;
        }
    }

    // Save current DOM to a file for debugging
    private static void dumpPageSource(WebDriver driver, String fileName) {
        try (PrintWriter out = new PrintWriter(fileName)) {
            out.println(driver.getPageSource());
            System.out.println("Page source dumped to: " + fileName);
        } catch (FileNotFoundException ex) {
            System.err.println("Failed to dump page source: " + ex.getMessage());
        }
    }


}
