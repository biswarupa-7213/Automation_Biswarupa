package com.automation.Automation_Biswarupa;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserConfiguration {

	private static ChromeOptions options;
	private static boolean browserConfig;

	public static void configureBrowser() {
		System.out.println("Browser");
		if (Run_Framework.getBrowserName().equalsIgnoreCase("Chrome")) {
			options = new ChromeOptions();
			options.addArguments("--disable-notifications");
			options.addArguments("test-type");
			options.addArguments("window-size=1920,1080");
			options.addArguments("allow-running-insecure-content");
			options.addArguments("--disable-extentions");
			options.addArguments("disable-infobars");
			options.addArguments("disable-captcha");
			options.addArguments("--remote-allow-origins=*");
			Map<String, Object> prefs = new HashMap<>();
			prefs.put("profile.default_content_setting_values.media_stream_mic", 1);
			prefs.put("profile.default_content_setting_values.media_stream_camera", 1);
			prefs.put("profile.default_content_setting_values.geolocation", 1);
			prefs.put("profile.default_content_setting_values.notifications", 1);
			prefs.put("profile.content_settings.exceptions.automatic_downloads.*.setting", 1);
			options.setExperimentalOption("prefs", prefs);
			WebDriverManager.chromedriver().setup();

			browserConfig = true;

		} else if (Run_Framework.getBrowserName().equalsIgnoreCase("FireFox")) {
			DesiredCapabilities cap = new DesiredCapabilities();
			cap.setCapability("-marionette", true);
			cap.setCapability("marionettePort", "2828");
			cap.acceptInsecureCerts();
			WebDriverManager.firefoxdriver().setup();
			browserConfig = true;
		} else if (Run_Framework.getBrowserName().equalsIgnoreCase("Edge")) {

		} else {
			System.out.println("No Browser");
		}		
	}

	public static boolean isBrowserConfig() {
		return browserConfig;
	}

	public static void setBrowserConfig(boolean browserConfig) {
		BrowserConfiguration.browserConfig = browserConfig;
	}
	public static ChromeOptions getOptions() {
		return options;
	}

	public static void setOptions(ChromeOptions options) {
		BrowserConfiguration.options = options;
	}

}