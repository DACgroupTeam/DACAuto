package com.dac.main;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	WebDriver driver;
	
	public LoginPage(WebDriver driver) {
		//Initializing constructor
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="txt_username")
	WebElement login;
	
	@FindBy(id="txt_password")
	WebElement password;
	
	@FindBy(id="btlogin")
	WebElement submit;
	
	@FindBy(xpath="//div[@id='errordiv1']/span")
	WebElement warning;
	
	@FindBy(xpath="//img[@src='http://res.cloudinary.com/cloudasset/image/upload/v1461602331/map/images/f5o8ptkieiv15gbih1jc.png']")
	WebElement logo;
	
	public WebElement login() {
		return login;
	}
	
	public WebElement password() {
		return password;
	}
	
	public WebElement submit() {
		return submit;
	}
	
	public WebElement warning() {
		return warning;
	}
	
	public WebElement logo() {
		return logo;
	}
	

}
