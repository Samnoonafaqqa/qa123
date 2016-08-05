package com.qait.cnowsmoke.keywords;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import com.qait.automation.getpageobjects.GetPage;
import com.qait.automation.utils.DataReadWrite;
import com.qait.automation.utils.YamlReader;

public class CnowStudentAssignmentTakePageActions extends GetPage{
             
	
	public CnowStudentAssignmentTakePageActions(WebDriver driver){
		  super(driver,"CnowStudentAssignmentTake");
		
	}//end of constructor
   
	
	/**
	 * Verifying user is on Assignment tab
	 */
	public void verifyAssignmentTab(){
		  verifyPageTitleExact();
		  		  
	}//end of method
	
	/**
	 * Select created course in which student is enrolled.
	 */
	public void selectCourseFromDown(String course){
		logMessage("Clicking on Select Course Drop-down...");
		waitForElement(1);
		clickWithoutScroll(element("drop_down_course"));
		waitForElement(2);
		logMessage("Selecting Course From Drop-down...");
		waitForElement(2);
	    System.out.println(course);
	    clickWithoutScroll(element("stud_course",course));
	 		
	}//end of method
	
	/**
	 * Click on Assignment Tab.
	 */
	public void clickOnAssignmentTab(){
	   logMessage("Clicking on Assignment tab...");
	   waitForElement(1);
	   click(element("tab_assignment"));
		
	}//end of method
	
	
	/**
    *  This method will click on Assignment take button 	
    */
   public void clickOnAssignmentTakeButton(String assingment){
	   logMessage("Clicking on Assignment take button...");
	   waitForElement(1);
	   click(element("btn_assignment_take",assingment));
	   
   }//end of method
   
   /**
    * This method will click on Start Assignment button 
    */
   public void clickOnAssignmentStartButton(){
	   waitForElement(1);
	   logMessage("Clicking on Start Assignment button...");
	   click(element("btn_assignment_start"));
	   
   }//end of method
  
   public void attemptReadingTypeAssignment(){
	   logMessage("[INFO] Clicking on Mindtap reader link");
	   switchToFrame(element("iframe_reading"));
	   try{
		   driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		   click(element("link_mindtap"));
		   driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	   }catch(Exception e){
		   driver.navigate().refresh();
		   waitForElement(5);
		   click(element("link_mindtap"));
		   driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	   }//end of catch
   }//end of method
   
   /**
    * This method will click on question item and select an option and will click on Check my work.
    */
   public void attemptAssignment(String choice){
	   waitForElement(2);
	   logMessage("Clicking on current item...");
	   click(element("radio_item"));
	   
	   waitForElement(1); 
	   logMessage("Clicking on question option...");
	   click(element("radio_option",choice));
	   waitForElement(2);
	   
	   waitForElement(1); 
	   logMessage("Clicking on check my work...");
	   click(element("link_checkMyWork"));
	   waitForElement(1);
	   	   
   }//end of method
   
   public void assignmentSaveAndExit(){
	   logMessage("[INFO] Clicking on Save and Exit button.");
	   waitForElement(2);
	   clickWithoutScroll(element("btn_save"));
	   waitForElement(2);
	   clickWithoutScroll(element("btn_exit_hw"));
	   waitForElement(2);
	   
   }//end of method
   
   public void clickOnResumeLink(){
	   logMessage("[INFO] Clicking on resume assignment link.");
	   click(element("link_resume"));
	   waitForElement(1);
	  
   }//end of method
   
   public void attemptTestAssignment(String choice){
	   logMessage("Clicking on current item...");
	   click(element("radio_item"));
	   
	   waitForElement(1);
	   logMessage("Clicking on question option...");
	   waitForElement(1);
	   click(element("radio_option",choice));
	   waitForElement(2);
	   	   
   }//end of method
   
   
   /* **
    * Verify user attempts whether answer is correct or incorrect. 
    */
   public void verifyUserAttempt(){
	   logMessage("Verifing user attempt correct or incorrect...");
	   
	   if(element("feedback_txt").getText().contains("Correct"))
	      verifyElementTextConatins("feedback_txt","Correct");
	   else
		   verifyElementTextConatins("feedback_txt","Incorrect"); 
	   
   }//end of method
   
   /**
    * Assignment submit.  
    */
   public void userAssignmentSubmit(){
	   waitForElement(1);
	   logMessage("Clicking on Submit for grading btn...");
	   click(element("btn_assignment_submit"));
	   waitForElement(1);
	   
	   logMessage("Clicking on modular button on confirmation dialog...");
	   click(element("btn_modular_submit"));
	   waitForElement(1);
	   
   }//end of method
   
   public void clickOnExitButton(){
	   logMessage("[INFO] Clicking on Exit button");
	   click(element("btn_exit"));
	   
   }//end of method
   
