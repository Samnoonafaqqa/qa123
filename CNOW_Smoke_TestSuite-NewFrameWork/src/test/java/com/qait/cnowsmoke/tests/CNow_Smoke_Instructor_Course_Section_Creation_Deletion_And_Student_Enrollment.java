package com.qait.cnowsmoke.tests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qait.automation.TestSessionInitiator;
import com.qait.automation.utils.YamlReader;
import com.qait.cnowsmoke.keywords.CnowCourseCreationPageActions;

public class CNow_Smoke_Instructor_Course_Section_Creation_Deletion_And_Student_Enrollment {

    TestSessionInitiator test;
	
	@BeforeClass
	public void start_test_Session() {
		test = new TestSessionInitiator();
		test.launchApplication(YamlReader.getData("sso_login_url"));
		test.sso_login_page.VerifyUserIsOnSSOLoginPage();
		test.sso_login_page.enterEmailIdPasswordAndClickSubmit(YamlReader.getData("users.instructor.username"),YamlReader.getData("users.instructor.password"));
		test.sso_dashboard.verifyUserIsOnSsoDashboard();
		test.sso_dashboard.selectBookFromDropDown();
		test.sso_dashboard.launchCNowApplication();
		test.cnow_home.VerifyUserIsOnCNowHomePage();
		test.cnow_home.clickOnCourseTab();
				
	}//end of before class

	@Test(priority=4)
	public void Test01_1_createCourseManually(){
	 	test.cnow_course_creation.VerifyUserIsOnCourseTab();
		test.cnow_course_creation.clickOnCreateCourseButton();
		test.cnow_course_creation.verifyUserIsOnChooseProcessPage();
		test.cnow_course_creation.chooseTypeAsManuallyAndContinue();
		test.cnow_course_creation.fillCourseInformationSyllabusAndCreate();
		test.cnow_course_creation.verifyCourseCreation(CnowCourseCreationPageActions.courseNameManually);
	
	}//end of Test
		
	@Test(priority=5)
	public void Test02_createCourseSection(){		
	    test.cnow_course_section.clickOnCreateSectionLink();
		test.cnow_course_section.fillSectionInfo();
		test.cnow_course_section.verifySectionCreation();	 
		
	}//end of test
	
	@Test(priority=7)
	public void Test01_2_createCourseByCopy(){
		test.cnow_home.clickOnCourseTab();
		test.cnow_course_creation.VerifyUserIsOnCourseTab();
		test.cnow_course_creation.clickOnCreateCourseButton();
		test.cnow_course_creation.verifyUserIsOnChooseProcessPage();
		test.cnow_course_creation.chooseTypeAsCopyCourseAndContinue();
		test.cnow_course_creation.selectCreatedCourseFromDropDownAndContinue(CnowCourseCreationPageActions.courseNameManually);
		test.cnow_course_creation.verifyCourseCreation(CnowCourseCreationPageActions.courseNameCopy);	
		
	}//end of Test
	
	@Test(priority=9)
	public void Test01_3_createCourseByImport(){
 		test.cnow_home.clickOnCourseTab();
 	    test.cnow_course_creation.VerifyUserIsOnCourseTab();
		test.cnow_course_creation.clickOnCreateCourseButton();
		test.cnow_course_creation.verifyUserIsOnChooseProcessPage();
		test.cnow_course_creation.chooseTypeAsImportCourseAndContinue();
		test.cnow_course_creation.browseExportedCourseAndContinue("CNowCourse15Jun_131920141015.ecx");		
		test.cnow_course_creation.fillCourseInformationSyllabusAndCreate();
		test.cnow_course_creation.verifyCourseCreation(CnowCourseCreationPageActions.courseNameManually);
		
	}//end of Test
	
	@Test(priority=10)
	public void Test03_1_CreateCourseByImporitngLegacyCourseHavingSectionsOnly(){
		test.cnow_home.clickOnCourseTab();
 	  	test.cnow_course_creation.clickOnCreateCourseButton();
		test.cnow_course_creation.chooseTypeAsImportCourseAndContinue();
		test.cnow_course_creation.browseExportedCourseAndContinue("Course_with_section2015710.ecx");		
		test.cnow_course_creation.fillCourseInformationSyllabusAndCreate();
		test.cnow_course_creation.verifyCourseCreation(CnowCourseCreationPageActions.courseNameManually);
	  	test.cnow_course_creation.verifySectionsInCourse(); 
		
	}
	
	@Test(priority=12)
	public void Test03_2_CreateCourseByImporitngLegacyCourseHavingSectionsAndAssignment(){
		test.cnow_home.clickOnCourseTab();
 	  	test.cnow_course_creation.clickOnCreateCourseButton();
		test.cnow_course_creation.chooseTypeAsImportCourseAndContinue();
		test.cnow_course_creation.browseExportedCourseAndContinue("Course_with_assignment_section2015710.ecx");		
		test.cnow_course_creation.fillCourseInformationSyllabusAndCreate();
		test.cnow_course_creation.verifyCourseCreation(CnowCourseCreationPageActions.courseNameManually);
		test.cnow_course_creation.verifySectionsInCourse(); 
		test.cnow_assignment_creation.clickOnAssignmentsTab();
		test.cnow_course_creation.verifyAssignmentInCourse(); 
		
	}
	
	@Test(priority=16)
	public void Test_DeleteAllCreatedCourses(){
		test.cnow_home.clickOnCourseTab();                   
		test.cnow_course_creation.VerifyUserIsOnCourseTab();
		test.cnow_course_deletion.selectAllCourses();
		test.cnow_course_deletion.deleteAllCourse();
	
	}//end of Test
		
	@Test(priority=18)
	public void Test04_createCourseAndRegisterToStudent(){
		test.cnow_home.clickOnCourseTab();
		test.cnow_course_creation.VerifyUserIsOnCourseTab();
		test.cnow_course_creation.clickOnCreateCourseButton();
		test.cnow_course_creation.verifyUserIsOnChooseProcessPage();
		test.cnow_course_creation.chooseTypeAsManuallyAndContinue();
		test.cnow_course_creation.fillCourseInformationSyllabusAndCreate();
		test.cnow_course_creation.verifyCourseCreation(CnowCourseCreationPageActions.courseNameManually);
		test.cnow_student_enrollment.loginToStudentSSO();
		test.cnow_student_enrollment.logoutSSODashBoard();
		
	}//end of test
	
		
	@AfterClass
	public void stop_test_session() {
	  test.closeBrowserSession();
	  
	}//end of after class
	
}//end of class
