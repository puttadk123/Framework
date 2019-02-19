package com.learnautomation.testcases;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.learnautomation.utility.BrowserFactory;
import com.learnautomation.utility.ConfigDataProvider;
import com.learnautomation.utility.ExcelDataProvider;
import com.learnautomation.utility.Helper;

public class BaseClass {
	public WebDriver driver;
	public ExcelDataProvider excel;
	public ConfigDataProvider config;
	public ExtentReports report;
	public ExtentTest logger;
	
	@BeforeSuite
	public void setupSuite() {
		excel = new ExcelDataProvider();
		config = new ConfigDataProvider();
	
	ExtentHtmlReporter extent = new ExtentHtmlReporter(new File(System.getProperty("user.dir")+"/Reports/FreeCRM_" +Helper.getCurrentDateTime()+".html"));
	report = new ExtentReports();
	report.attachReporter(extent);
	}
	
	@BeforeClass 
	public void setup() {
		driver = BrowserFactory.startApplication(driver,config.getBrowser(),config.getStagingURL());	
	}
	
	@AfterClass 
	public void teardown() {
		BrowserFactory.quitBrowser(driver);
	}
	
	@AfterMethod
    public void teardownMethod(ITestResult result) throws Exception 
	{
	  if(result.getStatus()==ITestResult.FAILURE) {
		  
		  Helper.captureScreenshot(driver);
		  
		  logger.fail("Test failed",MediaEntityBuilder.createScreenCaptureFromPath(Helper.captureScreenshot(driver)).build());
	  } 
	  else if(result.getStatus()==ITestResult.SUCCESS) {
		  
		  Helper.captureScreenshot(driver);
		  
		  logger.pass("Test passed",MediaEntityBuilder.createScreenCaptureFromPath(Helper.captureScreenshot(driver)).build());
	  }
     	
	  else if(result.getStatus() == ITestResult.SKIP) {
		 //logger.skip(m)
	  }
		
	  report.flush();
	}
	
	

}
