package com.qait.automation.getpageobjects;

import static com.qait.automation.getpageobjects.ObjectFileReader.getELementFromFile;

import java.util.List;
import java.util.concurrent.TimeUnit;

import junit.framework.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.testng.Assert.*;

public class GetPage extends BaseUi {

	protected WebDriver driver;
	String pageName;

	public GetPage(WebDriver driver, String pageName) {
		super(driver, pageName);
		this.driver = driver;
		this.pageName = pageName;
	}

	// TODO: put this in right place, create dedicated class for frame and
	// window handlers
	protected void switchToNestedFrames(String frameNames) {
		switchToDefaultContent();
		String[] frameIdentifiers = frameNames.split(":");
		for (String frameId : frameIdentifiers) {
			wait.waitForFrameToBeAvailableAndSwitchToIt(getLocator(frameId
					.trim()));
		}
	}

	protected WebElement element(String elementToken) {
		return element(elementToken, "");
	}

	protected WebElement element(String elementToken, String replacement) {
		WebElement elem = null;
		try {
		
			try{
			    elem = wait.waitForElementToBeVisible(driver.findElement(getLocator(elementToken, replacement)));
			
			}catch(StaleElementReferenceException sre){
				logMessage("[Error] Handled Stale Reference Exception.");
				elem = wait.waitForElementToBeVisible(driver.findElement(getLocator(elementToken, replacement)));		
				
			}//end of catch
				
		} catch (NoSuchElementException nsex) {
			org.testng.Assert.fail("There is a problem with " + elementToken
					+ " in the " + this.pageName + ".txt file !!!");
		}
		return elem;
	}

	protected WebElement elementWithoutTry(String elementToken, String replacement) {
		WebElement elem = null;
		elem = wait.waitForElementToBeVisible(driver
					.findElement(getLocator(elementToken, replacement)));
		return elem;
	}

	
	protected List<WebElement> elements(String elementToken, String replacement) {
		return wait.waitForElementsToBeVisible(driver.findElements(getLocator(
				elementToken, replacement)));
	}

	protected List<WebElement> elements(String elementToken) {
		return elements(elementToken, "");
	}

	protected void isStringMatching(String actual, String expected) {
		Assert.assertEquals(expected,actual);
		logMessage("ACTUAL STRING : " + actual);
		logMessage("EXPECTED STRING :" + expected);
		logMessage("ACTUAL STRING is as EXPECTED STRING.");
	}
	protected void isStringContians(String actual, String expected) {
		logMessage("ACTUAL STRING : " + actual);
		logMessage("EXPECTED STRING :" + expected);
		Assert.assertTrue(actual.contains(expected));
		logMessage("ACTUAL STRING is as EXPECTED STRING.");
	}
	
	protected boolean isElementDisplayed(String elementName,
			String elementTextReplace) {
		wait.waitForElementToBeVisible(element(elementName, elementTextReplace));
		boolean result = element(elementName, elementTextReplace).isDisplayed();
		assertTrue(result, "Assertion Failed: element '" + elementName
				+ "with text " + elementTextReplace + "' is not displayed.");
		logMessage("Assertion Passed: element " + elementName + " with text "
				+ elementTextReplace + " is displayed.");
		return result;
	}

	protected void verifyElementText(String elementName, String expectedText) {
		wait.waitForElementToBeVisible(element(elementName));
		assertEquals(element(elementName).getText(), expectedText,
				"Assertion Failed: element '" + elementName
						+ "' Text is not as expected: ");
		logMessage("Assertion Passed: element " + elementName
				+ " is visible and Text is " + expectedText);
	}

	protected void verifyElementTextConatins(String elementName,
			String expectedtext) {
		wait.waitForElementToBeVisible(element(elementName));
		assertTrue(element(elementName).getText().contains(expectedtext),
				"Assertion Failed: element" + elementName
						+ "Text is not as expected:");
		logMessage("Assertion Passed: element " + elementName
				+ " is visible and Text is " + expectedtext);

	}

	protected boolean isElementDisplayed(String elementName) {
		wait.waitForElementToBeVisible(element(elementName));
		boolean result = element(elementName).isDisplayed();
		assertTrue(result, "Assertion Failed: element '" + elementName
				+ "' is not displayed.");
		logMessage("Assertion Passed: element " + elementName
				+ " is displayed.");
		return result;
	}

	protected By getLocator(String elementToken) {
		String[] locator = getELementFromFile(this.pageName, elementToken);
		return getLocators(locator[1].trim(), locator[2].trim());
	}

	protected By getLocator(String elementToken, String replacement) {
		String[] locator = getELementFromFile(this.pageName, elementToken);
		locator[2] = locator[2].replaceAll("\\$\\{.+\\}", replacement);
	//	System.out.println("Xpath - "+locator[2]);
		return getLocators(locator[1].trim(), locator[2].trim());
	}

	// TODO rename to distiguish between getlocator and getlocators
	private By getLocators(String locatorType, String locatorValue) {
		switch (Locators.valueOf(locatorType)) {
		case id:
			return By.id(locatorValue);
		case xpath:
			return By.xpath(locatorValue);
		case name:
			return By.name(locatorValue);
		case classname:
			return By.className(locatorValue);
		case css:
			return By.cssSelector(locatorValue);
		case linktext:
			return By.linkText(locatorValue);
		default:
			return By.id(locatorValue);
		}
	}
 
	/***
	 * handle ok button
	 */
	public void handleOkButton() {
		try {
			driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		    WebElement okButton= driver.findElement(By.xpath("//div[@class = 'right-align']/input"));
			okButton.isDisplayed();
			click(okButton);
	       	driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
		} catch (Exception e) {
			driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
			logMessage("Ok Button Not Found");
		}
	}
	
	public void handleViewCoursesButton() {
		try {
			driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
			WebElement viewCourseButton= driver.findElement(By.xpath("//div[@class = 'right-align']/input[2]"));
			viewCourseButton.isDisplayed();
			click(viewCourseButton);
	        driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
		} catch (Exception e) {
			driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
			logMessage("viewCourseButton not found");
			System.out.println("Exception-"+e);
		}
	}
	
	public void waitForElement(int t){
		wait.hardWait(t);
	}
  
}