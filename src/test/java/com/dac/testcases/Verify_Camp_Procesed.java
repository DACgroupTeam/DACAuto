package com.dac.testcases;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;

import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

import com.dac.main.Campaigns_DBEnglish_Page;
import com.dac.main.CreateCampaignPage;
import com.dac.main.Navigationpage;

import autoitx4java.AutoItX;
import resources.BaseTest;

public class Verify_Camp_Procesed extends BaseTest{
	
	@Test
	public void Processed_Camp_Test() throws Exception {
		String filePath="C:\\Users\\wasim\\git\\DACAuto\\logo.jpeg";
		
		Navigationpage NP=new Navigationpage(driver);
		NP.clickCampaigns();
		
		Campaigns_DBEnglish_Page CP=new Campaigns_DBEnglish_Page(driver);
		CP.click_CreateCampaignBTN();
		//Thread.sleep(5000);
	
		CreateCampaignPage newCampaign=new CreateCampaignPage(driver);
		
		newCampaign.selectCampType(2);
		
		newCampaign.selectCampLang(1);
		
		newCampaign.setCampaignName("Test English Campaign7");
		
		newCampaign.setCampaignBrandName("wasim brand");
		
		newCampaign.setCampDescr("Camp Description");
		
		newCampaign.setSenderName("wasim");
	
		
		//newCampaign.clickUploadLogo("C:\\Users\\wasim\\git\\DACAuto\\logo.jpeg");
			
		Thread.sleep(5000);
		AutoItX x=new AutoItX();
		x.winWaitActive("Open");
		x.controlFocus("Open", "", "Edit1");
		x.ControlSetText("Open", "", "Edit1", filePath);
		x.controlClick("Open", "", "Button1");
		//Runtime.getRuntime().exec("C:\\Users\\wasim\\Downloads\\AutoIt Script\\Logo upload Script.exe");
		
		Thread.sleep(5000);
		newCampaign.downloadCampEmailTemplate();

		
		
		/*newCampaign.uploadCampEmailTemplate("./EmailTemplate.xlsx");
		
		newCampaign.setCampSubject("subject campaign name");
		
		newCampaign.setCampBanner("introduction banner");
		
		newCampaign.setCampBodyCopy("Hello wasim");
		
		newCampaign.setCampSignature("thank you camp");
		
		newCampaign.setContactInfo("#8/20", "Ananthapur", "Behind Post Office", "515774", "8179615605");
		
		newCampaign.setScheduledStartDate("05", "10", "2018");
		
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_ENTER);
		
		newCampaign.setCampaignTime("02:00 AM");*/
		
		/*newCampaign.clickSaveDraft();
		
		newCampaign.clickViewAllCampaignBTN();*/
		
		
		
		
	}
}
