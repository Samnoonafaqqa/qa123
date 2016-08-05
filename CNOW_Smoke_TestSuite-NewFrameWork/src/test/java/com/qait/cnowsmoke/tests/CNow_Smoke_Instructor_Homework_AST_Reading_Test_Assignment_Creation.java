package com.qait.cnowsmoke.tests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qait.automation.TestSessionInitiator;
import com.qait.automation.utils.DataReadWrite;
import com.qait.automation.utils.YamlReader;

public class CNow_Smoke_Instructor_Homework_AST_Reading_Test_Assignment_Creation {

TestSessionInitiator test;
	
	@BeforeClass
	public void start_test_Session() {
		test = new TestSessionInitiator();
			
	}//end of before class

	@Test
	public void Test01_loginToSSODashboard(){
		test.launchApplication(YamlReader.getData("sso_login_url"));
		test.sso_login_page.VerifyUserIsOnSSOLoginPage();
		test.sso_login_page.enterEmailIdPasswordAndClickSubmit(YamlReader.getData("users.instructor.username"),YamlReader.getData("users.instructor.password"));
        
	}//end of Test
		
	@Test(dependsOnMethods="Test01_loginToSSODashboard")
	public void Test02_selectBookFromSSODashBoard(){
		test.sso_dashboard.verifyUserIsOnSsoDashboard();
		test.sso_dashboard.selectBookFromDropDown();
		test.sso_dashboard.launchCNowApplication();
	
	}///end of Test
	
	@Test(dependsOnMethods="Test02_selectBookFromSSODashBoard")
	public void Test03_createHomeworkTypeAssignment(){
		test.cnow_home.VerifyUserIsOnCNowHomePage();
		test.cnow_assignment_creation.clickOnAssignmentsTab();
		test.cnow_student_take.selectCourseFromDown(DataReadWrite.getProperty("studentCourse"));
		test.cnow_assignment_creation.clickOnCreateAssignmentButton();
		test.cnow_assignment_creation.chooseAssignmentTypeAndContinue("homework");
		test.cnow_assignment_creation.enterAssigmentInfoAndContinue("Homework");
	    test.cnow_assignment_creation.selectAChapterAndContinue("Homework");	
	    test.cnow_assignment_creation.includeAllQuestionsAndContinue();
	    test.cnow_assignment_creation.verifyOptionsPageAndDone("homework");
	    test.cnow_assignment_creation.verifyAssignmentCreation("homework");
	   
		test.cnow_assignment_creation.clickOnAssignmentsTab();
		test.cnow_assignment_creation.clickOnCreateAssignmentButton();
		test.cnow_assignment_creation.chooseAssignmentTypeAndContinue("homework");
		test.cnow_assignment_creation.enterAssigmentInfoAndContinue("Resume");
	    test.cnow_assignment_creation.selectAChapterAndContinue("Homework");	
	    test.cnow_assignment_creation.includeAllQuestionsAndContinue();
	    test.cnow_assignment_creation.verifyOptionsPageAndDone("homework");
	    test.cnow_assignment_creation.verifyAssignmentCreation("homework");
	       
	    
	}//end of test
	
	@Test(dependsOnMethods="Test03_createHomeworkTypeAssignment")
	public void Test04_createTestTypeAssignment(){
    	test.cnow_assignment_creation.clickOnAssignmentsTab();
		test.cnow_assignment_creation.clickOnCreateAssignmentButton();
		test.cnow_assignment_creation.chooseAssignmentTypeAndContinue("test");
		test.cnow_assignment_creation.enterAssigmentInfoAndContinue("Test");
		test.cnow_assignment_creation.selectAChapterAndContinue("test");
		test.cnow_assignment_creation.createAndVerifyQuestionPool();
		test.cnow_assignment_creation.verifyOptionsPageAndDone("test");
		test.cnow_assignment_creation.verifyAssignmentCreation("test");
	
	}//end of test
	
	@Test(dependsOnMethods="Test04_createTestTypeAssignment")
	public void Test05_createASTTypeAssignment(){
		test.cnow_assignment_creation.clickOnAssignmentsTab();
		test.cnow_assignment_creation.clickOnCreateAssignmentButton();
		test.cnow_assignment_creation.chooseAssignmentTypeAndContinue("AST");
		test.cnow_assignment_creation.enterAssigmentInfoAndContinue("AST");
		test.cnow_assignment_creation.selectAChapterAndContinue("AST");
		test.cnow_assignment_creation.verifyOptionsPageAndDone("AST");
		test.cnow_assignment_creation.verifyAssignmentCreation("AST");
		
	}//end of Test
	
	@Test(dependsOnMethods="Test05_createASTTypeAssignment")
	public void Test06_createReadingTypeAssignment(){
		test.cnow_assignment_creation.clickOnAssignmentsTab();
		test.cnow_assignment_creation.clickOnCreateAssignmentButton();
		test.cnow_assignment_creation.chooseAssignmentTypeAndContinue("reading");
		test.cnow_assignment_creation.enterAssigmentInfoAndContinue("Reading");
		test.cnow_assignment_creation.selectAndVerifyEbookPageAndDone();
		test.cnow_assignment_creation.verifyAssignmentCreation("Reading");  	

	}//end of Test
	
	@Test(dependsOnMethods="Test06_createReadingTypeAssignment")
	public void Test07_SignOUT(){
        test.cnow_home.instructorLogOUT();
		
	}//end of Test
		
    @AfterClass
	public void stop_test_session() {
	    test.closeBrowserSession();
		
	}//end of after class
		
}//end of class
