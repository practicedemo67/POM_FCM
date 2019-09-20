package com.fcm.qa.base;

import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.fcm.qa.util.TestUtil;
import com.fcm.qa.util.WebEventListen;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {
	public static Properties p;
	public static WebDriver driver;
	public static WebEventListen w;
	public static EventFiringWebDriver edriver;
	public static ExtentTest logger;
	public TestBase() {
		p=new Properties();
		try {
			FileInputStream fis = new FileInputStream("F:\\selenium\\POM_FREECRM\\src\\main\\java\\com\\fcm\\qa\\config\\config.properties");
			p.load(fis);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
public static void setup() {
	String browser=p.getProperty("browser");
	if (browser.equalsIgnoreCase("chrome")) {
		System.setProperty("webdriver.chrome.logfile","./log/chrome.log");
		//System.setProperty("webdriver.chrome.driver", p.getProperty("chromePath"));
		WebDriverManager.chromedriver().setup();
		ChromeOptions c=new ChromeOptions();
		c.addArguments("--disable-notifications");
		driver=new ChromeDriver(c);
	} else if(browser.equalsIgnoreCase("IE")){
		//System.setProperty("webdriver.ie.driver",  p.getProperty("IEPath"));
		WebDriverManager.iedriver().setup();
		driver=new InternetExplorerDriver();
	}else if(browser.equalsIgnoreCase("firefox")){
		System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE,"./log/firefox.log");
		//System.setProperty("webdriver.gecko.driver", p.getProperty("FFPath"));
		WebDriverManager.firefoxdriver().setup();
		driver=new FirefoxDriver();
	}
	edriver=new EventFiringWebDriver(driver);
	w=new WebEventListen();
	edriver.register(w);
	driver=edriver;
	driver.manage().window().maximize();
	driver.manage().deleteAllCookies();
	TestUtil.pageloadtimeout();
	TestUtil.implicitWait();
	driver.get(p.getProperty("url"));
	TestUtil.implicitWait();
}
public static void logout() {
	driver.quit();
}
}
