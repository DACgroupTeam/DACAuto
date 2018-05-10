package resources;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Utilities {
	
	
	public static String captureScreenshot(WebDriver driver, String resultName) throws IOException {
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, new File("target/surefire-reports/" + resultName + "screenshot.png"));
		String Imglocation = "target/surefire-reports/" + resultName + "screenshot.png";
		return Imglocation;
	}
	
	
	//For the test evidence creation
	public static void addScreenshot(WebDriver driver, String message) throws IOException {
		System.out.println("reached screenshot block");
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, new File("screenshots/"+message+".png"));

	}
	
	


}
