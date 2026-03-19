package locators;

import org.openqa.selenium.By;

public class LoginPageLocators {

    private final By searchLoginButton = By.xpath("//a[contains(@href, 'id.atlassian.com/login') and contains(text(), 'Log in')]");
    private final By emailInputField = By.id("username-uid1");
    private final By loginSubmitButton = By.id("login-submit");
    private final By passwordInputField = By.id("password");
    private final By logo = By.id("header-member-menu-avatar");
    private final By failedLoginMessage = By.id("WhiteboxContainer");
    private final By signupButton = By.id("signup-submit");

}
