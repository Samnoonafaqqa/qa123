/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.qait.automation;

import static com.qait.automation.utils.YamlReader.getYamlValue;
import static com.qait.automation.utils.YamlReader.setYamlFilePath;
import static com.qait.automation.utils.DataReadWrite.getProperty;

import com.qait.automation.utils.CustomFunctions;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Reporter;

//import com.qait.cnow.v8.keywords.LoginPageActions;
import com.qait.cnowsmoke.keywords.CnowAssignmentCreationPageActions;
import com.qait.cnowsmoke.keywords.CnowBuildVersionCheckPageActions;
import com.qait.cnowsmoke.keywords.CnowCourseCreationPageActions;
import com.qait.cnowsmoke.keywords.CnowCourseDeletionPageActions;
import com.qait.cnowsmoke.keywords.CnowCourseSectionCreationPageActions;
import com.qait.cnowsmoke.keywords.CnowHomePageActions;
import com.qait.cnowsmoke.keywords.CnowInstructorGradebookPageActions;
import com.qait.cnowsmoke.keywords.CnowStudentAssignmentTakePageActions;
import com.qait.cnowsmoke.keywords.CnowStudentEnrollmentPageActions;
import com.qait.cnowsmoke.keywords.SSOLoginPageActions;
import com.qait.cnowsmoke.keywords.SSOStudentDashboardPageActions;
import com.qait.cnowsmoke.keywords.SSODashboardPageActions;

public class TestSessionInitiator {

	protected WebDriver driver;
    private WebDriverFactory wdfactory;
    String browser;
    String seleniumserver;
    String seleniumserverhost;
    String appbaseurl;
    String applicationpath;
    String chromedriverpath;
    String datafileloc = "";
    static int timeout;
    Map<String, Object> chromeOptions = null;
    DesiredCapabilities capabilities;
    
    public CustomFunctions customFunctions;
    
    /**
     * Initiating the page objects
     */
    
    public SSODashboardPageActions sso_dashboard;
	public CnowHomePageActions cnow_home;
	public CnowCourseCreationPageActions cnow_course_creation;
    public SSOLoginPageActions sso_login_page; 	
    public CnowBuildVersionCheckPageActions vesion_check;
    public CnowCourseSectionCreationPageActions cnow_course_section;
    public CnowStudentEnrollmentPageActions cnow_student_enrollment;
    public CnowCourseDeletionPageActions cnow_course_deletion;
    public CnowAssignmentCreationPageActions cnow_assignment_creation;
    public CnowInstructorGradebookPageActions cnow_gradebook;
    public SSOStudentDashboardPageActions student_sso_dashboard;
    public CnowStudentAssignmentTakePageActions cnow_student_take;
    
    private void _initPage() {
    	customFunctions = new CustomFunctions(driver);
    	sso_login_page= new SSOLoginPageActions(driver);
        vesion_check= new CnowBuildVersionCheckPageActions (driver);
        sso_dashboard= new SSODashboardPageActions(driver);
        cnow_home= new CnowHomePageActions(driver); 
        cnow_course_creation= new CnowCourseCreationPageActions(driver);
        cnow_course_section= new CnowCourseSectionCreationPageActions(driver);
        cnow_student_enrollment= new CnowStudentEnrollmentPageActions(driver);
        cnow_course_deletion= new CnowCourseDeletionPageActions(driver);
        cnow_assignment_creation= new CnowAssignmentCreationPageActions(driver);
        cnow_gradebook= new CnowInstructorGradebookPageActions(driver);
        student_sso_dashboard= new SSOStudentDashboardPageActions(driver);
        cnow_student_take= new CnowStudentAssignmentTakePageActions(driver);
        
    }//end of method
    
    /**
     * Page object Initiation done
     */
    public TestSessionInitiator() {
        wdfactory = new WebDriverFactory();
        testInitiator();
    }

    private void testInitiator() {
        setYamlFilePath();
        _configureBrowser();
        _initPage();
    }

    private void _configureBrowser() {
        driver = wdfactory.getDriver(_getSessionConfig());
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Integer.parseInt(_getSessionConfig().get("timeout")), TimeUnit.SECONDS);
    }

    private Map<String, String> _getSessionConfig() {
        String[] configKeys = {"tier", "browser", "seleniumserver", "seleniumserverhost", "timeout" , "driverpath"};
        Map<String, String> config = new HashMap<String, String>();
        for (String string : configKeys) {
            config.put(string, getProperty("./Config.properties", string));
        }
        return config;
    }

    public void launchApplication() {
        launchApplication(getYamlValue("app_url"));
    }

    public void launchApplication(String applicationpath) {
        Reporter.log("The application url is :- " + applicationpath, true);
        Reporter.log("The test browser is :- " + _getSessionConfig().get("browser"), true);
        driver.get(applicationpath);
    }

    public void getURL(String url) {
        driver.manage().deleteAllCookies();
        driver.get(url);
    }

    public void closeBrowserSession() {
        driver.quit();
    }
}