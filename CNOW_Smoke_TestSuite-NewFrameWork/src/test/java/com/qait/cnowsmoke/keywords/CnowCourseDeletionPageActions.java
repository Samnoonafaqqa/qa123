package com.qait.cnowsmoke.keywords;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;

import com.qait.automation.getpageobjects.GetPage;


public class CnowCourseDeletionPageActions extends GetPage{

	public CnowCourseDeletionPageActions(WebDriver driver){
		    super(driver,"CnowCourseDeletion");
	
	}//end of constructor
	
	public void selectAllCourses(){
		logMessage("Selecting All the courses...");
		click(element("check_all_courses"));
		
	}//end of method
	
	public void deleteAllCourse(){
		try{
			driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		    logMessage("Clicking On 'Show All options link'...");
		    click(elementWithoutTry("link_show_all",""));
		    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		}catch(Exception e){
			logMessage("[INFO] Show All options link is not avialble.");
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			
		}//end of catch
		
		logMessage("Selecting Delete option from drop-down...");
		selectProvidedTextFromDropDown(element("list_courses_actions"),"Archive");
		logMessage("Clicking on OK button of(confirming deletion of all the courses) ...");
		click(element("btn_pop_ok"));
		
	}//end of method
	
		
}//end of class
