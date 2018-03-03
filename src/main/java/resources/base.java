package resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class base {

	public static WebDriver driver;
	public Properties prop;

	// ***************************************************************************************
	// Base Class Usage
	public static ExtentReports report;
	public static ExtentTest logger;

	// @AfterMethod
	@AfterMethod(alwaysRun = true)
	public void TearDown_AM(ITestResult result) throws IOException {
		System.out.println("@After Method");
		try {
			if (result.getStatus() == ITestResult.FAILURE) {
				String res = captureScreenshot(driver, result.getName(), result.getName());
				String image = logger.addScreenCapture(res);
				System.out.println(image);
				String TestCaseName = this.getClass().getSimpleName()
						+ " Test Case Failure and Title/Boolean Value Failed";
				logger.log(LogStatus.FAIL, TestCaseName + logger.addScreenCapture(res));
				// logger.log(LogStatus.FAIL, image, this.getClass().getSimpleName() + " Test
				// Case Failure and Title/Boolean Value Failed");
			} else if (result.getStatus() == ITestResult.SUCCESS) {
				logger.log(LogStatus.PASS, this.getClass().getSimpleName() + " Test Case Success and Title Verified");
			} else if (result.getStatus() == ITestResult.SKIP) {
				logger.log(LogStatus.SKIP, this.getClass().getSimpleName() + " Test Case Skipped");
			}
			report.endTest(logger);
			report.flush();

		} catch (Throwable t) {
			logger.log(LogStatus.ERROR, t.fillInStackTrace());
		}

	}

	private String captureScreenshot(WebDriver driver2, String name, String result) throws IOException {
		System.out.println("reached screenshot block");
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		FileUtils.copyFile(src, new File("C://report//" + result + "screenshot.png"));
		String Imglocation = "C://report//" + result + "screenshot.png";
		// TODO Auto-generated method stub
		return Imglocation;
	}
	// ***************************************************************************************

	public WebDriver initializeDriver() throws IOException {

		prop = new Properties();
		FileInputStream fis = new FileInputStream(
				"C:\\Users\\rahuln\\DAC\\src\\main\\java\\resources\\data.properties");
		prop.load(fis);
		String browserName = prop.getProperty("browser");

		// ClassLoader classLoader = getClass().getClassLoader();
		// File file = new File(classLoader.getResource("somefile").getFile());
		// System.out.println("ClassLoader :: "+file.getAbsolutePath());
		File resourcesDirectory = new File("target/surefire-reports");
		System.out.println("File :: " + resourcesDirectory.getAbsolutePath());

		// *********************************************************************************
//		report = new ExtentReports("C:\\Users\\rahuln\\DAC\\target\\surefire-reports\\TestReportRahul.html");
		report = new ExtentReports("target/surefire-reports/TestReportRahul.html");
		report.loadConfig(new File("Extent-Config.xml"));

		logger = report.startTest(this.getClass().getSimpleName()).assignCategory("Regression Testcases");

		// Test Case Usage: Using it at Every Step in Each Test Case
		logger.log(LogStatus.INFO, "Log for Each Step in Test Case");
		// *****************************************************************************

		if (browserName.equals("chrome")) {
			// System.setProperty("webdriver.chrome.driver",
			// "C:\\drivers\\chromedriver.exe");
			driver = new ChromeDriver();
			// execute in chrome driver

		} else if (browserName.equals("firefox")) {
			driver = new FirefoxDriver();
			// firefox code
		} else if (browserName.equals("IE")) {
			// IE code
			System.setProperty("webdriver.ie.driver", "C:\\drivers\\MicrosoftWebDriver.exe");
			driver = new InternetExplorerDriver();
		}

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;

	}

	public void getScreenshot(String result) throws IOException {
		System.out.println("reached screenshot block");
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, new File("C://test//" + result + "screenshot.png"));

	}

}
