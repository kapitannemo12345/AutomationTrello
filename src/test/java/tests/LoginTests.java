package tests;

import base.BaseTest;
import org.testng.annotations.Test;
import pages.LoginPage;


public class LoginTests extends BaseTest {
    
    @Test
    public void validLogin(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginWithValidCredentials();
    }

    @Test
    public void invalidPasswordLogin(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginWithInvalidPassword();
    }

}
