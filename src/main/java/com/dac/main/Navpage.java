package com.dac.main;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Navpage {

	WebDriver driver;

	public Navpage(WebDriver driver) {
		// Initializing constructor
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "//a[@href='/Dashboard/AllLocations/']")
	WebElement AllLocations;

	@FindBy(id = "//a[@href='/Dashboard/AllGroups/']")
	WebElement AllGroups;

	@FindBy(xpath = "//a[@href='/Dashboard/VaReport/']")
	WebElement Visibility;

	@FindBy(xpath = "//a[@href='/Dashboard/AccuracyReport/']")
	WebElement Accuracy;

	@FindBy(xpath = "//a[@href='/Dashboard/AnalysisReport/']")
	WebElement Analysis;
	
	@FindBy(xpath = "//a[@href='/Dashboard/GoogleRanking/']")
	WebElement GoogleRanking;
	
	@FindBy(xpath = "//a[@href='/Dashboard/ReviewStream/']")
	WebElement ReviewStream;
	
	@FindBy(xpath = "//a[@href='/Dashboard/Review/ReviewReport/']")
	WebElement ReviewReport;
	
	@FindBy(xpath = "//a[@href=//a[@href='/ReportCard/Index/']]")
	WebElement ReportCard;
	
	@FindBy(xpath = "//a[@href='/CategorizedSentiment/Index/']")
	WebElement CategorizedSentiment;
	
	@FindBy(xpath = "//a[@href='/Review/FrequentKeywords/']")
	WebElement FrequentKeywords;
	
	
	

	public WebElement getGoogleRanking() {
		return GoogleRanking;
	}

	public WebElement getReviewStream() {
		return ReviewStream;
	}

	public WebElement getReviewReport() {
		return ReviewReport;
	}

	public WebElement getReportCard() {
		return ReportCard;
	}

	public WebElement getCategorizedSentiment() {
		return CategorizedSentiment;
	}

	public WebElement getFrequentKeywords() {
		return FrequentKeywords;
	}

	public WebElement getAccuracy() {
		return Accuracy;
	}

	public WebElement getAllGroups() {
		return AllGroups;
	}

	public WebElement getAllLocations() {
		return AllLocations;
	}

	public WebElement getAnalysis() {
		return Analysis;
	}

	public WebElement getVisibility() {
		return Visibility;
	}

}
