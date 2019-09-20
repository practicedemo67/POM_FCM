package com.fcm.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.fcm.qa.base.TestBase;
import com.fcm.qa.pages.ContactPage;
import com.fcm.qa.pages.HomePage;
import com.fcm.qa.pages.LoginPage;
import com.fcm.qa.util.TestUtil;

public class ContactPageTest extends TestBase{
	LoginPage login;
	HomePage homepage;
	ContactPage contact;
	@BeforeMethod()
	public void initialization() {
		setup();
		login=new LoginPage();
		TestUtil.implicitWait();
		login.navigateToLoginPage();
		homepage=login.loginToHomepage(p.getProperty("username"), p.getProperty("password"));
		contact=homepage.navigateToContacts();
	}
	@Test(priority=2)
	public void verifyPage() {
		Assert.assertTrue(contact.verifyContactsPage());
	}
	@Test(priority=1,dataProvider="newContactdata")
	public void verifyContactCreation(String firstname,String lastname) {
		contact.clickOnNew();
		TestUtil.implicitWait();
		contact.enterUserDetails(firstname, lastname);
		
		
	}
	@DataProvider()
	public Object[][] newContactdata() {
		Object [][] data=TestUtil.readExcelSheet("Sheet1");
		return data;
	}
	
	@AfterMethod
	public void closeBrowser() {
		logout();
		System.out.println("after method");
	}
}
