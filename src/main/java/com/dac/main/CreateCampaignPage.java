package com.dac.main;

import java.awt.Desktop.Action;
import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateCampaignPage extends BasePage{

	WebDriver driver;
	Select select;
	Actions action;
	JavascriptExecutor js;
	WebDriverWait wait;
	
	public CreateCampaignPage(WebDriver driver) {
		
		wait=new WebDriverWait(driver, 20);
		this.driver = driver;
		action = new Actions(driver);
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name="campaigntype")
	private WebElement campaignType;
	
	@FindBy(name="campaignLanguage")
	private WebElement campaignLang;
	
	@FindBy(name="campaignName")
	private WebElement campaignName;
	
	@FindBy(xpath="//input[@ng-model='BrandName']")
	private WebElement campaignBrandName;
	
	@FindBy(name="description")
	private WebElement campaignDescription;
	
	@FindBy(xpath="//div[@title='upload']")
	private WebElement uploadCampaignLogo;
	
	@FindBy(name="sendername")
	private WebElement campaignSenderName;
	
	@FindBy(name="subject")
	private WebElement campaignSubject;
	
	@FindBy(name="introbanner")
	private WebElement campaignIntroBanner;
	
	@FindBy(name="body")
	private WebElement campaignBodyCopy;
	
	@FindBy(name="signature")
	private WebElement campaignSignature;
	
	// --------------- Contact info section -----------------------
	
	@FindBy(xpath="//input[@ng-model='BrandAddressLine1']")
	private WebElement contactAddressLine1;
	
	@FindBy(xpath="//input[@ng-model='BrandAddressLine2']")
	private WebElement contactAddressLine2;
	
	@FindBy(xpath="//input[@ng-model='BrandCity']")
	private WebElement contactInfoBrandCity;
	
	@FindBy(xpath="//input[@ng-model='BrandStProv']")
	private WebElement contactInfoBrandStProv;
	
	@FindBy(xpath="//input[@ng-model='BrandZipPostalCode']")
	private WebElement contactInfoZipCode;
	
	@FindBy(xpath="//input[@ng-model='BrandPhoneNumber']")
	private WebElement contactInfoPhoneNumber;
	
	//---------------------------------------------------------------
	
	@FindBy(name="startdate")
	private WebElement scheduledStartDate;
	
	@FindBy(name="time")
	private WebElement scheduledTime;
	
	@FindBy(id="emailTemplate")
	private WebElement downloadEmailTemplate;
	
	@FindBy(xpath="//input[@ng-model='file']")
	private WebElement uploadEmailTemplate;
	
	@FindBy(xpath="//button[@class='draftBtn']//span")
	private WebElement saveDraftBTN;
	
	@FindBy(className="editDraft")
	private WebElement viewAllCampaignBTN;
	
	@FindBy(className="editDraft1")
	private WebElement continueEditBTN;
	
	/** indexes for 
	 * 
	 * @param campTypeIndex 
	 * Location Index : 1
	 * Brand Index    : 2
	 * MLC Index      : 3	
	 * @throws InterruptedException */
	public void selectCampType(int campTypeIndex) throws InterruptedException {
		Thread.sleep(5000);
		while(true) {
			if(campaignType.isDisplayed()) {
				select = new Select(campaignType);
				select.selectByIndex(campTypeIndex);
				break;
			}	
		}
	}
	
	
	/** indexes of campaign languages 
	 * 
	 * English        : 1	   French (Canada) : 2	  French (France) : 3	
	 * German         : 4	   Italian		   : 5	  Spanish (Mexico): 6
	 * Spanish(Spain) : 7      Swedish         : 7							*/
	public void selectCampLang(int CampLangIndex) {
		
		select = new Select(campaignLang);
		select.selectByIndex(CampLangIndex);
	}
	
	public void setCampaignName(String campName) {
		
		scrollByElement(campaignName, driver);
		campaignName.sendKeys(campName);
	}
	
	public void setCampaignBrandName(String campBrandName) {
		
		scrollByElement(campaignBrandName, driver);
		campaignBrandName.sendKeys(campBrandName);
	}
	
	public void setCampDescr(String campDescription) {
		
		campaignDescription.sendKeys(campDescription);
	}
	
	public void setSenderName(String campSenderName) {
		
		scrollByElement(campaignSenderName, driver);
		campaignSenderName.sendKeys(campSenderName);
	}
	
	public void setCampSubject(String campSubject) {
		
		scrollByElement(campaignSubject, driver);
		campaignSubject.sendKeys(campSubject);
	}
	
	public void setCampBanner(String campIntroBanner) {
		campaignIntroBanner.sendKeys(campIntroBanner);
	}
	
	public void setCampBodyCopy(String campBodyCopy) {
		campaignBodyCopy.sendKeys(campBodyCopy);
	}
	
	public void setCampSignature(String campSignaturee) {
		scrollByElement(campaignSignature, driver);
		campaignSignature.sendKeys(campSignaturee);
	}
	
	public void setContactInfo(String addressL1, String city, String contactInfoSTProv, String zipCode, String phoneNum, String... addressL2) {
		
		scrollByElement(contactAddressLine1, driver);
		contactAddressLine1.sendKeys(addressL1);
		contactAddressLine2.sendKeys(addressL2);
		contactInfoBrandCity.sendKeys(city);
		contactInfoBrandStProv.sendKeys(contactInfoSTProv);
		contactInfoZipCode.sendKeys(zipCode);
		contactInfoPhoneNumber.sendKeys(phoneNum);
	}
	
	public void setScheduledStartDate(String month_MM, String date_DD, String year_YYYY) {
		
		scrollByElement(scheduledStartDate, driver);
		scheduledStartDate.sendKeys(month_MM+"/"+date_DD+"/"+year_YYYY);
	}
	
	/** TimeValue_AM_PM could be in the format of "hh:mm AM/PM" */
	public void setCampaignTime(String TimeValue_AM_PM) {
		
		scrollByElement(scheduledTime, driver);
		select = new Select(scheduledTime);
		select.selectByVisibleText(TimeValue_AM_PM);
	}
	
	public void downloadCampEmailTemplate() {
		
		scrollByElement(downloadEmailTemplate, driver);
		downloadEmailTemplate.click();

	}
	
	public void uploadCampEmailTemplate(String ExcelPath) {
		
		File file = new File(ExcelPath);
		String filePath = file.getAbsolutePath();
		uploadEmailTemplate.sendKeys(filePath);	
	}
	
	/*public void uploadLogo(String LogoPath) {
		
		scrollByElement(uploadCampaignLogo, driver);
		Actions choose = action.moveToElement(uploadCampaignLogo);
		//action.click();
		File file = new File(LogoPath);
		String filePath = file.getAbsolutePath();
		choose.sendKeys(filePath).perform();
		//uploadCampaignLogo.sendKeys(filePath);
	}*/
	
	public void clickSaveDraft() {
		scrollByElement(saveDraftBTN, driver);
		saveDraftBTN.click();
	}
	
	public void clickViewAllCampaignBTN() throws InterruptedException {
		Thread.sleep(5000);
		action.moveToElement(viewAllCampaignBTN).click(viewAllCampaignBTN).perform();
	}
	
	public void clickContinueEditBTN() throws InterruptedException {
		Thread.sleep(5000);
		action.moveToElement(continueEditBTN).click(continueEditBTN).perform();
	}
	
	public void clickUploadLogo(String filePath) {
		scrollByElement(uploadCampaignLogo, driver);
//		uploadCampaignLogo.click();
		uploadCampaignLogo.sendKeys(filePath);
	}
	
	public void openDownloadedFile(File downloadPath, String fileName) throws Exception {
		waitForDownloadToComplete(downloadPath, fileName);
	}
}
