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

    @FindBy(id = "WhiteboxContainer")
    private WebElement failedLoginMessage;

    @FindBy(id = "signup-submit")
    private WebElement signupButton;

    public LoginPage(WebDriver driver){
        PageFactory.initElements(driver, this);
    }

    public void loginWithValidCredentials(){
        String email = System.getenv("T_EMAIL");
        String password = System.getenv("T_PASSWORD");
        searchLoginButton.click();
        emailInputField.sendKeys(email);
        loginSubmitButton.click();
        passwordInputField.sendKeys(password);
        loginSubmitButton.click();
        Assert.assertTrue( "login failed", logo.isDisplayed());
    }

    public void loginWithInvalidPassword(){
        String email = System.getenv("T_EMAIL");
        String password = "123";
        searchLoginButton.click();
        emailInputField.sendKeys(email);
        loginSubmitButton.click();
        passwordInputField.sendKeys(password);
        loginSubmitButton.click();
        Assert.assertTrue( "failed login message does not show", failedLoginMessage.isDisplayed());
    }

    public void loginWithInvalidEmail(){
        String email = "fake123213145@gmail.com";
        searchLoginButton.click();
        emailInputField.sendKeys(email);
        loginSubmitButton.click();
        Assert.assertTrue( "failed login message does not show", signupButton.isDisplayed());
    }
}
