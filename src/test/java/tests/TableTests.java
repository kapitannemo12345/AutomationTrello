package tests;

import base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.BoardsPage;

public class TableTests extends BaseTest {

    @Test
    @Description("Create table")
    @Severity(SeverityLevel.CRITICAL)
    public void createTable(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginWithValidCredentials();
        BoardsPage boardsPage = new BoardsPage(driver);
        boardsPage.createBoard();
    }





}
