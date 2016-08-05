package com.qait.cnowsmoke.keywords;

import org.openqa.selenium.WebDriver;
import com.qait.automation.getpageobjects.GetPage;
import com.qait.automation.utils.YamlReader;


public class SSODashboardPageActions extends GetPage {
	
	public SSODashboardPageActions(WebDriver driver) {
 		super(driver, "SsoDashboardPage");
 		
	}//end of constructor

	/**
	 * Verify user is on SSO DashBoard page. 
	 */
	public void verifyUserIsOnSsoDashboard() {
		logMessage("Verifying user is on SSO Dashboard page...");
		verifyPageTitleExact();
	
	}//end of method

	/**
	 * Select waac-25 book from drop down.
	 */
	public void selectBookFromDropDown() {
		logMessage("Selecting book from drop-down...");
		selectProvidedTextFromDropDown(element("drop_down_book"),YamlReader.getData("book_name"));
		
	}//end of method

	/**
	 * This method will take href associated with course link and edit this URL with switch URL.
	 * Then it will navigate to CNow application. 
	 */
	public void launchCNowApplication(){
		logMessage("Launching CNow Application...");
		String getURL= element("selected_book").getAttribute("href");
		System.out.println("URL-"+ getURL);
		
		System.out.println("getURL :"+getURL);
   	    String regex = "http://[a-zA-Z]+\\.ilrn\\.com";
  		String replacement = YamlReader.getData("switch_url");
		getURL = getURL.replaceFirst(regex, replacement);
		System.out.println("URL-"+ getURL);
		driver.get(getURL);
	
	}//end of method

}//end of class

