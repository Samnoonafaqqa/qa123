package com.qait.cnowsmoke.keywords;

import org.openqa.selenium.WebDriver;

import com.qait.automation.getpageobjects.GetPage;


public class CnowHomePageActions extends GetPage {

	
	public CnowHomePageActions(WebDriver driver){
		super (driver, "CnowHomePage");
				
	}//end of constructor

	
	/**
	 * Verifying user is on CNow home page.
	 */
	public void VerifyUserIsOnCNowHomePage() {
		logMessage("Verifying User is on CNow Home Page...");
		wait.waitForPageTitleToBeExact("Home");
	 	
	}//end of method
	
	
	/**
	 * Clicking on courses Tab
	 */
	public void clickOnCourseTab(){
		logMessage("Clicking on Courses tab...");
        click(element("tab_courses"));	 	
		
	}//end of method
	
	
	/**
	 * Log out Instructor from CNow application. 
	 */
	public void instructorLogOUT(){
		logMessage("Clicking on Global Action Menu...");
		click(element("icon_global"));
	  
		logMessage("Clicking on LogOUT link...");
		click(element("link_logOut"));
		
	}//end of method
	
}//end of class
