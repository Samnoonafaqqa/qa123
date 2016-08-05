package com.qait.cnowsmoke.keywords;

import org.openqa.selenium.WebDriver;

import com.qait.automation.getpageobjects.GetPage;
import com.qait.automation.utils.DataReadWrite;
import com.qait.automation.utils.YamlReader;

public class CnowStudentEnrollmentPageActions extends GetPage {

	public CnowStudentEnrollmentPageActions(WebDriver driver) {
		super(driver, "CnowStudentEnrollment");
	
	}//end of constructor 

	/**
	 * This method will enroll student using boarding pass.
	 * It will follow second step of boarding pass, Login to SSO dashboard.
	 */
	public void loginToStudentSSO(){
		logMessage("Launching SSO Dashboard...");
		wait.waitForElementToBeVisible(element("link_course_key"));
		String courseKey=element("link_course_key").getText().split("course/")[1];
		DataReadWrite.writeDataToFile("CourseKey",courseKey);
		
		driver.get(element("link_course_key").getAttribute("href"));
		logMessage("Entering Student name...");
		wait.waitForElementToBeVisible(element("input_stu_name"));
		click(element("input_stu_name"));
		element("input_stu_name").sendKeys(YamlReader.getData("users.student.username"));
		logMessage("Entering Student password...");
		click(element("input_stu_pass"));
		waitForElement(1);
		element("input_stu_pass").sendKeys(YamlReader.getData("users.student.password"));
		logMessage("Clicking On logIn button...");
		click(element("btn_submit"));
		
	}//end of method
	
	/**
	 * Continue enrollment with access code
	 */	
	public void enterAccessCode(){
		
		
	}//end of method
	
	
	/**
	 * Continue with free trail 
	 */
	public void launchCNowApp(){
		 logMessage("launching CNow Application...");
		 waitForElement(2);
		 String getURL= element("btn_open").getAttribute("onclick");
		 System.out.println("URL---"+getURL);
		 getURL = getURL.substring(getURL.indexOf("http://"), getURL.indexOf("\',\'"));
		 System.out.println("URL---"+getURL);
		 String regex = "http://[a-zA-Z]+\\.ilrn\\.com";
		 String replacement = YamlReader.getData("switch_url");
		 getURL = getURL.replaceFirst(regex, replacement);
		 driver.get(getURL);
		 waitForElement(3);
		 
	}//end of method
	
	/**
	 * Verify student Dashboard.
	 */
	public void verifyStudentEnrollment(){
		logMessage("Verifying Student Enrollment...");
		verifyPageTitleExact();
		
	}//end of method
	
	public void logoutSSO(){
		logMessage("Logging out Student SSO...");
		click(element("link_globalUserIcon"));
		click(element("link_logout"));
			
	}//end of method
	
	public void logoutSSODashBoard(){
		logMessage("Clicking on SignOut link...");
		wait.waitForElementToBeClickable(element("link_SSO_logout"));
		click(element("link_SSO_logout"));
				
	}//end of method
	
}//end of class
