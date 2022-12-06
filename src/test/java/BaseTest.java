package test.java;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.configuration.Theme;
import io.github.bonigarcia.wdm.WebDriverManager;
import main.java.utils.Constants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.File;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    public static WebDriver driver;
    public static ExtentReports extent;
    public static ExtentTest logger;
    @BeforeTest
    public void beforeTestMethod() {
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(System.getProperty(Constants.reportsFilePath)+File.separator+"reports"+File.separator+"AutomationReport.html");
        htmlReporter.config().setTestViewChartLocation(ChartLocation.BOTTOM);
        htmlReporter.config().setChartVisibilityOnOpen(true);
        htmlReporter.config().setTheme(Theme.STANDARD);
        htmlReporter.config().setDocumentTitle("Automation Report");
        htmlReporter.config().setEncoding("utf-8");
        htmlReporter.config().setReportName("Automation Test Results");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        extent.setSystemInfo("Automation Tester", "Dhineswar A T");
        extent.setSystemInfo("Organization", "VeralevelOrg");
        extent.setSystemInfo("Build no", "CodeAssessment-TestVag 1.0");
    }

    @BeforeMethod
    @Parameters(value ={"browserName"})
    public void beforeMethodMethod(String browserName, Method testMethod) {
        logger=extent.createTest(testMethod.getName());
        setupDriver(browserName);
        driver.manage().window().maximize();
        driver.get(Constants.url);
    }

    @AfterMethod
    public void afterMethodMethod(ITestResult result) {
        if(result.getStatus()== ITestResult.SUCCESS){
            String methodName = result.getMethod().getMethodName();
            String logText = "Test  Case: "+ methodName + " Passed";
            Markup m = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
            logger.log(Status.PASS,m);
        } else if (result.getStatus()==ITestResult.FAILURE){
            String methodName = result.getMethod().getMethodName();
            String logText = "Test case: "+methodName+" Failed";
            Markup m = MarkupHelper.createLabel(logText,ExtentColor.RED);
            logger.log(Status.FAIL,m);
        }
        else if (result.getStatus()==ITestResult.SKIP){
            String methodName = result.getMethod().getMethodName();
            String logText = "Test case: "+methodName+" SKIPPED";
            Markup m = MarkupHelper.createLabel(logText,ExtentColor.BLUE);
            logger.log(Status.SKIP,m);
            }
        driver.quit();
    }

    @AfterTest
    public void afterTestMethod() {
        extent.flush();
    }

    public void setupDriver(String browserName){
        switch (browserName){
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver=new ChromeDriver();
            case"firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
            case "edge":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + browserName);
        }
    }
}
