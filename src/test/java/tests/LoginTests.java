package tests;

import base.BaseTest;
import org.testng.annotations.Test;
import pages.LoginPage;


public class LoginTests extends BaseTest {
    
    @Test
    public void login() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginWithValidCredentials(driver);
    }
}
