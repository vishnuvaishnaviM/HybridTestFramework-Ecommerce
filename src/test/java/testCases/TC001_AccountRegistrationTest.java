package testCases;
import org.testng.Assert;
import org.testng.annotations.*;



import pageObjects.AccountRegisterPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass{
    @Test(groups = {"Regression","Master"})
    public void verifyAccountCreation(){

        logger.info("--------starting TC001_AccountRegistrationTest ");


        try{
            HomePage hp = new HomePage(driver);
        hp.clickMyAccount();
        logger.info("--------clicked on my account link");
        hp.clickRegister();
        logger.info("---------clicked on register link");


        AccountRegisterPage arp = new AccountRegisterPage(driver);
        logger.info("---------Providing customer details");
        arp.fname(randomString().toUpperCase());
        arp.lname(randomString().toUpperCase());
        arp.email(randomString()+"@gmail.com");
        arp.tel(randomNumber());
        String pwd = randomAlphaNumeric();
        arp.pwd(pwd);
        arp.confirmpwd(pwd);
        arp.clickpolicy();
        arp.clickContinue();
        logger.info("---------validating expected message");
        String conMsg = arp.msgConfirm();
        if(conMsg.equals("Your Account Has Been Created!")){
            Assert.assertTrue(true);
        }else{
            logger.error("---------Test failed");
            logger.debug("---------Debug logs");
            Assert.assertTrue(false);
        }
        }
        catch(Exception e){
            Assert.fail();
        }

        logger.info("-----------Finished TC001_AccountRegistrationTest ");
        
    }
}
