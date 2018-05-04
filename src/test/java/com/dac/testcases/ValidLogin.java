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

import resources.BaseTest;

public class ValidLogin extends BaseTest {

	LoginPage lp;

	@Test
	public void testValidLogin() throws InterruptedException {
		
		driver.get(prop.getProperty("url"));
		
		lp.clearUserName();
		lp.setUserName("dac_ra_beta1@dacgroup.com");
		
		lp.clearPassword();
		lp.setPassword("111111");
		
		lp.clickLogin();
		Thread.sleep(6000);
		boolean test = lp.logo().isDisplayed();
		logger.log(LogStatus.INFO, "asserting logo displayed");
		Assert.assertTrue(test);

	}

}
