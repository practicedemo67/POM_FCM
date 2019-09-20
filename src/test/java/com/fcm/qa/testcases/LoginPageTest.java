package com.fcm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fcm.qa.base.TestBase;
import com.fcm.qa.pages.HomePage;
import com.fcm.qa.pages.LoginPage;
import com.fcm.qa.util.TestUtil;

public class LoginPageTest extends TestBase {
LoginPage login;
HomePage homepage;
@BeforeMethod
public void initialization() {
	setup();
	login=new LoginPage();
	TestUtil.implicitWait();
	login.navigateToLoginPage();
	TestUtil.implicitWait();
	System.out.println("before method");
}
@Test(priority=1)
public void verifyLoginPageTitle() {
	Assert.assertEquals(login.verifyTitle(),"CRM");
	System.out.println("test1");
}
@Test(priority=2)
public void verifyLogin() {
	homepage=login.loginToHomepage(p.getProperty("username"),p.getProperty("password"));
	TestUtil.implicitWait();
	Assert.assertEquals(homepage.verifyUser(),"practice demo");
	System.out.println("test 2");
}
@AfterMethod
public void closeBrowser() {
	logout();
}
}
