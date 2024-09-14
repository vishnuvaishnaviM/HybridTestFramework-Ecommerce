package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.v125.v125CdpInfo;
import org.openqa.selenium.support.FindBy;

public class AccountRegisterPage extends BasePage{
    WebDriver driver;

    public AccountRegisterPage(WebDriver driver){
        super(driver);
    }

    @FindBy(xpath = "//input[@id='input-firstname']")
    WebElement firstName;

    @FindBy(xpath = "//input[@id='input-lastname']")
    WebElement lastName;

    @FindBy(xpath = "//input[@id='input-email']")
    WebElement email;

    @FindBy(xpath = "//input[@id='input-telephone']")
    WebElement telephone;

    @FindBy(xpath = "//input[@id='input-password']")
    WebElement password;

    @FindBy(xpath = "//input[@id='input-confirm']")
    WebElement confirmPassword;

    @FindBy(xpath = "//input[@name='agree']")
    WebElement policy;

    @FindBy(xpath = "//input[@value='Continue']")
    WebElement continueBtn;

    @FindBy(xpath = "//h1[normalize-space()='Your Account Has Been Created!']")
    WebElement positiveFeedBackConfirm;

    public void fname(String first){
        firstName.sendKeys(first);
    }
    public void lname(String last){
        lastName.sendKeys(last);
    }
    public void email(String mail){
        email.sendKeys(mail);
    }
    public void tel(String phone){
        telephone.sendKeys(phone);
    }
    public void pwd(String passwrd){
        password.sendKeys(passwrd);
    }
    public void confirmpwd(String cpwd){
        confirmPassword.sendKeys(cpwd);
    }
    public void clickpolicy(){
        policy.click();
    }
    public void clickContinue(){
        continueBtn.click();
    }
    public String msgConfirm(){
        try{
            return (positiveFeedBackConfirm.getText());
        }
        catch(Exception e){
            return (e.getMessage());
        }
    }
}
