 package com.crm.SDET26.organization;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;

public class CreateOrgTest {

	public static void main(String[] args) throws IOException {
		Random ran= new Random();
		int randomnum = ran.nextInt(1000);
		
	//commondata
		FileInputStream fis = new FileInputStream("./data/VtigerLogin.properties");
		Properties pObj = new Properties();
		pObj.load(fis);
		String BROWSER = pObj.getProperty("browser");
		String URL = pObj.getProperty("url");
		String USERNAME = pObj.getProperty("username");
		String PASSWORD = pObj.getProperty("password");
	//Exceldata
		FileInputStream efis = new FileInputStream("./data/VtigerTestSpecificData.xlsx");
		Workbook wb= WorkbookFactory.create(efis);
		Sheet sh1 = wb.getSheet("Organization");
		Row row = sh1.getRow(0);
		String orgname = row.getCell(1).getStringCellValue() + randomnum;
		wb.close();
		
	//Browser
		
		WebDriver driver = null;
		if(BROWSER.equals("chrome")) {
			driver = new ChromeDriver();
		}else if(BROWSER.equals("edge")) {
			driver = new EdgeDriver();
		}else {
			driver = new ChromeDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(URL);
		
	//login 
        driver.findElement(By.name("user_name")).sendKeys(USERNAME);
        driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
        driver.findElement(By.id("submitButton")).click();

	//ORG
        driver.findElement(By.linkText("Organizations")).click();
        driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
        driver.findElement(By.name("accountname")).sendKeys(orgname);
       

        
         driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

    //Verify
        String verifyOrgname = driver.findElement(By.className("dvHeaderText")).getText();
        
        if(verifyOrgname.contains(orgname)) {
        	System.out.println(orgname + " is verified and pass");
        }else {
        	System.out.println(orgname + " is verified and fail");
        }
	
	//close
        Actions act = new Actions(driver);
        act.moveToElement(driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"))).perform();
        driver.findElement(By.linkText("Sign Out")).click();
        driver.close();

		
	}
}
