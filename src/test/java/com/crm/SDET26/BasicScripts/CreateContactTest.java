package com.crm.SDET26.BasicScripts;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateContactTest {
	
	public static void main(String[] args) throws InterruptedException, IOException {
		
	//commondata
		FileInputStream fis = new FileInputStream("./VtigerLogin.properties");
		Properties pObj = new Properties();
		pObj.load(fis);
		String URL = pObj.getProperty("url");
		String BROWSER = pObj.getProperty("browser");
		String USERNAME = pObj.getProperty("username");
		String PASSWORD = pObj.getProperty("password");
		
	//Testspecificdata
		FileInputStream efis = new FileInputStream("./VtigerTestSpecificData.xlsx");
		
		
		WebDriver driver = null;
	    if(BROWSER.equals("chrome")) {
	    	driver = new ChromeDriver();
	    }else {
	    	driver = new EdgeDriver();
	    	
	    }
		
		driver.get(URL);
		driver.manage().window().maximize();
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//login		
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
	//contacts
		driver.findElement(By.xpath("//a[text() = 'Contacts']")).click();
		driver.findElement(By.xpath("//img[@title = 'Create Contact...']")).click();
		driver.findElement(By.name("lastname")).sendKeys("Chandar");
		driver.findElement(By.xpath("//input[@title = 'Save [Alt+S]']")).click();
		

		//logout
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text() = ' Administrator']")));
					
			WebElement adminimg = driver.findElement(By.xpath("//span[@class='userName']/../following-sibling ::td[1]/img"));
					
			Actions act = new Actions(driver);
			
			act.moveToElement(adminimg).perform();
			
			driver.findElement(By.linkText("Sign Out")).click();

	
	}

}
