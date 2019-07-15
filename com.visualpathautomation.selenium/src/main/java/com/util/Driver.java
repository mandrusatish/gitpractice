package com.util;

import java.net.MalformedURLException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;

public class Driver {

	/** Selenium web driver. */
	protected static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
	

	/** Name of the test case. */
	private String testName;

	/** Name of the test defined in the TestNG file. */
	private String testArea;
	
	private String browser;

	/** Firefox Profile being used to modify headers */
	private FirefoxProfile profile;

	
	public Driver(ThreadLocal<WebDriver> driver) {
		this.driver = driver;
		createDriver();
	}
	/*public Driver(String testName, String texstArea)
			throws MalformedURLException {
		// Set variables
		this.testName = testName;
		this.testArea = testArea;
//		this.browser = browser;
		// Create driver
				createDriver();
			
			
	}*/

	private void createDriver() {

		// Set driver with profile

		/*System.setProperty("webdriver.chrome.driver",
				System.getProperty("user.dir")
						+ "\\newchromedriver\\chromedriver.exe");*/
		
		/*System.setProperty("webdriver.chrome.driver",
				System.getProperty("user.dir")
						+ "\\linuxchromedriver\\chromedriver");*/
		
		System.setProperty("webdriver.chrome.driver","/usr/bin/chromedriver");

		// To remove message "You are using an unsupported command-line flag:
		// --ignore-certificate-errors.
		// Stability and security will suffer."
		// Add an argument 'test-type'

		DesiredCapabilities capabilities = DesiredCapabilities.chrome();

		ChromeOptions options = new ChromeOptions();
		options.addArguments("test-type");
		options.addArguments("start-maximized");
		capabilities.setCapability("chrome.binary",
				System.getProperty("user.dir")
						+ "\\newchromedriver\\chromedriver.exe");
		capabilities.setCapability(ChromeOptions.CAPABILITY, options);
		Driver.driver.set(new ChromeDriver(capabilities));		
		
	}

	public WebDriver getDriver() {
		return driver.get();
	}

	public static void setDriver(ThreadLocal<WebDriver> driver) {
		Driver.driver = driver;
	}

	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

	public String getTestArea() {
		return testArea;
	}

	public void setTestArea(String testArea) {
		this.testArea = testArea;
	}

	public FirefoxProfile getProfile() {
		return profile;
	}

	public void setProfile(FirefoxProfile profile) {
		this.profile = profile;
	}
}
