package com.qait.cnowsmoke.keywords;

import org.openqa.selenium.WebDriver;

import com.qait.automation.getpageobjects.GetPage;
import com.qait.automation.utils.YamlReader;


public class SSOStudentDashboardPageActions extends GetPage{

	public SSOStudentDashboardPageActions(WebDriver driver){
		super(driver,"SSOStudentDashboardPage");
		
	}//end of constructor
	
	/**
	 * Verify user is on student SSO DashBoard page. 
	 */
	public void verifyUserIsOnSsoDashboard() {
		logMessage("Verifying user is on SSO Dashboard page...");
		verifyPageTitleExact();
	
	}//end of method

	/**
	 * Navigate to CNOW application. 
	 */
	public void navigateToCNOWApp(String course){
		logMessage("Verifying Accounting book is displayed...");
		isElementDisplayed("accountingBook");
		
		logMessage("Launching CNow Application...with course"+ course);
		waitForElement(2);
		String getURL= element("btn_open",course).getAttribute("onclick");
		getURL = getURL.substring(getURL.indexOf("http://"), getURL.indexOf("\',\'"));
		String regex = "http://[a-zA-Z]+\\.ilrn\\.com";
		String replacement = YamlReader.getData("switch_url");
		getURL = getURL.replaceFirst(regex, replacement);
		System.out.println("URL---"+getURL);		
		driver.get(getURL);
		handleOkButton();
		handleViewCoursesButton();
		
	}//end of method
	
	
}//end of class
