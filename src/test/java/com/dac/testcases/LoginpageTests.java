package com.dac.testcases;

import java.io.IOException;

import org.testng.Assert;
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
		lp = new LoginPage(driver);
	}

	@Test
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



	@AfterTest
	public void tearDownMethod() throws Exception {
		driver.close();
		driver = null;

	}
}
