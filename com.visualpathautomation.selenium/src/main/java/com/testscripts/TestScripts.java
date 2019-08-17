package com.testscripts;

import java.lang.reflect.Method;
import java.net.MalformedURLException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.VisualPath.SignupWithInputDetails;
import com.util.Driver;



public class TestScripts extends Driver {

	public TestScripts(ThreadLocal<WebDriver> driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
		
	@Test(priority =0)
	public static void Signup() throws MalformedURLException, InterruptedException
	{
		SignupWithInputDetails m = new SignupWithInputDetails(driver);
	   
		
		
	}
}
