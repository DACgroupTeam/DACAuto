package com.dac.testcases;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.testng.annotations.Test;

import com.dac.main.Campaigns_DBEnglish_Page;
import com.dac.main.CreateCampaignPage;
import com.dac.main.Navigationpage;

import resources.BaseTest;

public class Verify_Camp_Procesed extends BaseTest{
	
	@Test
	public void Processed_Camp_Test() throws Exception {
		
		Navigationpage NP=new Navigationpage(driver);
		NP.clickCampaigns();
		Thread.sleep(5000);

		Campaigns_DBEnglish_Page CP=new Campaigns_DBEnglish_Page(driver);
		CP.click_CreateCampaignBTN();
	
		CreateCampaignPage newCampaign=new CreateCampaignPage(driver);
		Thread.sleep(5000);
		newCampaign.selectCampType(2);
		
		newCampaign.selectCampLang(1);
		
		newCampaign.setCampaignName("Test English Campaign");
		
		newCampaign.setCampaignBrandName("wasim brand");
		
		newCampaign.setCampDescr("Camp Description");
		
		newCampaign.setSenderName("wasim");
		
		newCampaign.downloadCampEmailTemplate();
		
		Thread.sleep(2000);
		newCampaign.uploadCampEmailTemplate("./EmailTemplate.xlsx");
		
		newCampaign.setCampSubject("subject campaign name");
		
		newCampaign.setCampBanner("introduction banner");
		
		newCampaign.setCampBodyCopy("Hello wasim");
		
		newCampaign.setCampSignature("thank you camp");
		
		newCampaign.setContactInfo("#8/20", "Ananthapur", "Behind Post Office", "515774", "8179615605");
		
		newCampaign.setScheduledStartDate("05", "10", "2018");
		
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_ENTER);
		
		newCampaign.setCampaignTime("02:00 AM");
		
		newCampaign.clickSaveDraft();
		
		
	}
}
