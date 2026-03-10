package locators;

import org.openqa.selenium.By;

public class LoginPageLocators {

    public static final By searchLoginButton = By.xpath("//a[contains(@href, 'id.atlassian.com/login') and contains(text(), 'Log in')]");
    public static final By emailInputField = By.id("username-uid1");
    public static final By loginSubmitButton = By.id("login-submit");
    public static final By passwordInputField = By.id("password");
    public static final By logo = By.id("header-member-menu-avatar");
    public static final By failedLoginMessage = By.id("WhiteboxContainer");
    public static final By signupButton = By.id("signup-submit");

}
