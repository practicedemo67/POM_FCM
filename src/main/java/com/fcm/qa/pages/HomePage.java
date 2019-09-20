package com.fcm.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.fcm.qa.base.TestBase;
import com.fcm.qa.util.TestUtil;


public class HomePage extends TestBase{
	@FindBy(xpath ="//div[@class='header item']")
	WebElement logo;
	@FindBy(className="user-display")
	WebElement login_user;
	@FindBy(xpath="//span[text()='Contacts']")
	WebElement contacts;
	
	public HomePage() {
		PageFactory.initElements(driver, this);
	}
	public boolean verifyLogo() {
		return logo.isDisplayed();
	}
	public String verifyUser() {
		return login_user.getText();
	}
	public String verifyTitle() {
		return driver.getTitle();
	}
	public ContactPage navigateToContacts() {
		TestUtil.clickUsingJS(contacts);
		TestUtil.implicitWait();
		return new ContactPage();
	}
}
