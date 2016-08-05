package com.qait.cnowsmoke.tests;

import static com.qait.automation.utils.YamlReader.getData;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qait.automation.TestSessionInitiator;


public class CNow_Smoke_Build_Version_Check{

	
	TestSessionInitiator test;
	
	@BeforeClass
	public void start_test_Session() {
		test = new TestSessionInitiator();
		
	}//end of before class

	
	@Test
	public void Test1_checkCNOWBuildVersion(){
		test.launchApplication(getData("switch_url"));
		test.vesion_check.checkCNOWBuildVersion();
	    
	}//end of Test
	
	
	@AfterClass
	public void stop_test_session() {
		test.closeBrowserSession();
		
	}//end of after class
	
}//end of  class
