package utilities;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;
//listener utility files
public class ExtenetReportManager implements ITestListener{
    public ExtentSparkReporter sparkReporter; //UI of the report
    public ExtentReports extent; //populate common info on the report
    public ExtentTest test; //creating test case entries in the report and update status of the test methods
    String repName;
    public void onStart(ITestContext testContext) { //will run only once before starting test cases execution
        SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
        Date dt = new Date();
        String timeStamp = df.format(dt);
        
        repName = "Test-Report-" + timeStamp + ".html";
        
        sparkReporter = new ExtentSparkReporter(".\\reports\\" + repName);
        sparkReporter.config().setDocumentTitle("Automation Report - Opencart");
        sparkReporter.config().setReportName("Functional testing - Opencart");
        sparkReporter.config().setTheme(Theme.STANDARD);
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        extent.setSystemInfo("Computer Name", "localhost");
        extent.setSystemInfo("Application", "Opencart");
        extent.setSystemInfo("Tester Name", System.getProperty("user.name"));
        //to get the OS and windows parameters details from the current xml that is running the tests
        String os = testContext.getCurrentXmlTest().getParameter("os");
        extent.setSystemInfo("Operating System", os);

        String browser = testContext.getCurrentXmlTest().getParameter("browser");
        extent.setSystemInfo("Browser", browser);

        List<String> includedGroups = testContext.getCurrentXmlTest().getIncludedGroups();
        if(!includedGroups.isEmpty()){
            extent.setSystemInfo("Groups", includedGroups.toString());
        }


        }


   public void onTestSuccess(ITestResult result) { //will run on each testcase success
    test = extent.createTest(result.getTestClass().getName()); //create a new entry in the report
    //to display groups in the report
    test.assignCategory(result.getMethod().getGroups());
    test.log(Status.PASS, "Test case PASSED is: " + result.getName());//update status p/f/s
   }


   public void onTestFailure(ITestResult result) { // will run on each test case failure
    test = extent.createTest(result.getTestClass().getName());
    test.assignCategory(result.getMethod().getGroups());
    test.log(Status.FAIL, "Test case FAILED is: " + result.getName());
    test.log(Status.FAIL, "Test Case FAILED cause is: " + result.getThrowable());
    try{
        String imgPath = new BaseClass().captureScreen(result.getName());
        test.addScreenCaptureFromPath(imgPath);
    }catch(Exception e){
        e.printStackTrace();
    }
}


   public void onTestSkipped(ITestResult result) { //will run when a test case is skipped
    test = extent.createTest(result.getTestClass().getName());
    test.assignCategory(result.getMethod().getGroups());
    test.log(Status.SKIP, "Test case SKIPPED is: " + result.getName());
    test.log(Status.INFO, result.getThrowable().getMessage());
   }


   public void onFinish(ITestContext context) {
    extent.flush();
    //to automatically open the report after the tests execution
   

    // String pathOfExtentReport = System.getProperty("user.dir")+"\\reports\\"+repName;
    // File extentreport = new File(pathOfExtentReport);
    // try{
    //     Desktop.getDesktop().browse(extentreport.toURI());
    // }catch(IOException e){
    //     e.printStackTrace();
    // }
   }
}
