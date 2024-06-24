package com.automation.Automation_Biswarupa;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import com.automation.Automation_Biswarupa.Run_Framework;

public class Functions {
	private static String osname;

	public static void startBrowser() {
//		osname = Run_Framework.getosname();
//		String osarch = Run_Framework.getosarch();
//		String path = Run_Framework.getHomeDir() +"/Driver/" + osname +"/" +Run_Framework.getBrowserName()+"/" + osarch;  
		if (Run_Framework.getBrowserName().equalsIgnoreCase("chrome")) {
			if (BrowserConfiguration.isBrowserConfig() == false) {
				BrowserConfiguration.configureBrowser();
			}
			try {
//				if (osname.equalsIgnoreCase("Windows")) {
//					path = path + "/" + "chromedriver.exe";
//				}
//
//				else {
//					path = path + "/" + "chromedriver";
//				}
//
//				System.setProperty("webdriver.chrome.driver", path);
				Run_Framework.driver = new ChromeDriver(BrowserConfiguration.getOptions());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Run_Framework.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			Run_Framework.driver.manage().window().maximize();
		} else if (Run_Framework.getBrowserName().equalsIgnoreCase("firefox")) {
			if (BrowserConfiguration.isBrowserConfig() == false) {
				BrowserConfiguration.configureBrowser();
			}
			Run_Framework.driver = new FirefoxDriver();
			Run_Framework.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			Run_Framework.driver.manage().window().maximize();
		} else if (Run_Framework.getBrowserName().equalsIgnoreCase("edge")) {
			if (BrowserConfiguration.isBrowserConfig() == false) {
				BrowserConfiguration.configureBrowser();
			}
			WebDriverManager.edgedriver().setup();
			Run_Framework.driver = new EdgeDriver();
			Run_Framework.driver.manage().window().maximize();
		} else {
			System.out.println("Browser not found");
		}
	}

	public static void ActionMethod(WebElement webElement) {

		String action = ReadFromDatasheet.getAction().toUpperCase().trim();
		if (webElement != null) {

			switch (action) {
			case "CLICK":
				Click();
				break;
			case "SENDKEYS":
				Sendkeys();
				break;
			case "CHECK_VISIBILITY":
				Check_Visibility();
				break;
			case "GETTEXT":
				Gettext();
				break;

			default:
				break;
			}
		} else {
			switch (action) {
			case "BROWSEURL":
				BrowseUrl();
				break;
			}
		}
	}

	private static void BrowseUrl() {
		Run_Framework.driver.get(ReadFromDatasheet.getDataValue());
	}

	private static void Gettext() {
		// TODO Auto-generated method stub

	}

	private static void Check_Visibility() {
		String expected = ReadFromDatasheet.getDataValue();
		String actual = null;
		if (Validator.getWebElement().isDisplayed()) {
			actual = "Visible";
		}
		SaveResult.Result(expected,actual);

	}

	private static void Sendkeys() {
		// TODO Auto-generated method stub

	}

	private static void Click() {
		Validator.getWebElement().click();
	}

}