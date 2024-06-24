package com.automation.Automation_Biswarupa;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Validator {

	private static WebDriverWait wait;
	private static WebElement webElement=null;

	public static void Validate(String action, String locator, String locatorType) {
		if (action.equalsIgnoreCase("startbrowser")) {
			Functions.startBrowser();
		} else {
//			WebElement webElement = CheckObjectVisibility(locator, locatorType,action);
			if(action.equalsIgnoreCase("BrowseURl")) {
			}else {
				webElement = CheckObjectVisibility(locator, locatorType);
			}
		}
		Functions.ActionMethod(webElement);

	}

	public static WebElement getWebElement() {
		return webElement;
	}

	public static void setWebElement(WebElement webElement) {
		Validator.webElement = webElement;
	}

	public static WebElement CheckObjectVisibility(String locator, String locatorType) {
		WebElement webElementValue = null;
		wait = new WebDriverWait(Run_Framework.driver, Duration.ofSeconds(Run_Framework.getMaxExplicitWait()));
		String locatorsType = locatorType.toUpperCase().trim();
		try {
		switch(locatorsType) {

		case "ID":
			System.out.println(locator);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.id(locator)));
			webElementValue = Run_Framework.driver.findElement(By.id(locator));
			break;
		case "XPATH":
			System.out.println(locator);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator)));
			webElementValue = Run_Framework.driver.findElement(By.xpath(locator));
			break;
		case "CLASSNAME":
			System.out.println(locator);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.className(locator)));
			webElementValue = Run_Framework.driver.findElement(By.className(locator));
			break;
		case "NAME":
			System.out.println(locator);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.name(locator)));
			webElementValue = Run_Framework.driver.findElement(By.name(locator));
			break;
		case "CSSSELECTOR":
			System.out.println(locator);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(locator)));
			webElementValue = Run_Framework.driver.findElement(By.cssSelector(locator));
			break;
		case "LINKTEXT":
			System.out.println(locator);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText(locator)));
			webElementValue = Run_Framework.driver.findElement(By.linkText(locator));
			break;
		case "PARTIALLINKTEXT":
			System.out.println(locator);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.partialLinkText(locator)));
			webElementValue = Run_Framework.driver.findElement(By.partialLinkText(locator));
			break;
		case "TAGNAME":
			System.out.println(locator);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName(locator)));
			webElementValue = Run_Framework.driver.findElement(By.tagName(locator));
			break;
		case "FRAMEINDEX":
			int index = Integer.parseInt(locator);
			Run_Framework.driver.switchTo().frame(index);
			break;
		case "FRAMENAME":
			Run_Framework.driver.switchTo()
					.frame((Run_Framework.driver.findElement(By.name(locator))));
			System.out.println("switch to..............................." + locator);
			break;
		case "FRAMEID":
			Run_Framework.driver.switchTo().frame((Run_Framework.driver.findElement(By.id(locator))));
			System.out.println("switch to..............................." + locator);
			break;
		case "FRAMEXPATH":
			Run_Framework.driver.switchTo()
					.frame((Run_Framework.driver.findElement(By.xpath(locator))));
			System.out.println("switch to..............................." + locator);
			break;
		default:
			webElementValue = null;
			break;

		}
	}catch(Exception e) {
		System.out.println("Element not present in the screen");
	}
		return webElementValue;
		}

}