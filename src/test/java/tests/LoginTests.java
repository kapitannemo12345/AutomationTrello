package tests;

import base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.Test;
import pages.LoginPage;


public class LoginTests extends BaseTest {
    
    @Test
    @Description("Login with valid credentials")
    @Severity(SeverityLevel.CRITICAL)
    public void validLogin(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginWithValidCredentials();
    }

    @Test
    @Description("Login with invalid credentials")
    @Severity(SeverityLevel.CRITICAL)
    public void invalidPasswordLogin(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginWithInvalidPassword();
    }

    @Test
    @Description("Login with invalid 2 credentials")
    @Severity(SeverityLevel.CRITICAL)
    public void invalidEmailLogin(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginWithInvalidEmail();
    }
}
