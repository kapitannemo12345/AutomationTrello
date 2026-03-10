package tests;

import base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.BoardsPage;
import org.testng.Assert;


public class LoginTests extends BaseTest {
    
    @Test
    @Description("Login with valid credentials")
    @Severity(SeverityLevel.CRITICAL)
    public void validLogin(){
        LoginPage loginPage = new LoginPage(driver);
        BoardsPage boardsPage = loginPage.loginWithValidCredentials(); // transition from login page to boards page
        Assert.assertTrue(boardsPage.isUserLoggedIn(), "Login failed");
        //implement validotion of logo on

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
