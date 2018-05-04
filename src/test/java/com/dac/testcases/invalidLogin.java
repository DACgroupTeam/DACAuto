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

public class invalidLogin extends BaseTest {

	LoginPage lp;

	@Test
	public void testInvalidLogin() {
		try {
			driver.get(prop.getProperty("url"));
			
			lp.clearUserName();
			lp.setUserName("dac_ra_beta1@dacgroup.com");
			
			lp.clearPassword();
			lp.setPassword("1111");
			
			lp.clickLogin();
			Thread.sleep(6000);
			logger.log(LogStatus.INFO, "asserting validation message");
			Assert.assertEquals("Invalid Password", lp.warning().getAttribute("innerText"));

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
