package com.VisualPath;

/*
 * AGENT BASE PAGE
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

//import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

import com.util.Driver;
//import com.util.SendMail;
//import com.variable.Variables;

public abstract class BasePage {

	/** Driver Reference */
	protected static Driver driver;

	private URL testURL;

	private String transactionAmount;

	/**
	 * Base Page constructor, sets the driver
	 * 
	 * @param driver
	 *            driver created by BaseTest class
	 */
	public BasePage(Driver driver) {
		this.driver = driver;
	}

	/**
	 * Abstract method to locate page specific elements
	 * 
	 * @throws InterruptedException
	 * @throws IOException 
	 */
	public abstract void findPageElements() throws InterruptedException, IOException;

	public WebElement locateElement(By by) {
		try {
			// Get the element by Unique identifier of the element
			WebElement w = driver.getDriver().findElement(by);
			return w;
		} catch (NoSuchElementException e) {
			throw new NoSuchElementException("Element not found : " + by);

		}
	}

	/**
	 * Write the String in the field
	 * 
	 * @param by
	 *            Unique identifier of the element
	 * @param s
	 *            String to be written into the field
	 */
	public void type(By by, String s) {
		// Locate the element
		WebElement w = locateElement(by);
		// Type the given string to the field
		type(w, s);
	}

	/**
	 * Clears the field and then writes in it
	 * 
	 * @param w
	 *            Element that will be written into
	 * @param s
	 *            String to be written into the field
	 */
	public void type(WebElement w, String s) {
		// Clear the field
		w.clear();
		// Write the string in it
		w.sendKeys(s);
	}

	public void doubleClick(WebElement w) {

		Actions action = new Actions(driver.getDriver());

		action.doubleClick(w);
		action.perform();
	}

	public void doubleClick(By by) {

		WebElement w = locateElement(by);

		doubleClick(w);
	}

	public void actionClick(WebElement w) {

		Actions action = new Actions(driver.getDriver());
		action.moveToElement(w, w.getSize().getWidth() - 5, 2).click()
				.perform();
		// action.click(w);
		// action.perform();
	}

	public void actionClick(By by) {

		WebElement w = locateElement(by);

		actionClick(w);

	}
	
	public void actionClickOnGivenCoOrdinates(By by,int x, int y) {

		WebElement w = locateElement(by);
		
		Actions builder = new Actions(driver.getDriver());   
		builder.moveToElement(w, x, y).click().build().perform();
		

	}

	public void slideElement(WebElement w) {

		Actions moveSlider = new Actions(driver.getDriver());

		/*
		 * Action action = moveSlider.dragAndDropBy(w, 30, 0).build();
		 * action.perform();
		 */

		moveSlider.moveToElement(w).clickAndHold().moveByOffset(0, 200)
				.release().perform();
	}

	public void slideElement(By by) {

		WebElement w = locateElement(by);

		slideElement(w);
	}

	/**
	 * Create URL
	 * 
	 * @return
	 * @throws MalformedURLException
	 */
	public URL AgentUrlCreator(String ipaddress, String tenantCode)
			throws MalformedURLException {

		String path = "http://";

		// path = path +
		// "14.140.144.63:7070/mfsportal/jfl/435309110105/agent.do";
		path = path + ipaddress + "/mfsportal/" + tenantCode + "/agent.do";

		// Create URL and set the variable
		this.testURL = new URL(path);

		return this.testURL;
	}
	
	public URL MerchantUrlCreator(String ipaddress, String tenantCode, String merchantid)
			throws MalformedURLException {

		String path = "http://";

		// path = path +
		// "14.140.144.63:7070/mfsportal/jfl/435309110105/agent.do";
		path = path + ipaddress + "/mfsportal/" + tenantCode + "/" + merchantid
				+ "/merchant.do";

		// Create URL and set the variable
		this.testURL = new URL(path);

		return this.testURL;
	}

	public URL AdminUrlCreator(String ipaddress, String tenantCode)
			throws MalformedURLException {

		String path = "http://";

		// path = path +
		// "14.140.144.63:7070/mfsportal/jfl/435309110105/agent.do";
		path = path + ipaddress + "/mfsportal/" + tenantCode + "/login.do";

		// Create URL and set the variable
		this.testURL = new URL(path);

		return this.testURL;
	}

	/**
	 * List the all the Web elements
	 * 
	 * @param by
	 *            identifier of the list element
	 * @return Returns the list of the web elements
	 */
	public List<WebElement> listWithElements(By by) {
		// List of all the elements
		List<WebElement> elementList = driver.getDriver().findElements(by);

		// List of all visible elements
		List<WebElement> visiableElementList = new ArrayList<WebElement>();

		for (int i = 0; i < elementList.size(); i++) {
			elementList = driver.getDriver().findElements(by);
			// if check to verify visibility of the element
			if (driver.getDriver().findElements(by).get(i).isDisplayed()) {
				// Add element to visibleElement list
				visiableElementList.add(elementList.get(i));
			}
		}
		// Returns visiableElementList object
		return visiableElementList;
	}
	
	
	
	
	
	
	

	/**
	 * List the inner text of the all the Web Elements
	 * 
	 * @param by
	 *            identifier of the list element
	 * @return Returns the list of the inner text of the web elements
	 */
	public List<String> listWithElementText(By by) {
		// List of all the elements
		List<WebElement> elementList = driver.getDriver().findElements(by);
		// List of the inner text of the web elements
		List<String> elementTextList = new ArrayList<String>();
		for (int i = 0; i < elementList.size(); i++) {
			// Add the inner text to elementListText list
			elementTextList.add(elementList.get(i).getText());
		}
		// Returns elementTextList Object
		return elementTextList;

	}

	public void waitForElementToDisplay(By by) {

		new WebDriverWait(driver.getDriver(), 30).until(ExpectedConditions
				.visibilityOfElementLocated(by));


	}
	

	public String waitForAnyOneElementToDisplay(List<By> elementList) {
		boolean conditionTrue = true;
		while (conditionTrue == true) {
			for (int i = 0; i < elementList.size(); i++) {
				try {

					new WebDriverWait(driver.getDriver(), 2)
							.until(ExpectedConditions
									.visibilityOfElementLocated((By) elementList
											.get(i)));
					conditionTrue = false;
				} catch (Exception e) {
				}
			}
		}
		return null;
	}

	/*
	 * *
	 * *
	 */
	public void selectGivenDropdownValue(By by, String value) {

		List<WebElement> bropdownValues = listWithElements(by);

		int index = 0;

		for (int i = 0; i < bropdownValues.size(); i++) {
			if (bropdownValues.get(i).getText().trim().equalsIgnoreCase(value)) {
				index = i;
				break;
			}
		}

		bropdownValues.get(index).click();

	}

	public void closeTab(By by, String value) {

		List<WebElement> bropdownValues = listWithElements(by);

		int index = 0;

		for (int i = 0; i < bropdownValues.size(); i++) {
			if (bropdownValues.get(i).getText().trim().equalsIgnoreCase(value)) {
				System.out.println(bropdownValues.get(i).getText());
				index = i;
				break;
			}
		}

		WebElement bropdownValues1 = bropdownValues.get(index);

		System.out.println(bropdownValues.get(index));
		System.out.println(bropdownValues1);
		bropdownValues1.findElement(By.cssSelector("span[title='Close Tab'"));

		// .findElements((By) listWithElements(Variables.test));

	}

	public void clickOnAgentAgentHierarchy(By by, String operationName) {

		List<WebElement> allOperations = listWithElements(by);

		int index = -1;
		// boolean condition = true;
		// while (condition == true) {
		for (int i = 0; i < allOperations.size(); i++) {

			if (allOperations.get(i).getText().equalsIgnoreCase(operationName)) {
				index = i;
				// condition = false;
				break;
			}
		}
		// }
		doubleClick(allOperations.get(index));
	}

	/**
	 * Verify message is on page, locate, check the length and the message
	 * 
	 * @param by
	 *            Unique identifier of the element
	 */
	public void checkMessage(By by, String message) {
		// Check message is contains any ??? symbols or not
		//Assert.assertTrue(locateElement(by).getText().equalsIgnoreCase(message));
	}


	public String generateRandomMobileNumber() {
		boolean useLetters = false;
		boolean useNumbers = true;
		int stringLength = 9;
		String startswith = "9";
		String S = RandomStringUtils.random(stringLength, useLetters,
				useNumbers);
		return startswith + S;
	}

	public String generateRandomString(String name) {
		boolean useLetters = true;
		boolean useNumbers = false;
		int stringLength = 9;
		String startswith = "";

		if (name.equals("First"))
		startswith = "Steven";
		if (name.equals("Last"))
		startswith = "Smith";

		String S = RandomStringUtils.random(stringLength, useLetters,useNumbers);
		return startswith + S;
	}

	public static TreeMap<String, String> getProperties(String file,
			String fieldname) throws IOException {
		final int lhs = 0;
		final int rhs = 1;

		TreeMap<String, String> map = new TreeMap<String, String>();
		BufferedReader bfr = null;
		bfr = new BufferedReader(new FileReader(new File(
				System.getProperty("user.dir") + "\\src\\main\\resources\\"
						+ file)));

		String line;
		while ((line = bfr.readLine()) != null) {
			if (!line.startsWith("#") && !line.isEmpty()) {
				String[] pair = line.trim().split("=");
				if (pair[lhs].contains(fieldname))
					map.put(pair[lhs].trim(), pair[rhs].trim());
			}
		}
		bfr.close();

		return (map);
	}

	
	
	public static void takeSnapShot(String fileWithPath) throws Exception {
		// Convert web driver object to TakeScreenshot
		TakesScreenshot scrShot = ((TakesScreenshot) driver.getDriver());
		// Call getScreenshotAs method to create image file
		File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
		// Move image file to new destination
		File DestFile = new File(System.getProperty("user.dir")+"\\test-output\\html\\screenshots\\"+(fileWithPath + "-" + new Random().nextInt())+".png");
		// Copy file at destination
		FileUtils.copyFile(SrcFile, DestFile);
		reportLogScreenshot(DestFile);

	}
	
	
	protected static void reportLogScreenshot(File file) {
		System.setProperty("org.uncommons.reportng.escape-output", "false");
		String absolute = file.getAbsolutePath();
		 int beginIndex = absolute.indexOf("screenshots");
		 String relative = absolute.substring(beginIndex).replace(".\\","");
		 String screenShot = relative.replace('\\','/');		 
		 Reporter.log("<a href=\"" + screenShot+ "\"><p align=\"left\">Click here for screnshot" + new Date()+ "</p>");
		 
		
	}
	

	public String generateRandomNumber() {
		boolean useLetters = false;
		boolean useNumbers = true;
		int stringLength = 6;
		String startswith = "9";
		String S = RandomStringUtils.random(stringLength, useLetters,
				useNumbers);
		return startswith + S;
	}
	
	public static String getRandomDecimalValue(final Random random,
		    final int lowerBound,
		    final int upperBound,
		    final int decimalPlaces){

		    if(lowerBound < 0 || upperBound <= lowerBound || decimalPlaces < 0){
		        throw new IllegalArgumentException("ERROR !!!!");
		    }

		    final double dbl =
		        ((random == null ? new Random() : random).nextDouble() //
		            * (upperBound - lowerBound))
		            + lowerBound;
		    return String.format("%." + decimalPlaces + "f", dbl);

		}
	
	
	public static String getEmailValues(String fieldname) throws IOException {

		return getProperties("Email.Properites", fieldname).get(fieldname);
	}

	public static String getAgentTestDataValues(String fieldname)
			throws IOException {

		return getProperties("AgentTestData.txt", fieldname).get(fieldname);
	}
	
	public static String getMerchantTestDataValues(String fieldname)
			throws IOException {

		return getProperties("MerchantTestData.txt", fieldname).get(fieldname);
	}
	
	public static String getAdminTestDataValues(String fieldname)
			throws IOException {

		return getProperties("AdminTestData.txt", fieldname).get(fieldname);
	}
	
	public String getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(String transactionAmount) {
		this.transactionAmount = transactionAmount;
	}
	
	/*
	 Subscriber Specific
	 */
	
	public void selectvalues(WebElement by,int value)
	 {
		 Select select = new Select(by);
		 select.selectByIndex(value);
		 
	 }
	
  public URL subscriberUrlCreator(String ipaddress,String tenantCode,String agentid) throws MalformedURLException{
		
		String path = "http://";
		
	//	path = path + "14.140.144.63:7070/mfsportal/jfl/435309110105/agent.do";
	//	path = path + ipaddress+"/mfsportal/"+tenantCode+"/"+agentid+"/agent.do";
		
		//path = path + ipaddress+":8090/mfsportal/"+tenantCode+"/subscriber.do";
		path = path + ipaddress+"/mfsportal/"+tenantCode+"/subscriber.do";

 	  	// Create URL and set the variable
   	this.testURL = new URL(path);
   	
   	return this.testURL;
	}
  
  public static String getSubscriberTestDataValues(String fieldname)
		   throws IOException {

		  return getProperties("Subscribertestdata.txt", fieldname).get(fieldname);
		 }

}
