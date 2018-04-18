package resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.aventstack.extentreports.reporter.*;

public abstract class base implements IAutoconst {

	static {
		System.setProperty(IE_KEY, IE_VALUE);
		System.setProperty(GECKO_KEY, GECKO_VALUE);
		System.setProperty(CHROME_KEY, CHROME_VALUE);
	}

	public static WebDriver driver;
	public Properties prop;
	public static ExtentReports report;
	public static ExtentTest logger;

	//****************************Extent report
	
	@BeforeTest(alwaysRun = true)
	public void reportSetup() throws IOException {

		report = new ExtentReports("target/surefire-reports/TestReport.html");
		report.loadConfig(new File("Extent-Config.xml"));
	}

	@AfterMethod(alwaysRun = true)
	public void generateReport(ITestResult result) throws IOException {
		System.out.println("@After Method");
		try {
			if (result.getStatus() == ITestResult.FAILURE) {
				String res = Utilities.captureScreenshot(driver, result.getName());
				String TestCaseName = this.getClass().getSimpleName() + " Failed";
				String methodname = result.getMethod().getMethodName();
				logger.log(LogStatus.FAIL, methodname);
				logger.log(LogStatus.FAIL, TestCaseName + logger.addScreenCapture(result.getName() + "screenshot.png"));
				logger.log(LogStatus.FAIL, result.getThrowable());
			} else if (result.getStatus() == ITestResult.SUCCESS) {
				logger.log(LogStatus.PASS, this.getClass().getSimpleName() + " Test Case Success and Verified");
			} else if (result.getStatus() == ITestResult.SKIP) {
				logger.log(LogStatus.SKIP, this.getClass().getSimpleName() + " Test Case Skipped");
			}
			report.endTest(logger);
			report.flush();
		} catch (Throwable t) {
			logger.log(LogStatus.ERROR, t.fillInStackTrace());
		}

	}

	
	//***************** intialising  browser
	
	public WebDriver initializeDriver() throws IOException {
		
		prop = new Properties();
		FileInputStream fis = new FileInputStream(CONFIG_PATH);
		prop.load(fis);
		logger = report.startTest(this.getClass().getSimpleName()).assignCategory("Regression Testcases");
		logger.log(LogStatus.INFO, "Log for Each Step in Test Case");
		String browserName = prop.getProperty("browser");

		if (browserName.equals("chrome")) {
			driver = new ChromeDriver();

		} else if (browserName.equals("firefox")) {
			driver = new FirefoxDriver();
			// firefox code
		} else if (browserName.equals("IE")) {
			// IE code
			driver = new InternetExplorerDriver();
		}

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;

	}
	
	
	
	//**********************for login to auth and then Dashboard

	public void loginAuth(WebDriver driver, Properties prop) {
		// login to auth centre
		driver.get(prop.getProperty("AuthCenterURL"));
		WebElement Login = driver.findElement(By.xpath(prop.getProperty("Authlogin")));

		Login.sendKeys(prop.getProperty("email"));
		WebElement Password = driver.findElement(By.xpath(prop.getProperty("Authpassword")));
		Password.sendKeys(prop.getProperty("password"));
		WebElement Signin = driver.findElement(By.xpath(prop.getProperty("signin")));
		Signin.submit();

	}

	public void navigateToDashboard(WebDriver driver, Properties prop) {
		String oldTab = driver.getWindowHandle();
		WebElement email = driver.findElement(By.id(prop.getProperty("emailsearch")));
		email.clear();
		email.sendKeys(prop.getProperty("emailID"));
		WebElement linkToDashboard = driver.findElement(By.linkText(prop.getProperty("dashboardLink")));
		linkToDashboard.click();
		ArrayList<String> newTab = new ArrayList<String>(driver.getWindowHandles());
		newTab.remove(oldTab);
		// change focus to new tab
		driver.switchTo().window(newTab.get(0));

	}

}
