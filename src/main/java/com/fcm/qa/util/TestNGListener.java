package com.fcm.qa.util;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.MediaEntityModelProvider;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.fcm.qa.base.TestBase;

public class TestNGListener extends TestBase implements ITestListener{
	public static ExtentHtmlReporter reporter;
	public static ExtentReports report;
	public static ExtentTest test;
	
	public void onTestStart(ITestResult result) {
		logger=test.createNode(result.getName());	
		System.out.println("Test "+result.getName()+" started");
		logger.info("Test "+result.getName()+" started");
	}

	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		System.out.println("Test "+result.getName()+" passed");
		logger.pass("Test "+result.getName()+" passed");
		report.flush();
	}

	public void onTestFailure(ITestResult result) {
		
		String path=TestUtil.takeScreenshot();
		try {
			logger.fail("Test "+result.getName()+" failed",MediaEntityBuilder.createScreenCaptureFromPath(path).build() );
			report.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		System.out.println("Test "+result.getName()+" skipped");
		logger.skip("Test "+result.getName()+" skipped");
		report.flush();
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext context) {
		String path="F:\\selenium\\POM_FREECRM\\test-output\\extentReport\\Extentreport.html";
		reporter=new ExtentHtmlReporter(path);
		reporter.config().setReportName(context.getSuite().getName());
		reporter.config().setTheme(Theme.DARK);
		report = new ExtentReports();
		report.attachReporter(reporter);
		report.setSystemInfo("User",p.getProperty("username"));
		report.setSystemInfo("browser", p.getProperty("browser"));
		report.setSystemInfo("Application URL", p.getProperty("url"));
		System.out.println("Test "+context.getName()+" started");
		test=report.createTest(context.getName());
	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		System.out.println("Test "+context.getName()+" completed");
		
		report.flush();
	}

}
