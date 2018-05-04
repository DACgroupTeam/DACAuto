package com.dac.testcases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.dac.main.Campaigns_DBEnglish_Page;
import com.dac.main.Navigationpage;

import resources.BaseTest;

public class Verify_Camp_Procesed extends BaseTest{
	
	@Test
	public void Processed_Camp_Test() {
		
		Navigationpage NP=new Navigationpage(driver);
		NP.clickReports();
		
	}
}
