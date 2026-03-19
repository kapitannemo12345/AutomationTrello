package pages;

import base.BasePage;
import io.qameta.allure.Allure;
import locators.LoginPageLocators;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;;


public class LoginPage extends BasePage {

    private final By searchLoginButton = By.xpath("//a[contains(@href, 'id.atlassian.com/login') and contains(text(), 'Log in')]");
    private final By emailInputField = By.id("username-uid1");
    private final By loginSubmitButton = By.id("login-submit");
    private final By passwordInputField = By.id("password");
    private final By logo = By.id("header-member-menu-avatar");
    private final By failedLoginMessage = By.id("WhiteboxContainer");
    private final By signupButton = By.id("signup-submit");

    //private final WebDriver driver;
    //private final WebDriverWait wait;

    public LoginPage(WebDriver driver){
        super(driver); // calls BasePage constructor
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
