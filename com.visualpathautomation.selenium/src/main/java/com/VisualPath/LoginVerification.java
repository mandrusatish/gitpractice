package com.VisualPath;

/*import java.io.IOException;
import java.net.MalformedURLException;

import org.openqa.selenium.WebDriver;*/
//import junit.framework.Assert;

import java.io.FileInputStream;
import java.io.IOException;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.util.Driver;

public class LoginVerification extends Driver{

		
	
	static Driver d = new Driver(driver);
    static WebElement Username,Email,Password,ConfirmPassword,SignINSubmit;
    static String title,username,email,password,confirmpassword,homepagetitle,expectedhomepagetitle;
    
	
	public LoginVerification(ThreadLocal<WebDriver> driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		
	}
	
	public static void PageElements() throws BiffException, IOException
	{
		
		
		FileInputStream file = new FileInputStream(System.getProperty("user.dir")
				+ "\\Testdata\\SignUP_TestData.xls");
             Workbook w = Workbook.getWorkbook(file);
             Sheet s = w.getSheet("Loginurl");
             
             String LoginURL = s.getCell(0, 0).getContents();
                      
        d.getDriver().get(LoginURL);
        
        //d.getDriver().get("http://52.15.72.181:8080/vpath/login");
		//Loading the Page Elements//
		 Username = d.getDriver().findElement(By.cssSelector("input[name='username']"));
		 Password = d.getDriver().findElement(By.cssSelector("input[name='password']"));
		 
		 SignINSubmit = d.getDriver().findElement(By.cssSelector("button[type='submit']"));
	}
	

	@Test
	public static void UserLogin() throws BiffException, IOException, InterruptedException
	{
	 PageElements();
		
	 FileInputStream file = new FileInputStream(System.getProperty("user.dir")
				+ "\\Testdata\\SignUP_TestData.xls");
       Workbook w = Workbook.getWorkbook(file);
       Sheet s = w.getSheet("Logindata");
		
	             username = s.getCell(0, 0).getContents();
	             password = s.getCell(1, 0).getContents();
		     
		
				//Passing the Data//
				Username.sendKeys(username);
				Password.sendKeys(password);
				SignINSubmit.click();
				Thread.sleep(3000);
				homepagetitle = d.getDriver().findElement(By.cssSelector("span[class='user-name']")).getText();
				
				System.out.println("title of the home screensssss jhjhkhjhjhj :"+homepagetitle);
				
				if(homepagetitle.equalsIgnoreCase(username))
				{
					System.out.println("User logged in Successfully");
				}
				else
				{
					System.out.println("User Login Failed");
				}
				
				teardown();		
	       }
			
	 public static  void teardown()
	 {
		 d.getDriver().close();
	 }
	
	
}
