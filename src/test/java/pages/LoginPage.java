package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class LoginPage {

    @FindBy(xpath = "//a[contains(@href, 'id.atlassian.com/login') and contains(text(), 'Log in')]" )
    private WebElement searchLoginButton;

    @FindBy(id = "username")
    private WebElement emailInputField;

    @FindBy(id = "login-submit")
    private WebElement loginSubmitButton;

    @FindBy(id = "password")
    private WebElement passwordInputField;

    @FindBy(className = "AuI9fDVRnIPU3l")
    private WebElement logo;

    public LoginPage(WebDriver driver){
        PageFactory.initElements(driver, this);
    }

    public void loginWithValidCredentials(WebDriver driver){
        String email = System.getenv("T_EMAIL");
        String password = System.getenv("T_PASSWORD");
        searchLoginButton.click();
        emailInputField.sendKeys(email);
        loginSubmitButton.click();
        passwordInputField.sendKeys(password);
        loginSubmitButton.click();
        Assert.assertTrue( "login failed", logo.isDisplayed());
    }

}
