package com.learnautomation.utility;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import java.util.*;

public class Helper {

	//screenshots,alerts,frames,windows,sync issue,javascript executor
	
	public static String captureScreenshot(WebDriver driver) {
		
		
		File src =((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
		String screenpath = System.getProperty("user,dir")+ "/Screenshots/FreeCRM_" + getCurrentDateTime()+".png";
		
		try {
			FileHandler.copy(src,new File("./Screenshots/FreeCRM"+getCurrentDateTime()+".png"));
			System.out.println("screenshot captured");
			
		} catch (Exception e) {
			System.out.println("unable to capture screenshot" +e.getMessage());
		}
		
		return screenpath;
		
	}
	
	public static String getCurrentDateTime() {
		DateFormat customFormat = new SimpleDateFormat("MM_dd_yyyy_HH_mm_ss");
	    Date currentDate=new Date();
	    return customFormat.format(currentDate);
	}
	
	
	
	
	
	
	
	
	
	
}
