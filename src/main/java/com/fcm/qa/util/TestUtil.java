package com.fcm.qa.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.fcm.qa.base.TestBase;

public class TestUtil extends TestBase{
public static void pageloadtimeout() {
	driver.manage().timeouts().pageLoadTimeout(30,TimeUnit.SECONDS);
}
public static void implicitWait() {
	driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
}
public static void clickUsingJS(WebElement button) {
	JavascriptExecutor j=(JavascriptExecutor)driver;
	j.executeScript("arguments[0].click();",button);
}
public static void waitForElement(WebElement element) {
	(new WebDriverWait(driver,30)).until(ExpectedConditions.elementToBeClickable(element));
}
public static Object[][] readExcelSheet(String sheet) {
Object[][] obj=null;
String path="F:\\selenium\\POM_FREECRM\\src\\main\\java\\com\\fcm\\qa\\testData\\contactDetails.xlsx";
try {
	FileInputStream fis=new FileInputStream(path);
	Workbook w=new XSSFWorkbook(fis);
	Sheet sh=w.getSheet(sheet);
	obj=new Object[sh.getLastRowNum()][sh.getRow(0).getLastCellNum()];
	for(int i=0;i<sh.getLastRowNum();i++) {
		for(int j=0;j<sh.getRow(0).getLastCellNum();j++) {
			obj[i][j]=sh.getRow(i+1).getCell(j).toString();
		}
	}
	w.close();
	fis.close();
} catch (Exception e) {
	e.printStackTrace();
}
return obj;
}
public static String takeScreenshot() {
	File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	String path="F:\\selenium\\POM_FREECRM\\test-output\\screenshot\\error"+System.currentTimeMillis()+".png";
	try {
		FileHandler.copy(src, new File(path));
	} catch (IOException e) {
		
		e.printStackTrace();
	}
	System.out.println("screenshot taken");
	return path;
}
}
