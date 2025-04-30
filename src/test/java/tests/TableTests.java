package tests;

import base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.BoardsPage;
import pages.SingleBoardPage;

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

    @Test
    @Description("Create table")
    @Severity(SeverityLevel.CRITICAL)
    public void modifyTable(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginWithValidCredentials();
        BoardsPage boardsPage = new BoardsPage(driver);
        boardsPage.createBoard();
        SingleBoardPage singleBoardPage = new SingleBoardPage(driver);
        singleBoardPage.addList(false, "to do");
        singleBoardPage.addList(false, "in progress");
        singleBoardPage.addList(false, "done");
    }



    //delete table
    //edit table
}
