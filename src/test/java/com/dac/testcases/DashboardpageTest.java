package com.dac.testcases;

import java.io.IOException;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.dac.main.LoginPage;
import com.dac.main.Navpage;
import com.relevantcodes.extentreports.LogStatus;

import resources.base;

public class DashboardpageTest extends base{
	
	Navpage nav ;

	@BeforeTest
	public void setup() throws IOException {
		driver = initializeDriver();
		driver.manage().window().maximize();
		nav = new Navpage(driver);
		loginAuth(driver, prop); //logins to DAC
		navigateToDashboard(driver, prop); //navigate to dashboard
	}

	@Test
	public void invalid_login() {
		try {
			
			Thread.sleep(10000);
			nav.getAnalysis().click();
			Thread.sleep(10000);
			((JavascriptExecutor)driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
			Actions action= new Actions(driver);
			Thread.sleep(20000);
			action.moveToElement(driver.findElement(By.xpath("//*[contains(text(), '75')][contains(@style,'color:#606060')]")))
			.build().perform();
		
			String aggregate= driver.findElement(By.xpath("//*[@class=\"highcharts-tooltip\"]//child::*[contains(text(), 'Score Value')]")).getText();
			System.out.println(aggregate);
			String Dbaggregate= driver.findElement(By.xpath("(//*[@id='ace-settings-box']//span[@class='infobox-data-number'])[2]")).getText();
			Assert.assertEquals(aggregate, Dbaggregate);
		
			logger.log(LogStatus.INFO, "asserting validation message");
			
//			
//			driver.findElement(By.linkText("Content Analysis")).click();
//		    driver.findElement(By.id("completeness_table")).click();
//		    driver.findElement(By.cssSelector("g.highcharts-markers.highcharts-series-0.highcharts-tracker > path")).click();
			

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
