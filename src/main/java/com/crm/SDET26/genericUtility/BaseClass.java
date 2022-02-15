package com.crm.SDET26.genericUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.crm.SDET26.pomRepository.HomePage;
import com.crm.SDET26.pomRepository.LoginPage;

public class BaseClass {
	
	public FileUtility fLib = new FileUtility();
	public ExcelUtility eLib = new ExcelUtility();
	public JavaUtility jLib = new JavaUtility();
	public WebDriverUtility wLib = new WebDriverUtility();
	public static WebDriver sDriver;
	
	@BeforeSuite(alwaysRun = true)
	public void dataBaseConnection() {
		System.out.println("Established DB Connection");
	}
	
	public	WebDriver driver = null;
	//@Parameters("browser")
	@BeforeClass(alwaysRun = true)
		//public void browserLaunch(String browser) throws Throwable {
		public void browserLaunch() throws Throwable {
			
		String browser = fLib.getPropertyKeyValue("browser");

		if(browser.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", "./src/main/resources/chromedriver.exe");

			driver = new ChromeDriver();
		}else if(browser.equals("edge")) {
			System.setProperty("webdriver.edge.driver", "./src/main/resources/msedgedriver.exe");
			driver = new EdgeDriver();			
		}else if(browser.equals("ie")) {
			System.setProperty("webdriver.ie.driver", "");
			
			driver = new InternetExplorerDriver();
		}else {
			driver = new ChromeDriver();
		}
		
	}
	
	@BeforeMethod(alwaysRun = true)
	public void login() throws Throwable {
		String USERNAME = fLib.getPropertyKeyValue("username");
		String PASSWORD = fLib.getPropertyKeyValue("password");
		String URL = fLib.getPropertyKeyValue("url");
		LoginPage lp = new LoginPage(driver);
		lp.loginToApp(driver, URL, USERNAME, PASSWORD);
		sDriver = driver;
	}
	
	@AfterMethod(alwaysRun = true)
	public void logout() {
		HomePage hp = new HomePage(driver);
		hp.logout();
	}
	@AfterClass(alwaysRun = true)
	public void closeBrowser(){
		driver.quit();
		
	}
	
	@AfterSuite(alwaysRun = true)
	public void disconnectDB() {
		System.out.println("db disconnected");
	}
	
}
