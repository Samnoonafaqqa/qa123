package com.qait.cnowsmoke.keywords;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.qait.automation.getpageobjects.GetPage;

public class CnowInstructorGradebookPageActions extends GetPage{

	public CnowInstructorGradebookPageActions(WebDriver driver) {
		super(driver,"CnowInstructorGradebook");
	   		
	}//end of constructor
   
	/**
	 * Click on Gradebook tab
	 */
	public void navigateToGradebookTab(){
		 logMessage("[INFO] Navigating to Gradebook Tab.");
		 waitForElement(3);
		 click(element("tab_gradebook"));
		 
		 
	}//end of method
	
	public void filterStudent(String stud){
		logMessage("[INFO] Entering student name.");
		waitForElement(1);
		element("input_findstud").clear();
		waitForElement(1);
		sendKeys(element("input_findstud"), stud);
		logMessage("[INFO] clicking on find button.");
		click(element("btn_find"));
		
	}//end of method
	
	public void verifyGradesFor(String assignment,String grades){
		logMessage("[INFO] Verifying grades for "+assignment);
		String assignmentId = element("resume_assignment",assignment).getAttribute("data-columnid");
		System.out.println("assignment-ID- "+assignmentId);
		if(assignment.contains("Reading")){
			Assert.assertTrue(Integer.parseInt(element("reading_score",assignmentId).getText().split(":")[2])>2);
			
		}//end of if
		else{
			if(grades.split("\\.")[1].equals("00%"))
				isStringMatching(element("inprogress_icon",assignmentId).getText().split("%")[0],grades.split("\\.")[0]);
			else
				isStringMatching(element("inprogress_icon",assignmentId).getText(),grades);
		}
		
		
	}//end of method
	
	
	/**
	 * verify student has taken the assignment, score is reflected on grade-book.
	 * Click on score link
	 */
	public void clickOnAndVerifyStudentScoreLink(){
		logMessage("[INFO] Verifying student score on Gradebook page.");
		verifyElementTextConatins("score_link","%");
		
		logMessage("[INFO] Clicking on Student Score Link.");
		isElementDisplayed("score_link");
		click(element("score_link"));
	}//end of method
	
	/**
	 * Select assignment take to delete.
	 */
	public void selectTakeAndDelete(){
		logMessage("[INFO] Selecting student take.");
		clickWithoutScroll(element("check_take"));
		waitForElement(1);
		
		logMessage("[INFO] Clicking on selected student take delete button.");
		clickWithoutScroll(element("btn_delete_selected_take"));
		waitForElement(1);
		
		logMessage("[INFO] Clicking on modular OK button on overlay.");
		click(element("btn_modularOK"));
		
	}//end of method
	
	/**
	 * Click on return without saving button and verify take is not appeared on gradebook page. 
	 */
	public void clickOnReturnButton(){
		logMessage("[INFO] Clicking on Retun without Saving button.");
		waitForElement(2);
		clickWithoutScroll(element("btn_return_without_save"));
				
	}//end of method 
	
	/**
	 * Verify take is deleted on gradebook page.
	 */
	public void verifyTakeDeletedOnGradebook(){
		logMessage("[INFO] Verifying take deletion on Gradebook page.");
		waitForElement(1);
		verifyElementTextConatins("score_link_not_taken", "not taken");
		
	}//end of method
	
	String assignmentID="";
	public void verifyInProgressIcon(String assignment){
		logMessage("[INFO] Verifying In-progress Icon is displayed.");
		assignmentID=element("resume_assignment",assignment).getAttribute("data-columnid");
		isStringMatching(element("inprogress_icon",assignmentID).getAttribute("class"),"incompleteButton");
		
	}//end of method
	
	public void clickOnInprogressIcon(){
		logMessage("[INFO] Clicking on In-progress Icon.");
		click(element("inprogress_icon",assignmentID));
		
	}//end of method
	
	public void verifyEGDPage(){
		waitForElement(2);
		isStringContians(getPageTitle(),"Edit Grade Details");
		
	}//end of method
	
}//end of class
