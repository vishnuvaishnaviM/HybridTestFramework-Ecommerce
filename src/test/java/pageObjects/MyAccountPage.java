package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage{
    WebDriver driver;
    public MyAccountPage(WebDriver driver){
        super(driver);
    }

    @FindBy(xpath = "//h2[text()='My Account']") // My account page heding
    WebElement msgHeading;

    @FindBy(xpath = "//div[@class='list-group']//a[text()='Logout']")
    WebElement logout;

    public boolean isMyAccountPageExist(){
        try{
            return (msgHeading.isDisplayed());
        }catch(Exception e){
            return false;
        }
    }

    public void clicklogout(){
        logout.click();
    }
}
