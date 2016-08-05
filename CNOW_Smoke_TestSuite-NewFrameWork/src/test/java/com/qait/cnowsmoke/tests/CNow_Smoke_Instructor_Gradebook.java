package com.qait.cnowsmoke.tests;


import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qait.automation.TestSessionInitiator;
import com.qait.automation.utils.DataReadWrite;
import com.qait.automation.utils.YamlReader;

public class CNow_Smoke_Instructor_Gradebook {
   
	TestSessionInitiator test;
	
	@BeforeClass
	public void start_test_Session() {
		test = new TestSessionInitiator();
		test.launchApplication(YamlReader.getData("sso_login_url"));
		test.sso_login_page.VerifyUserIsOnSSOLoginPage();
		test.sso_login_page.enterEmailIdPasswordAndClickSubmit(YamlReader.getData("users.instructor.username"),YamlReader.getData("users.instructor.password"));
		test.sso_dashboard.selectBookFromDropDown();
		test.sso_dashboard.launchCNowApplication();
				
	}//end of before class

	@Test(priority=1)
	public void Test10_1_VerifyGradesForAST(){
		test.cnow_gradebook.navigateToGradebookTab();
		test.cnow_gradebook.filterStudent(YamlReader.getData("users.student.name"));
		test.cnow_gradebook.verifyGradesFor("AST", DataReadWrite.getProperty("AST"));
		
	}//end of Test
	
	@Test(priority=3)
	public void Test10_2_VerifyGradesForHomework(){
		test.cnow_gradebook.navigateToGradebookTab();
		test.cnow_gradebook.verifyGradesFor("Homework", DataReadWrite.getProperty("Homework"));
		
	}//end of Test
	
	@Test(priority=1)
	public void Test10_3_VerifyGradesForTest(){
		test.cnow_gradebook.navigateToGradebookTab();
		test.cnow_gradebook.verifyGradesFor("Test", DataReadWrite.getProperty("Test"));
			
	}//end of Test
	
	@Test(priority=1)
	public void Test10_4_VerifyGradesForReading(){
		test.cnow_gradebook.navigateToGradebookTab();
		test.cnow_gradebook.verifyGradesFor("Reading","");
		
	}//end of Test
	
	
	@Test(priority=6)
	public void Test11_1_verifyAndDeleteTake(){
		test.cnow_gradebook.navigateToGradebookTab();
		test.cnow_gradebook.clickOnAndVerifyStudentScoreLink();
		test.cnow_gradebook.selectTakeAndDelete();
		test.cnow_gradebook.clickOnReturnButton();
		test.cnow_gradebook.verifyTakeDeletedOnGradebook();
		
	}//end of Test
	
	@Test(priority=7)
	public void Test11_2_verifyIncompleteAssignment(){
		test.cnow_gradebook.navigateToGradebookTab();
		test.cnow_gradebook.verifyInProgressIcon("Resume");
		test.cnow_gradebook.clickOnInprogressIcon();
		test.cnow_gradebook.verifyEGDPage();
				
	}//end of Test
	
	@Test(priority=8)
	public void Test_signOut(){
		test.cnow_home.instructorLogOUT();	
		
	}//end of Test
	
	@AfterClass
	public void stop_test_session() {
		test.closeBrowserSession();
		
    }//end of after class
	

}//END OF CLASS
