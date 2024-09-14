package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

// Data is valid - login success - test pass - logout
// Data is valid - login failed - test fail 

// Data is invalid - login success - test fail - logout 
// Data is invalid - login failed - test pass 
public class TC003_LoginDDT extends BaseClass{
    @Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class, groups = "Datadriven")
    public void verigy_loginDDT(String email, String pwd, String exp) {
        logger.info("-----------Starting TC003_LoginDDT -----------");
        try {
            HomePage hp = new HomePage(driver);
            hp.clickMyAccount();
            hp.clickLogin();
    
            LoginPage lp = new LoginPage(driver);
            lp.enteremail(email); // Use email directly
            lp.enterpwd(pwd); // Use pwd directly
            lp.clicklogin();
    
            MyAccountPage mcp = new MyAccountPage(driver);
            boolean targetPage = mcp.isMyAccountPageExist();
    
            if (exp.equalsIgnoreCase("Valid")) {
                if (targetPage == true) {
                    Assert.assertTrue(true);
                    mcp.clicklogout();
                } else {
                    Assert.assertTrue(false, "Login should have succeeded, but it failed.");
                }
            } else {
                if (targetPage == true) {
                    mcp.clicklogout();
                    Assert.assertTrue(false, "Login should have failed, but it succeeded.");
                } else {
                    Assert.assertTrue(true);
                }
            }
        } catch (Exception e) {
            logger.error("Test case failed due to exception: " + e.getMessage());
            Assert.fail();
        }
        logger.info("------------Finished TC003_LoginDDT--------------");
    }
    

}
