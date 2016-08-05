package com.qait.cnowsmoke.keywords;

import org.openqa.selenium.WebDriver;

import com.qait.automation.getpageobjects.GetPage;



   /**
    * The Class SSOLoginPage.
   */
   public class SSOLoginPageActions extends GetPage {

	 WebDriver driver;
   
	public SSOLoginPageActions(WebDriver driver) {
		super(driver,"SSOLoginPage");
		this.driver = driver;
       	
	}//end of constructor


	/**
	 * Verify SSO login Page.
	 * 
    */
	public void VerifyUserIsOnSSOLoginPage() {
		logMessage("Verifying User is on SSO Login Page...");
	//	verifyPageTitleExact();
	
	}//end of method

	
	/**
	 * Enter User name, password and click on Submit button. 
	 **/
	public void enterEmailIdPasswordAndClickSubmit(String userName,String password) {
		element("inp_UserName").clear();
		logMessage("Entering User Name...");
		element("inp_UserName").sendKeys(userName);
		element("inp_password").clear();
		logMessage("Entering User Password...");
		element("inp_password").sendKeys(password);
		logMessage("Clicking on submit button...");
		element("btn_SignIn").click();
	
	}//end of method
	
}//end of class
