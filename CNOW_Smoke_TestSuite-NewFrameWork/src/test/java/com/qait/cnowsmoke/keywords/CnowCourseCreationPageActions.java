package com.qait.cnowsmoke.keywords;

import java.io.File;





import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebElement;







import com.qait.automation.utils.*;
import com.qait.automation.getpageobjects.GetPage;

public class CnowCourseCreationPageActions extends GetPage {
	
	public static String courseID;
	public static String courseNameManually;
	public static String courseNameCopy;
	public static String courseNameImport;
	
	public CnowCourseCreationPageActions(WebDriver driver) {
		super(driver, "CnowCourseCreationPage");
 				
	}//end of constructor

	/**
	 * Verify Course Page.
	 * 
    */
	public void VerifyUserIsOnCourseTab() {
		logMessage("[INFO] Verifying User is on Courses Tab.");
		verifyPageTitleExact();
	
	}//end of method	

	/**
	 * Click on Create Course button
	 * 
	 */
	public void clickOnCreateCourseButton() {
		logMessage("[INFO] Clicking on Create Course Button.");
		waitForElement(2);
		click(element("btn_create_course"));
				
	}//end of method
	
	/**
	 * Verifying user is on Choose process page.
	 */
	public void verifyUserIsOnChooseProcessPage(){
		 logMessage("[INFO] Verifying Choose process page");
		 wait.waitForElementToBeVisible(element("tab_chooseType_heading"));
		 waitForElement(1);
		 verifyElementTextConatins("tab_chooseType_heading",element("tab_chooseType_heading").getText());
				
	}//end of method
	
	/**
	 * This method will choose 'Build a Course Manually' option as choose type and click on continue button.
	 */
	public void chooseTypeAsManuallyAndContinue(){
		logMessage("Choosing Course creation type as manually...");
		click(element("radio_create_course_manually"));
		logMessage("Clicking on Continue button...");
		click(element("btn_continue"));
		 
	}//end of method
	
	
	/**
	 * This method will choose 'Copy an Existing Course' option as choose type and click on continue button.
	 */
	public void chooseTypeAsCopyCourseAndContinue(){
		logMessage("Choosing Course creation type as Copy...");
		click(element("radio_create_course_copy"));
		logMessage("Clicking on Continue button...");
		click(element("btn_continue"));
		 
	}//end of method
	
	/**
	 * This method will choose Import a course option as choose type and click on continue button.  
	 */
	public void chooseTypeAsImportCourseAndContinue(){
		logMessage("Choosing Course creation type as Import...");
		element("radio_create_course_import").click();
		logMessage("Clicking on Continue button...");
		click(element("btn_continue"));
		 
	}//end of method
	
	/**
	 * This method will browse the exported course 'CNowCourse15Jun_131920141015.ecx' and click on continue button. 	
	 */
	public void browseExportedCourseAndContinue(String course){
		try {
			waitForElement(1);
			String currentDir = System.getProperty("user.dir");
			WebElement e = element("btn_browse_course");
			waitForElement(1);
			((RemoteWebElement) e).setFileDetector(new LocalFileDetector());
			if (System.getProperty("os.name").contains("Windows")) {
				e.sendKeys(currentDir + File.separator
						+ course);
			} else {
				e.sendKeys(currentDir + File.separator
						+ course);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		waitForElement(1);
		logMessage("Clicking on Continue button...");
		click(element("btn_continue"));
		
	}//end of method 
	
	/**
	 * This method select a created course from drop down appeared after selecting course creation option
	 * as Copy an existing Course.  
	 */
	public void selectCreatedCourseFromDropDownAndContinue(String courseToCreateCopy){
		logMessage("Selecting Course from drop down...");
		selectProvidedTextFromDropDown(element("dropDown_choose_course"),courseToCreateCopy);
		logMessage("Going to click on Continue Button...");                          		
		element("btn_create_course").click();
		courseNameCopy=element("input_course_name").getText();
		logMessage("Going to click on Create Course Button...");                          		
		element("btn_create_course").click();
	  		
	}//end of method
	
	/** 
	 * Fill course information and create course.
    */
	public void fillCourseInformationSyllabusAndCreate() {
		switchToDefaultContent();
		logMessage("Verifying Course Information tab...");
		verifyElementTextConatins("tab_courseInfo_heading",element("tab_courseInfo_heading").getText());
		logMessage("Selecting book from drop-down...");
		try{
			driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		selectProvidedTextFromDropDown(element("dropDown_books"),"Warren/Reeve/Duchac: Accounting, 25th Edition");
		driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
		}catch(AssertionError e){
			System.out.println("Single book is available in Instructor Account.");
			driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);	
		}
		switchToActiveElement();
		logMessage("Entering course name...");
		click(element("input_course_name"));
		waitForElement(1);
		element("input_course_name").clear();
		courseNameManually= YamlReader.getData("course_name")+" "+DateUtil.getTimeInMiliSeconds();
		element("input_course_name").sendKeys(courseNameManually);
		
		switchToActiveElement();
		logMessage("Entering course start date...");
		click(element("input_start_date"));
		element("input_start_date").clear();
		element("input_start_date").sendKeys(DateUtil.getDate());
				
		logMessage("Entering course year as 2015...");
		element("input_end_date").clear();
		element("input_end_date").sendKeys("Oct 31, 2016 12:00 AM");
						
		logMessage("Course Information is done...");                          		
		click(element("input_course_name"));
		
		 logMessage("clicking on Syllabus radio Button..."); 
		 click(element("radio_syllabus"));
		 
		 sendKeys(element("input_syllabus"), "https://www.google.com"); 
		
		
		
		logMessage("Going to click on Create Course Button...");                          		
		click(element("btn_create_course"));
		
	}//end of method
	
	
	/**
	 * verify course is created
	 */
	public void verifyCourseCreation(String courseName){
		logMessage("Verifying Course Creation...");
		waitForElement(3);
		verifyElementTextConatins("header_courseCreated",courseName);
	    String url=driver.getCurrentUrl();
	    String strs[]=url.split("=");
        courseID=strs[1];
        DataReadWrite.writeDataToFile("studentCourse",courseName);
	     
	}//end of method
	
	public void verifySectionsInCourse(){
		logMessage("[INFO] Verifying sections available.");
		Assert.assertTrue(elements("section_created").size()>1);
		logMessage("Available Sections are "+elements("section_created").size());
		
	}//end of method
	    
	public void verifyAssignmentInCourse(){
		logMessage("[INFO] Verifying assignment available.");
		waitForElement(1);
		isElementDisplayed("assignment_created");
		
	}//end of method
	
}//end of class
