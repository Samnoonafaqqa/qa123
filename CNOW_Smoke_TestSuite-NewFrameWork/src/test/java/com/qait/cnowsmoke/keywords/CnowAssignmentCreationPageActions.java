package com.qait.cnowsmoke.keywords;


import org.openqa.selenium.WebDriver;

import com.qait.automation.getpageobjects.GetPage;
import com.qait.automation.utils.DataReadWrite;
import com.qait.automation.utils.DateUtil;



public class CnowAssignmentCreationPageActions extends GetPage{

	public CnowAssignmentCreationPageActions(WebDriver driver){
		super(driver,"CnowAssignmentCreation");
				
	}//end of constructor
	
	/**
	 * Click on assignments Tab 
	 */
	public void clickOnAssignmentsTab(){
		logMessage("Clicking on Assignments tab...");
		click(element("tab_assignment"));
		
	}//end of method
	
	/**
	 * Selecting a course from dropdown
	 */
	public void selectCourseFromDropDown(){
		logMessage("selection course from drop-down...");
		selectProvidedTextFromDropDownWithoutScroll(element("courselist"),DataReadWrite.getProperty("studentCourse"));
		
	}//end of method
	
	
	/**
	 * Click on CreateAssignment button.
	 */
	public void clickOnCreateAssignmentButton(){
		waitForElement(2);
		logMessage("Clicking on Create Assignment button...");
		wait.waitForElementToBeVisible(element("btn_create_assigment"));
		waitForElement(2);
		click(element("btn_create_assigment"));
		waitForElement(2);
		logMessage("Verify user is on Choose Assignment type page...");
		wait.waitForElementToBeVisible(element("choose_process_heading"));
		verifyElementTextConatins("choose_process_heading", "Create an Assignment for the Course:");
		
	}//end of method
	
