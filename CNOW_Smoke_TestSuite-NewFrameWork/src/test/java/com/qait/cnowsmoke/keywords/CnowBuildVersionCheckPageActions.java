package com.qait.cnowsmoke.keywords;

import org.openqa.selenium.WebDriver;

import com.qait.automation.getpageobjects.GetPage;
import com.qait.automation.utils.YamlReader;

public class CnowBuildVersionCheckPageActions extends GetPage{
	
	public CnowBuildVersionCheckPageActions(WebDriver driver){
		super(driver,"CnowBuildVersionCheck");
		
	}//end of constructor

	/**
	 * Get and check the CNow build version.
	 * 
	 */
	public void checkCNOWBuildVersion() {
		 logMessage("Checking CNOW Build Version...");
   	     isStringMatching("Version "+YamlReader.getData("cnow_build_version"),element("txt_cnow_build_version").getText());
	
	}//end of method

	
}//end of class 
