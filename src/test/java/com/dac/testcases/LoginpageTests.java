package com.dac.testcases;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.dac.main.LoginPage;
import com.relevantcodes.extentreports.LogStatus;

import resources.base;




public class LoginpageTests extends base {

	
	LoginPage lp;
	
	@BeforeTest
	public void setup() throws IOException {
		driver = initializeDriver();
	    lp= new LoginPage(driver);
	}
	
	@Test(priority=1)
	public void invalid_login() {
		try {
		driver.get(prop.getProperty("url"));
		lp.login().clear();
		lp.login().sendKeys("dac_ra_beta1@dacgroup.com");
		lp.password().clear();
		lp.password().sendKeys("1111");
		lp.submit().click();
		Thread.sleep(6000);
		logger.log(LogStatus.INFO, "asserting validation message");
		Assert.assertEquals("Invalid Password2", lp.warning().getAttribute("innerText"));
		
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
	@Test(priority=2)
	public void valid_login() throws InterruptedException {
		
		
		lp.login().clear();
		lp.login().sendKeys("dac_ra_beta1@dacgroup.com");
		lp.password().clear();
		lp.password().sendKeys("111111");
		lp.submit().click();
		Thread.sleep(6000);
	    boolean test= lp.logo().isDisplayed();
	    logger.log(LogStatus.INFO, "asserting logo displayed");
		Assert.assertFalse(test);
		
		}
	
		@AfterTest
		public void tearDownMethod() throws Exception {
			driver.close();
			driver=null;
			
		}
}
