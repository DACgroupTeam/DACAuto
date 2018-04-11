package com.dac.testcases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.dac.main.LoginPage;
import com.relevantcodes.extentreports.LogStatus;

import resources.base;

public class LoginpageValidLogin extends base {

	LoginPage lp;

	@BeforeClass
	public void setup() throws IOException {
		driver = initializeDriver();
		lp = new LoginPage(driver);
	}



	@Test
	public void valid_login() throws InterruptedException {
		driver.get(prop.getProperty("url"));
		lp.login().clear();
		lp.login().sendKeys("dac_ra_beta1@dacgroup.com");
		lp.password().clear();
		lp.password().sendKeys("111111");
		lp.submit().click();
		Thread.sleep(6000);
		boolean test = lp.logo().isDisplayed();
		logger.log(LogStatus.INFO, "asserting logo displayed");
		Assert.assertTrue(test);

	}

	@AfterClass
	public void tearDownMethod() throws Exception {
		driver.close();
		driver = null;

	}
}