   public void verifyMindtapReaderPage(){
	   changeWindow(1);
	   waitForElement(4);
	   if(getPageTitle().contains("http")){
		   getRefreshWindow();
		   waitForElement(3);   
	   }//end of if
	   isStringContians(getPageTitle(), "MindTap");
	   waitForElement(1);
	   driver.close();
	   changeWindow(0);
	   switchToDefaultContent();
	 	   
   }//end of method
   
   public void attemptASTAssignment(){
	   switchToFrame(element("iframe_ast"));
	   logMessage("[INFO] Clicking on start Pre-Test link.");
	   waitForElement(2);
	   click(element("ast_pre_start_link"));
	   attemptASTQuiz(2);
	   logMessage("[INFO] Clicking on End Pre-Test button.");
	   waitForElement(2);
	   click(element("ast_pre_end"));
	   switchToDefaultContent();
	   click(element("pop_up_pre_end"));
	   switchToFrame(element("iframe_ast"));
	   waitForElement(1);
	   logMessage("[INFO] Verifying score of pre test - "+element("pre_score").getText().split("%")[0]);
	   Assert.assertTrue(Integer.parseInt(element("pre_score").getText().split("%")[0].trim())>=0);
	   logMessage("[INFO] Clicking on Post-Test start link.");
	   click(element("link_post_test"));
	   attemptASTQuiz(5);
	   waitForElement(1);
	   logMessage("[INFO] Clicking on End Post-Test button.");
	   waitForElement(1);
	   click(element("ast_pre_end"));
	   switchToDefaultContent();
	   click(element("pop_up_post_end"));
	   switchToFrame(element("iframe_ast"));
	   waitForElement(2);
	   logMessage("[INFO] Verifying score of post test - "+element("pre_score").getText().split("%")[0]);
	   Assert.assertTrue(Integer.parseInt(element("pre_score").getText().split("%")[0].trim())>=0);
	   logMessage("[INFO] Clicking on Assignment submit for grading button");
	   waitForElement(1);
	   switchToDefaultContent();
	   waitForElement(2);
	   click(element("btn_ast_submit"));
	   waitForElement(2);
	   click(element("btn_ast_submit_popup"));
	   waitForElement(1);
	   logMessage("[INFO]Verifying score on assignment finished page - "+element("score_finished_page").getText().split("\\.")[0]);
	   waitForElement(2);
	   Assert.assertTrue(Integer.parseInt(element("score_finished_page").getText().split("\\.")[0].trim())>=0); 
	   
   }//end of method
   
   public void attemptASTQuiz(int noOfQues){
	   int choice=1;
	   for(int i=1;i<=noOfQues;i++){
		   logMessage("[INFO] Attempting question "+i+" and choice "+ ++choice);
		   waitForElement(2);
		   clickUsingJS(element("ast_pre_choice",choice+""));
		   waitForElement(2);
		   clickUsingJS(element("ast_pre_ques_enter"));
		   waitForElement(2);
		   if(choice>3)choice=1;
	   }//end of for
	   
   }//end of method
   
   /**
    * Verify Assignment submition
    */
   public void verifyAssignmentSubmit(String assignment){
	   logMessage("Verifying Assignment finished page...");
	   waitForElement(2);
	   verifyElementTextConatins("header_assignment_finished","Finished");
	   DataReadWrite.writeDataToFile(assignment,element("score_on_finishedpage").getText());
	   
   }//end of method
   
   public void clickOnStudyToolsTab(){
	   waitForElement(1);
	   logMessage("[INFO] Clicking on Study tools tab.");
	   click(element("tab_studytools"));
	   
   }//end of method
   
   public void clickOnNewStudentUser(){
		logMessage("[INFO] Clicking on Create New User link");
		click(element("link_newStudentUser"));
				
	}//end of method
	
	public void ClickOnDontHaveAccLink(){
		waitForElement(1);
		logMessage("[INFO] Clicking on Don't have account link:");
		click(element("link_dontHaveAcc"));
		
	}//end of method
	
	public void enterEmailID(String email){
		logMessage("[INFO] Entering Email id of Student");	
		sendKeys(element("input_email"), email);
		DataReadWrite.writeDataToFile("NewStudent",email);
		logMessage("[INFO] New User "+email);
		logMessage("[INFO] Clicking on Create New Account button");	
		click(element("btn_createNewAcc"));
	
	}//end of method
	
	public void fillInformationFormAndSubmit(){
		waitForElement(1);
		sendKeys(element("input_FName"),"CNow");
		sendKeys(element("input_LName"), "StudTest");
		sendKeys(element("input_Pass"), "TAP1234");
		sendKeys(element("input_ConfirmPass"),"TAP1234");
		waitForElement(2);
		click(element("btn_arrow_que"));
		click(element("select_que"));
		sendKeys(element("input_Ans"),"qa");
		click(element("check_agreement"));
		click(element("btn_createMyAcc"));
		
	}//end of method
	
