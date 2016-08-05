package com.qait.cnowsmoke.tests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qait.automation.TestSessionInitiator;
import com.qait.automation.utils.DataReadWrite;
import com.qait.automation.utils.DateUtil;
import com.qait.automation.utils.YamlReader;


public class CNow_Smoke_Student_Take_All_Assignments_And_StudyTools{

TestSessionInitiator test;
	
	@BeforeClass
	public void start_test_Session() {
		test = new TestSessionInitiator();
		test.launchApplication(YamlReader.getData("sso_login_url"));
		test.sso_login_page.VerifyUserIsOnSSOLoginPage();
		test.sso_login_page.enterEmailIdPasswordAndClickSubmit(YamlReader.getData("users.student.username"),YamlReader.getData("users.student.password"));
		test.student_sso_dashboard.verifyUserIsOnSsoDashboard();
		test.student_sso_dashboard.navigateToCNOWApp(DataReadWrite.getProperty("studentCourse"));
	
	}//end of before class
	
	@Test(priority=1)
	public void Test08_1_studentTakeHomeworkAssignment(){
		test.cnow_student_take.clickOnAssignmentTab();
		test.cnow_student_take.verifyAssignmentTab();
		test.cnow_student_take.selectCourseFromDown(DataReadWrite.getProperty("studentCourse"));
		test.cnow_student_take.clickOnAssignmentTakeButton("Homework");
		test.cnow_student_take.clickOnAssignmentStartButton();
	    test.cnow_student_take.attemptAssignment("1");
	    test.cnow_student_take.assignmentSaveAndExit();
	    test.cnow_student_take.clickOnResumeLink();
	    test.cnow_student_take.clickOnAssignmentStartButton();
	    test.cnow_student_take.attemptAssignment("2");
	    test.cnow_student_take.verifyUserAttempt();
	    test.cnow_student_take.userAssignmentSubmit();
	    test.cnow_student_take.verifyAssignmentSubmit("Homework");
	    
	    test.cnow_student_take.clickOnAssignmentTab();
		test.cnow_student_take.clickOnAssignmentTakeButton("Resume");
		test.cnow_student_take.clickOnAssignmentStartButton();
	    test.cnow_student_take.attemptAssignment("1");
	    test.cnow_student_take.assignmentSaveAndExit();
	
	}//end of Test
	
	@Test(priority=4)
	public void Test08_2_studentTakeTestAssignment(){
		test.cnow_student_take.clickOnAssignmentTab();
		test.cnow_student_take.clickOnAssignmentTakeButton("Test");
		test.cnow_student_take.clickOnAssignmentStartButton();
	    test.cnow_student_take.attemptTestAssignment("2");
	    test.cnow_student_take.userAssignmentSubmit();
	    test.cnow_student_take.verifyAssignmentSubmit("Test");
	    
	}//end of Test
	
	
	@Test(priority=6)
	public void Test08_3_studentTakeReadingAssignment(){
		test.cnow_student_take.clickOnAssignmentTab();
		test.cnow_student_take.clickOnAssignmentTakeButton("Reading");
		test.cnow_student_take.clickOnAssignmentStartButton();
	    test.cnow_student_take.attemptReadingTypeAssignment();
	    test.cnow_student_take.verifyMindtapReaderPage();
	    test.cnow_student_take.clickOnExitButton();
	 	    
	}//end of Test
	
	@Test(priority=7)
	public void Test08_4_studentTakeASTAssignment(){
		test.cnow_student_take.clickOnAssignmentTab();
		test.cnow_student_take.clickOnAssignmentTakeButton("AST");
		test.cnow_student_take.clickOnAssignmentStartButton();
		test.cnow_student_take.attemptASTAssignment();
		test.cnow_student_take.verifyAssignmentSubmit("AST");
		test.cnow_home.instructorLogOUT();
		test.closeBrowserSession();
			
	}//end of Test
	
	
	@Test(priority=9)
	public void Test13_1_Student_Account_Creation(){
		test = new TestSessionInitiator();
		test.launchApplication(YamlReader.getData("sso_login_url"));
		test.cnow_student_take.clickOnNewStudentUser();
		test.cnow_student_take.ClickOnDontHaveAccLink();
		test.cnow_student_take.enterEmailID("CNowStud"+DateUtil.getTimeInMiliSeconds()+"@swlearning.com");
		test.cnow_student_take.fillInformationFormAndSubmit();
		test.cnow_student_take.findInstitute("Adams State");
		test.cnow_student_take.selectInstitute("001345");
	
	}//end of Test	
	
	@Test(priority=9)
	public void Test13_2_Student_Enrollment(){
		test.cnow_student_take.enterAccessCodeSubmit(DataReadWrite.getProperty("CourseKey"));
	    test.cnow_student_take.clickOnContinueButton(); 
			
	}//end of Test
	
	@Test(priority=10)
	public void Test13_3_NewAccountMessageVerification(){
		 test.cnow_student_take.clickOnCourseOpenButton(DataReadWrite.getProperty("studentCourse"));
		 test.cnow_student_take.TakeToMyCourseButton();
		 test.cnow_student_take.verifyNewAccountMessage(YamlReader.getData("NewAccountMessage"));
		 
		 
	}//end of Test
	
	@Test(priority=12)
	public void Test09_TakeAssignmentFromStudyToolsTab(){
		 test.cnow_student_take.clickOnStudyToolsTab();
		 test.cnow_student_take.clickOnStudyPlanViewButton();
		 test.cnow_student_take.clickOnTakeButton();
		 test.cnow_student_take.attemptASTQuiz(2);
		 test.cnow_student_take.clickOnEndPreTest();
		 test.cnow_student_take.verifyScoreOfPreTest();
		 test.cnow_student_take.takePostTake();
		 test.cnow_student_take.clickOnGradesTab();
		 test.cnow_student_take.clickOnStudyToolScoreLink();
		 test.cnow_student_take.verifyScoreOfStudyToolsTab();
		 test.cnow_home.instructorLogOUT();
	}//end of Test
	
	@AfterClass
	public void afterClass() {
		test.closeBrowserSession();
		
	}//end of after class
	
}//end of class
