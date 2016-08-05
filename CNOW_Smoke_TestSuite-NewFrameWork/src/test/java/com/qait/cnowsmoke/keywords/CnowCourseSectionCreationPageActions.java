package com.qait.cnowsmoke.keywords;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qait.automation.getpageobjects.GetPage;
import com.qait.automation.utils.YamlReader;
import com.qait.cnowsmoke.keywords.CnowCourseCreationPageActions;

public class CnowCourseSectionCreationPageActions extends GetPage{

	String courseID;
	
	public CnowCourseSectionCreationPageActions(WebDriver driver){
		super(driver,"CnowCourseSectionCreationPage");
		
	}//end of constructor
	
	/**
	 * Click on Create Section link of newly created course.
	 */
	public void clickOnCreateSectionLink(){
		logMessage("Clicking on create Section Link...");
     	courseID=CnowCourseCreationPageActions.courseID;
        WebElement link_section= driver.findElement(By.linkText("Create a Section"));
        click(link_section);
         
	}//end of method
	
	/**
	 * Enter Section information 
	 */
    public void fillSectionInfo(){
        logMessage("Entering Section name...");
        wait.waitForElementToBeVisible(element("input_section_name"));
        element("input_section_name").clear();
        click(element("input_section_name"));
        waitForElement(1);
        element("input_section_name").sendKeys(YamlReader.getData("section_name")+" "+courseID);
        
    	logMessage("Clicking on Create Section button...");
        element("btn_create_section").click();
        
    }//end of method
	
    /**
	 * Verify Section Creation  
	 */
	public void verifySectionCreation(){
		logMessage("Verifying section creation...");
		verifyElementTextConatins("txt_section_header","SECTION CREATED: "+YamlReader.getData("section_name")+" "+courseID);
		
	}//end of method 
	
}//end of class
