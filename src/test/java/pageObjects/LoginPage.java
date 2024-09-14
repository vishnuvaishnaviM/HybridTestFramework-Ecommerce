package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{

    WebDriver driver;

    public LoginPage(WebDriver driver){
        super(driver);
    }

    @FindBy(xpath = "//input[@id='input-email']") 
    WebElement email;
    @FindBy(xpath = "//input[@id='input-password']") 
    WebElement pwd;
    @FindBy(xpath = "//input[@value='Login']") 
    WebElement login;

    public void enteremail(String Email){
        email.sendKeys(Email);
    }
    public void enterpwd(String passwrd){
        pwd.sendKeys(passwrd);
    }
    public void clicklogin(){
        login.click();
    }
}
