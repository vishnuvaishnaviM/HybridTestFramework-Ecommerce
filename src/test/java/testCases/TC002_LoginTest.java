package testCases;

import org.testng.Assert;
import org.testng.annotations.*;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass{
    @Test(groups = {"Sanity", "Master"})
    public void verigy_login(){
        logger.info("--------starting TC002_LoginTest-----------");

        try{
            HomePage hp = new HomePage(driver);
            hp.clickMyAccount();
            hp.clickLogin();
    
            LoginPage lp = new LoginPage(driver);
            lp.enteremail(p.getProperty("email"));
            lp.enterpwd(p.getProperty("password"));
            lp.clicklogin();
    
            MyAccountPage mcp = new MyAccountPage(driver);
            boolean targetPage = mcp.isMyAccountPageExist();
            Assert.assertEquals(targetPage, true, "Login failed");
        }catch(Exception e){
            Assert.fail();
        }
        logger.info("--------finished TC002_LoginTest-----------");

    }

}
