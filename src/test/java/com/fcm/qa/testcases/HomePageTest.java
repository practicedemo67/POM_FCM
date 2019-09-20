package com.fcm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.fcm.qa.base.TestBase;
import com.fcm.qa.pages.HomePage;
import com.fcm.qa.pages.LoginPage;
import com.fcm.qa.util.TestUtil;

public class HomePageTest extends TestBase{
	HomePage homepage;
	LoginPage login;
@BeforeMethod()
public void initialization() {
	setup();
	login=new LoginPage();
	TestUtil.implicitWait();
	login.navigateToLoginPage();
	homepage=login.loginToHomepage(p.getProperty("username"), p.getProperty("password"));
}
@Test(priority=1)
public void verifyPageTitle() {
	Assert.assertEquals(homepage.verifyTitle(), "CRM");
}
@Test(priority=2)
public void verifyLoggedInUser() {
	Assert.assertEquals(homepage.verifyUser(), "practice demo");
}
@Test(priority=3)
public void verifyLogoDisplayed() {
	Assert.assertTrue(homepage.verifyLogo());
}
@AfterMethod
public void closeBrowser() {
	logout();
}
}
