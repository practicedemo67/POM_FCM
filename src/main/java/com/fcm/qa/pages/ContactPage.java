package com.fcm.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.fcm.qa.base.TestBase;
import com.fcm.qa.util.TestUtil;

public class ContactPage extends TestBase{
@FindBy(xpath="//button[text()='New']")
WebElement newButton;
@FindBy(xpath="//div[text()='Contacts']")
WebElement contactText;
@FindBy(xpath="//input[@name='first_name']")
WebElement firstname;
@FindBy(xpath="//input[@name='last_name']")
WebElement lastname;
@FindBy(xpath="//button[text()='Save']")
WebElement saveButton;
@FindBy(xpath="//tbody/tr[1]/td[2]")
WebElement created_user;


public ContactPage() {
	PageFactory.initElements(driver,this);
}

public boolean verifyContactsPage() {
	return contactText.isDisplayed();
}
public void clickOnNew() {
	newButton.click();
	TestUtil.implicitWait();
}
public void enterUserDetails(String fname,String lname) {
	
		firstname.sendKeys(fname);
		lastname.sendKeys(lname);
		saveButton.click();
		TestUtil.implicitWait();
	}
public String verifyUserCreated() {
	TestUtil.implicitWait();
	return created_user.getText();
}
}

