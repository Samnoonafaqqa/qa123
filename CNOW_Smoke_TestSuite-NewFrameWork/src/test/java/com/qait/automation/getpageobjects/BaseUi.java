/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.qait.automation.getpageobjects;

import static com.qait.automation.getpageobjects.ObjectFileReader.getPageTitleFromFile;
import static com.qait.automation.utils.DataReadWrite.getProperty;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import com.qait.automation.utils.SeleniumWait;

/**
 * 
 * @author prashantshukla
 */
public class BaseUi {

	WebDriver driver;
	protected SeleniumWait wait;
	private String pageName;

	protected BaseUi(WebDriver driver, String pageName) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
		this.pageName = pageName;
		this.wait = new SeleniumWait(driver, Integer.parseInt(getProperty(
				"Config.properties", "timeout")));
	}

	protected String getPageTitle() {
		return driver.getTitle();
	}

	protected void logMessage(String message) {
		Reporter.log(message, true);
	}

	protected String getCurrentURL() {
		return driver.getCurrentUrl();
	}

	protected void verifyPageTitleExact() {
		String pageTitle = getPageTitleFromFile(pageName);
		verifyPageTitleExact(pageTitle);
	}

	protected void verifyPageTitleExact(String expectedPagetitle) {
		try {
			wait.waitForPageTitleToBeExact(expectedPagetitle);
		} catch (TimeoutException ex) {
			logMessage("FAILED: Due to page title check on " + pageName);
			Assert.fail("PageTitle for " + pageName + " is not exactly: '"
					+ expectedPagetitle + "'!!! instead it is :- " + driver.getTitle());
		}

		logMessage("PASSED: PageTitle for " + pageName + " is exactly: '"
				+ expectedPagetitle + "'");
	}

	/**
	 * Verification of the page title with the title text provided in the page
	 * object repository
	 */
	protected void verifyPageTitleContains() {
		String expectedPagetitle = getPageTitleFromFile(pageName).trim();
		verifyPageTitleContains(expectedPagetitle);
	}

	/**
	 * this method will get page title of current window and match it partially
	 * with the param provided
	 * 
	 * @param expectedPagetitle
	 *            partial page title text
	 */
	protected void verifyPageTitleContains(String expectedPagetitle) {

		try {
			wait.waitForPageTitleToContain(expectedPagetitle);
		} catch (TimeoutException exp) {
			String actualPageTitle = driver.getTitle().trim();
			logMessage("FAILED: As actual Page Title: '" + actualPageTitle
					+ "' does not contain expected Page Title : '"
					+ expectedPagetitle + "'.");
		}
		String actualPageTitle = getPageTitle().trim();
		logMessage("PASSED: PageTitle for " + actualPageTitle + " contains: '"
				+ expectedPagetitle + "'.");
	}

	protected WebElement getElementByIndex(List<WebElement> elementlist,
			int index) {
		return elementlist.get(index);
	}

	protected WebElement getElementByExactText(List<WebElement> elementlist,
			String elementtext) {
		WebElement element = null;
		for (WebElement elem : elementlist) {
			if (elem.getText().equalsIgnoreCase(elementtext.trim())) {
				element = elem;
			}
		}
		// FIXME: handle if no element with the text is found in list No element
		// exception
		if (element == null) {
		}
		return element;
	}

	protected WebElement getElementByContainsText(List<WebElement> elementlist,
			String elementtext) {
		WebElement element = null;
		for (WebElement elem : elementlist) {
			if (elem.getText().contains(elementtext.trim())) {
				element = elem;
			}
		}
		// FIXME: handle if no element with the text is found in list
		if (element == null) {
		}
		return element;
	}

	protected void switchToFrame(WebElement element) {
		// switchToDefaultContent();
		wait.waitForElementToBeVisible(element);
		driver.switchTo().frame(element);
	}

	public void switchToDefaultContent() {
		driver.switchTo().defaultContent();
	}
	

	public void switchToActiveElement() {
		driver.switchTo().activeElement();
	}
	
	protected void executeJavascript(String script) {
		((JavascriptExecutor) driver).executeScript(script);
	}

	protected void hover(WebElement element) {
		Actions hoverOver = new Actions(driver);
		hoverOver.moveToElement(element).build().perform();
	}

	protected void handleAlert() {
		try {
			switchToAlert().accept();
			logMessage("Alert handled..");
			driver.switchTo().defaultContent();
		} catch (Exception e) {
			System.out.println("No Alert window appeared...");
		}
	}
	public void sendKeys(WebElement element, String str) {
		try {
			wait.waitForElementToBeVisible(element);
			scrollDown(element);
			element.sendKeys(str);
		} catch (StaleElementReferenceException ex1) {
			wait.waitForElementToBeVisible(element);
			scrollDown(element);
			element.sendKeys(str);
			logMessage("Clicked Element " + element	+ " after catching Stale Element Exception");
		} catch (Exception ex2) {
			logMessage("Element " + element + " could not be clicked! "
					+ ex2.getMessage());
		}
	}
	
	private Alert switchToAlert() {
		WebDriverWait wait = new WebDriverWait(driver, 1);
		return wait.until(ExpectedConditions.alertIsPresent());
	}

	protected void selectProvidedTextFromDropDown(WebElement el, String text) {
		wait.waitForElementToBeVisible(el);
		scrollDown(el);
		Select sel = new Select(el);
		sel.selectByVisibleText(text);
	}

	protected void selectProvidedTextFromDropDownWithoutScroll(WebElement el, String text) {
		wait.waitForElementToBeVisible(el);
		Select sel = new Select(el);
		sel.selectByVisibleText(text);
	}	
	
	protected void scrollDown(WebElement element) {
		((JavascriptExecutor) driver).executeScript(
				"arguments[0].scrollIntoView(true);", element);
	}

	protected void hoverClick(WebElement element) {
		Actions hoverClick = new Actions(driver);
		hoverClick.moveToElement(element).click().build().perform();
	}

	public void click(WebElement element) {
		try {
			waitForForeSeeToAppear();
			wait.waitForElementToBeVisible(element);
			scrollDown(element);
			element.click();
		} catch (StaleElementReferenceException ex1) {
			wait.waitForElementToBeVisible(element);
			scrollDown(element);
			element.click();
			logMessage("Clicked Element " + element	+ " after catching Stale Element Exception");
		} catch (Exception ex2) {
			logMessage("Element " + element + " could not be clicked! "
					+ ex2.getMessage());
		}
	}
	
	public void clickWithoutScroll(WebElement element) {
		try {
			waitForForeSeeToAppear();
			wait.waitForElementToBeVisible(element);
			element.click();
		} catch (StaleElementReferenceException ex1) {
			wait.waitForElementToBeVisible(element);
			element.click();
			logMessage("Clicked Element " + element	+ " after catching Stale Element Exception");
		} catch (Exception ex2) {
			logMessage("Element " + element + " could not be clicked! "
					+ ex2.getMessage());
		}
	}
	
	public void clickUsingJS(WebElement element) {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		try{
		executor.executeScript("arguments[0].click();", element);
		
		}catch(StaleElementReferenceException sre){
			System.out.println("Stale Exeception handled.");wait.hardWait(1);
			executor.executeScript("arguments[0].click();", element);
			
		}//end of catch
	}	
	protected String switchUrl(String cnow_original_url,String cnow_switch_url){
			String regex = "http://[a-zA-Z]+\\.com";
			logMessage("Replace Url from :"+cnow_original_url+"to :"+cnow_switch_url);
			String replacement = cnow_original_url.replaceFirst(regex, cnow_switch_url);
			logMessage("Launch replaced url:"+replacement);
			return replacement;
	}
	
   protected void changeWindow(int i) {
        
        Set<String> windows = driver.getWindowHandles();
        if (i > 0) {
            for (int j = 0; j < 9; j++) {
                System.out.println("Windows: " + windows.size());
                if (windows.size() >= 2) {
                    try {
                        Thread.sleep(5000);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    break;
                }
                windows = driver.getWindowHandles();
            }
        }
        String wins[] = windows.toArray(new String[windows.size()]);
        driver.switchTo().window(wins[i]);
        
        System.out.println("Title: " + driver.switchTo().window(wins[i]).getTitle());
    }
	
	
	private void waitForForeSeeToAppear() {
			try {
				driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
				driver.findElement(By.xpath("//div[contains(@class,'PopOverContainer')]/div[6]/div/img")).click();
				logMessage("Survey Overlay was visible.");
				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
				
			} catch (Exception e) {
				//logMessage("Survey Overlay is not visible.");
				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
				
			}//end of catch
	}//end of method
	protected void getRefreshWindow(){
		driver.navigate().refresh();
	}//end of method
	
}