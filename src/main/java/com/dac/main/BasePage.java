package com.dac.main;

import java.io.File;

import org.apache.commons.io.FilenameUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

public class BasePage {
	
	
		
	/**
	public void verifyPageIsDisplayed(WebDriver driver,String eResult) {
		String sETO=AutoUtil.getProperty(IAutoConst.CONFIG_PATH, "ETO");
		long ETO=Long.parseLong(sETO);
		WebDriverWait wait=new WebDriverWait(driver,ETO);
		try {
			Reporter.log(eResult,true);
			wait.until(ExpectedConditions.titleIs(eResult));
			Reporter.log("PASS: Expected Page is Displayed",true);
		}
		catch(Exception e) {
			Reporter.log("FAIL: Expected Page is NOT Displayed",true);
			Assert.fail();
		}
	}
	
	*/
	
	public void verifyText(WebElement e,String eText) {
		String aText=e.getText();
		System.out.println(aText);
		Assert.assertEquals(aText, eText);
	}
	
	public void scrollByElement(WebElement element,WebDriver driver) {
		
		JavascriptExecutor js=(JavascriptExecutor)driver;
		int yLoc = element.getLocation().getY();
		int xLoc = element.getLocation().getX();
		js=(JavascriptExecutor)driver;
		js.executeScript("window.scrollTo("+xLoc+", "+yLoc+")");
	}
	
	public static File waitForDownloadToComplete(File downloadPath, String fileName) throws Exception {
        boolean isFileFound = false;
        int waitCounter = 0;
        while (!isFileFound) {
            //logger.info("Waiting For Download To Complete....");
        	System.out.println("Waiting For Download To Complete....");
            for (File tempFile : downloadPath.listFiles()) {
                if (tempFile.getName().contains(fileName)) {
                    String tempEx = FilenameUtils.getExtension(tempFile.getName());
                    // crdownload - For Chrome, part - For Firefox
                    if (tempEx.equalsIgnoreCase("crdownload") || tempEx.equalsIgnoreCase("part")) {
                        Thread.sleep(1000);
                    } else {
                        isFileFound = true;
                        //logger.info("Download To Completed....");
                        return tempFile;
                    }
                }
            }
            Thread.sleep(1000);
            waitCounter++;
            if (waitCounter > 25) {
                isFileFound = true;
            }
        }
        throw new Exception("File Not Downloaded");
    }
}
