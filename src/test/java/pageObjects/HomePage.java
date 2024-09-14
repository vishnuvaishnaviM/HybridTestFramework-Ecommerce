package pageObjects;
//all page object classes will have - constructor, locators, actions

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{

    WebDriver driver;

    public HomePage(WebDriver driver){
        super(driver);
    }

    @FindBy(xpath = "//span[normalize-space()='My Account']") 
    WebElement my_account;

    @FindBy(xpath = "//a[normalize-space()='Register']")
    WebElement register;

    @FindBy(xpath = "//a[normalize-space()='Login']")
    WebElement login;


    public void clickMyAccount(){
        my_account.click();
    }
    public void clickRegister(){
        register.click();
    }
    public void clickLogin(){
        login.click();
    }
}
