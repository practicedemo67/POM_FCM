package com.fcm.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.fcm.qa.base.TestBase;
import com.fcm.qa.util.TestUtil;

public class LoginPage extends TestBase {
//web elements/PO
@FindBy(xpath="//span[text()='Log In']")
WebElement login_button;
@FindBy(xpath="//input[@name='email']")
WebElement username;
@FindBy(xpath="//input[@name='password']")
WebElement password;
@FindBy(xpath="//div[text()='Login']")
WebElement submit_button;

//initialization
public LoginPage() {
PageFactory.initElements(driver, this);
}
//actions on page elements
public void navigateToLoginPage() {
	TestUtil.waitForElement(login_button);
	login_button.click();
}
public HomePage loginToHomepage(String user,String pword) {
	username.sendKeys(user);
	password.sendKeys(pword);
	TestUtil.implicitWait();
	TestUtil.clickUsingJS(submit_button);
	return new HomePage();
}
public String verifyTitle() {
	return driver.getTitle();
}


}
