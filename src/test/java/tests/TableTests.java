package tests;

import base.BaseTest;
import base.CommonTest;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.BoardsPage;
import pages.SingleBoardPage;

import java.util.List;

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
        singleBoardPage.addList( "to do");
        CommonTest.Wait(500);
        singleBoardPage.addListItem("to do","item1");
        CommonTest.Wait(500);
        singleBoardPage.addList( "in progress");
        CommonTest.Wait(500);
        singleBoardPage.addListItem("in progress", "item2");
        CommonTest.Wait(500);
        singleBoardPage.addList( "done");
        CommonTest.Wait(500);
        singleBoardPage.addListItem("done", "item3");
        //singleBoardPage.addListItem("done");
    }



    //delete table
    //edit table
}
