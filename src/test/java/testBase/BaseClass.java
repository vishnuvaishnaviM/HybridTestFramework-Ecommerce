package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;
import org.apache.logging.log4j.LogManager;//log4j2
import org.apache.logging.log4j.Logger;//log4j2


public class BaseClass {
    public static WebDriver driver;
    public Logger logger; //log4j2
    public Properties p; //properties file
    
    @BeforeClass(groups = {"Sanity","Regression","Master","Datadriven"})
    @Parameters({"os","browser"})
    public void setup(String os,String br) throws IOException{
        
        //loading properties from config.properties file
        FileReader file = new FileReader("./src//test//resources//config.properties");
        p=new Properties();
        p.load(file);

        if(p.getProperty("execution_env").equalsIgnoreCase("remote")){
            //grid setup
            DesiredCapabilities capabilities = new DesiredCapabilities();
            //os
            if(os.equalsIgnoreCase("windows")){
                capabilities.setPlatform(Platform.WIN11);
            }else if(os.equalsIgnoreCase("mac")){
                capabilities.setPlatform(Platform.MAC);

            }else{
                System.out.println("No matching OS");
                return;
            }
            //browser
            switch (br.toLowerCase()) {
                case "chrome": capabilities.setBrowserName("chrome");break;
                case "edge": capabilities.setBrowserName("MicrosoftEdge");break;
                
                default:
                    System.out.println("Invalid browser");return;
            }
            driver=new RemoteWebDriver(new URL("http://192.168.29.120:4444/wd/hub"),capabilities);
        }
        if(p.getProperty("execution_env").equalsIgnoreCase("local")){
        switch (br.toLowerCase()) {
            case "chrome": driver = new ChromeDriver();break;
            case "edge": driver = new EdgeDriver();break;
            case "firefox": driver = new FirefoxDriver();break;
            default:
                System.out.println("Invalid browser");return;
        }
    }

        logger = LogManager.getLogger(this.getClass()); //this.getclass is used to get the logs of particular test case for this test is running

        driver.manage().deleteAllCookies();
        driver.get(p.getProperty("appURL"));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

    }
    @AfterClass(groups = {"Sanity","Regression","Master","Datadriven"})
    public void tearDown(){
        driver.quit();
    }
    
    public String randomString(){
        return RandomStringUtils.randomAlphanumeric(5);
    }
    public String randomNumber(){
        return RandomStringUtils.randomNumeric(10);
    }
    public String randomAlphaNumeric(){
        return RandomStringUtils.randomAlphanumeric(5)+RandomStringUtils.randomNumeric(3);
    }

    public String captureScreen(String tname) throws IOException{
        String timeStamp = new SimpleDateFormat("yyyMMddhhmmss").format(new Date());

        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

        String targetFilePath = System.getProperty("user.dir")+ "\\screenshots\\"+tname+"_"+timeStamp+ ".png";
        File targetFile = new File(targetFilePath);
        sourceFile.renameTo(targetFile);
        return targetFilePath;
    }
}