	/**
	 * Choose Assignment type.
	 */
	public void chooseAssignmentTypeAndContinue(String AssignmentType){
		 logMessage("Choosing assignment type as "+AssignmentType+"...");
		 click(element("radio_btn_"+AssignmentType));
		 logMessage("Clicking on Continue button...");
		 click(element("btn_continue"));
		 logMessage("Verify user is on Enter Assignment Info page...");
		 waitForElement(1);
		 element("assignment_info_heading");
		 verifyElementTextConatins("assignment_info_heading", "Create a");	 
		
	}//end of method
	
	
	/**
	 * Enter assignment information
	 */
	public void enterAssigmentInfoAndContinue(String assignmentType){
	     logMessage("Entering Assignment name...");
	     element("input_name").clear();
	     waitForElement(1);
	     element("input_name").sendKeys("Assignment_"+assignmentType+" "+DateUtil.getCurrentDateTime());
	    		 
	     logMessage("Entering Assignment start date...");
	     waitForElement(1);
	     click(element("input_start_date"));
	     click(element("startDate"));
	     click(element("btn_date_done"));
	   
	     logMessage("Assignment info done...");
	     click(element("input_name"));	     
	     
	     logMessage("Clicking on continue button...");
	     click(element("btn_info_continue"));
	     	     
	}//end of method
	
	
	/**
	 * Select first chapter from available chapters and enter 1 for multiple types questions and click on continue.
	 */
	public void selectAChapterAndContinue(String assignmentType){
		
		// if assignment type is other than AST 
	    if(!assignmentType.equalsIgnoreCase("AST")){
		   logMessage("Verifying Choose content page...");
		   waitForElement(1);
		   element("avail_question_heading");
		   verifyElementTextConatins("avail_question_heading","Choose Content Source");
	    
	       logMessage("Clicking on expand book link...");
	       element("expand_books");
		   click(element("expand_books"));
		
		   logMessage("Selecting Chapter one...");
		   click(element("chapter_one"));
		
		   logMessage("Clicking on continue button...");
		   click(element("btn_chapter_continue"));
		
		   logMessage("Selecting None option for question...");
		   waitForElement(1);
		   click(element("link_select_none"));
	    
	    }///end of if
		   
		if(assignmentType.equalsIgnoreCase("Homework")){
			logMessage("Inputing 1 for multiple choice questions...");
			element("input_typeCount").sendKeys("1");
			logMessage("Clicking on continue button...");
			click(element("btn_chapter_continue"));
			logMessage("Verifying Included questions page...");
			verifyElementTextConatins("included_ques_heading","Included Questions");
				
		}else if(assignmentType.equalsIgnoreCase("Test")){
			logMessage("Inputing 2 for multiple choice questions...");
			element("input_typeCount").sendKeys("2");
			logMessage("Clicking on continue button...");
			click(element("btn_chapter_continue"));
			logMessage("Verifying Included questions page...");
			verifyElementTextConatins("included_ques_heading","Included Questions");
	
		}else if(assignmentType.equalsIgnoreCase("AST")){
			logMessage("Clicking on expand book link...");
	        waitForElement(1);   	
			element("ast_expand_books");
			click(element("ast_expand_books"));
			
			logMessage("Selecting Chapter one...");
			click(element("ast_chapter_one"));
			    
			logMessage("Clicking on continue btn...");
			click(element("btn_chapter_continue"));
			
			logMessage("Verifying AST Customize Assignable Study Tool page...");
			verifyElementTextConatins("ast_content_select","Customize Assignable Study Tool Options");
			
			logMessage("Clicking on continue btn...");
			click(element("btn_continue"));
		                  		
		}//end of else-if
		
	}//end of method
	 
	
	/**
	  * Include all the questions.
	  */
	public void includeAllQuestionsAndContinue(){
	   	    logMessage("Checking All questions...");
	   	    clickWithoutScroll(element("check_all_ques"));
			
			logMessage("Clicking include all questions button...");
			waitForElement(1);
			clickWithoutScroll(element("btn_include_all_ques"));
			
			logMessage("Clicking on continue button...");
			click(element("btn_chapter_continue"));
			
	}//end of method
	
	
	/**
	 * Reading Assignment: Select an Ebook and click on Done button.  
	 */
	public void selectAndVerifyEbookPageAndDone(){
		logMessage("Clicking on expand book link...");
		waitForElement(2);
		element("mindap_expand_books");
		clickWithoutScroll(element("mindap_expand_books"));
		waitForElement(1);
		logMessage("Selecting Chapter one...");
		element("mindap_chapter_3");
		waitForElement(1);
		clickWithoutScroll(element("mindap_chapter_3"));
		
		logMessage("Clicking on Done btn...");
		waitForElement(1);
		element("btn_continue");
		click(element("btn_continue"));
		
	}//end of method
	
	
	/**
	 * Verify Assignment page and done.
	 */
	public void verifyOptionsPageAndDone(String assignmentType){
		 logMessage("Verifying Assignmetn Options page...");
		   
		 waitForElement(2);
		 if(assignmentType.equalsIgnoreCase("test"))
		   verifyElementTextConatins("assignment_options_heading","Test Options");
		 else
		   verifyElementTextConatins("assignment_options_heading","Assignment Options");
	   
		 logMessage("Clicking on create assignment button...");
         click(element("btn_create_assignment"));
 
	}//end of method 
	
	
	/**
	 * Question pool creation.
	 */
	public void createAndVerifyQuestionPool(){
		logMessage("Checking All questions...");
		clickWithoutScroll(element("check_all_ques"));
		
		logMessage("Selecting create pool option from drop-down...");
		selectProvidedTextFromDropDownWithoutScroll(element("dropDown_items"),"Create New Pool from Selected Questions");
		
		logMessage("Clicking on create pool button...");
		waitForElement(1);
		clickWithoutScroll(element("btn_create_pool"));
		
		logMessage("Clicking on create button on overlay...");
		click(element("btn_create_on_overlay"));
		
		logMessage("Clicking on continue button...");
		click(element("btn_chapter_continue"));
		
	}//end of method
	
	
	/**
	 * Verify assignment creation
	 */
	public void verifyAssignmentCreation(String assignmentType){
		waitForElement(1);
		logMessage("Verifying assignment creation...");
		  if(assignmentType.equalsIgnoreCase("test"))
			     verifyElementTextConatins("assignment_created_heading","TEST CREATED:");
		  else
		     	 verifyElementTextConatins("assignment_created_heading","ASSIGNMENT CREATED: ");
		
	}//end of method
	
}//end of class
