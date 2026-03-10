package pages;

import io.qameta.allure.Allure;
import locators.LoginPageLocators;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;;


public class LoginPage extends LoginPageLocators {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public LoginPage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    private void click(By locator){
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    private void type(By locator, String text){
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).sendKeys(text);
    }

    private boolean isVisible(By locator){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).isDisplayed();
    }

    public BoardsPage loginWithValidCredentials(){

        String email = System.getenv("T_EMAIL");
        String password = System.getenv("T_PASSWORD");
        Allure.step("click login button");
        click(searchLoginButton);
        Allure.step("click enter email");
        type(emailInputField, email);
        click(loginSubmitButton);
        Allure.step("enter password");
        type(passwordInputField, password);
        click(loginSubmitButton);
        Assert.assertTrue("login failed", isVisible(logo));

        return new BoardsPage(driver);
    }

    public void loginWithInvalidPassword(){

        String email = System.getenv("T_EMAIL");
        String password = "123";
        click(searchLoginButton);
        Allure.step("click enter email");
        type(emailInputField, email);
        click(loginSubmitButton);
        Allure.step("enter fake password");
        type(passwordInputField, password);
        click(loginSubmitButton);
        Assert.assertTrue("failed login message does not show", isVisible(failedLoginMessage));
    }

    public void loginWithInvalidEmail(){
        String email = "fake123213145@gmail.com";
        click(searchLoginButton);
        Allure.step("enter fake email");
        type(emailInputField, email);
        click(loginSubmitButton);
        Assert.assertTrue("failed login message does not show", isVisible(signupButton));
    }
}