	public void enterAccessCodeSubmit(String code){
		logMessage("[INFO] Entering access code");
		waitForElement(2);
		sendKeys(element("input_accessCode"),code);
		click(element("btn_register"));
		waitForElement(2);
	}
   

	public void findInstitute(String institute) {

		click(element("drop_down_loc_select"));
		click(element("institute_southAmrca"));
		element("input_institute_name").sendKeys(institute);
		click(element("bttn_search"));
		
	}

	public void selectInstitute(String id) {
			waitForElement(1);
			click(driver.findElement(By.xpath("//input[@id='"+id+"']")));
			waitForElement(1);
			click(element("btn_continue"));
	}

	public void clickOnContinueButton(){
		logMessage("[INFO] Clicking on continue button.");
		click(element("btn_continue_courseInfo"));
		
	}//end of method
	
	public void clickOnCourseOpenButton(String courseKey){
		waitForElement(1);
		logMessage("[INFO] Clicking on Course Open button.");
		click(element("btn_open"));
				
	}//end of method
	
	public void TakeToMyCourseButton(){
		JavascriptExecutor js= (JavascriptExecutor)driver;
		waitForElement(2);
		String getURL=(String)	js.executeScript("return document.getElementById('courseURL').getAttribute('value')"); 
		System.out.println("URL - "+getURL);
		String replacement = YamlReader.getData("switch_url");
		
		if(!driver.getCurrentUrl().contains(replacement)){
			getURL=replacement+getURL.split(".com")[1];
			System.out.println("URL switched - "+getURL);
			driver.get(getURL);
		}
					
		
		}//end of method
	
	public void verifyNewAccountMessage(String msg){
		logMessage("[ASSERTION] Verifying new account message.");
		waitForElement(2);
		isStringContians(element("text_newaccount").getText(),msg);
		logMessage("[INFO] Clicking on New account button");
		click(element("btn_continue_new_account"));
		
	}//end of method
   
	public void clickOnStudyPlanViewButton(){
		logMessage("[INFO] Clicking on study plan view button");
		switchToFrame(element("iframe_studyTools_tab"));	
		switchToFrame(element("frame_inner_section"));
		waitForElement(1);
		clickWithoutScroll(element("btn_studyPlanView"));
		
	}//end of method
	
	public void clickOnTakeButton(){
		waitForElement(1);
		logMessage("[INFO] Clikcing on Take button");
		clickWithoutScroll(element("btn_take_quiz"));
		
	}//end of method
	
	public void clickOnEndPreTest(){
		logMessage("[INFO] Clicking on End Pre-Test button.");
		waitForElement(2);
		clickWithoutScroll(element("ast_pre_end"));
		handleAlert();
		switchToDefaultContent();
		
	}//end of method
	
	public void verifyScoreOfPreTest(){
		switchToFrame(element("iframe_studyTools_tab"));	
		switchToFrame(element("frame_inner_section"));
		waitForElement(1);
		logMessage("[INFO] Verifying score of pre test - "+element("pre_score").getText().split("%")[0]);
		Assert.assertTrue(Integer.parseInt(element("pre_score").getText().split("%")[0].trim())>=0);
		switchToDefaultContent();
	}//end of method
	
	public void clickOnGradesTab(){
	   logMessage("[INFO] Clicking on Grades tab.");
	   click(element("tab_grades"));
		   
	 }//end of method
	
	public void clickOnStudyToolScoreLink(){
		waitForElement(1);
	    logMessage("[INFO] Clicking on Study Tools score link.");
	    clickWithoutScroll(element("link_study_tools_grades"));
		
	}//end of method
	
	public void verifyScoreOfStudyToolsTab(){
		waitForElement(1);
		logMessage("[INFO] Verifying score of pre test - "+element("score_studyTool").getText().split("%")[0]);
		Assert.assertTrue(Double.parseDouble(element("score_studyTool").getText().split("%")[0].trim())>=0);
				
	}//end of method
	
	public void takePostTake(){
		switchToFrame(element("iframe_studyTools_tab"));	
		switchToFrame(element("frame_inner_section"));
		logMessage("[INFO] Clicking on Home link.");
		waitForElement(4);
		clickWithoutScroll(element("btn_studyToolHome"));
		logMessage("[INFO] Clicking on Post Take link.");
		waitForElement(1);
		clickWithoutScroll(element("btn_post_take"));
		attemptASTQuiz(5);
		waitForElement(1);
		logMessage("[INFO] Clicking on End Post-Test button.");
		waitForElement(2);
		clickWithoutScroll(element("ast_pre_end"));
		handleAlert();
		switchToDefaultContent();
		switchToFrame(element("iframe_studyTools_tab"));	
		switchToFrame(element("frame_inner_section"));
		waitForElement(1);
		logMessage("[INFO] Verifying score of post test - "+element("pre_score").getText().split("%")[0]);
		Assert.assertTrue(Integer.parseInt(element("pre_score").getText().split("%")[0].trim())>=0);
		switchToDefaultContent();
	
	}//end of method
	
}//end of class
