 package com.crm.SDET26.pomRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.SDET26.genericUtility.FileUtility;
import com.crm.SDET26.genericUtility.WebDriverUtility;

public class LoginPage extends WebDriverUtility{
	
	WebDriver driver;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name = "user_name")
	private WebElement userNameEdt;   
	
	@FindBy(name = "user_password")
	private WebElement userPasswordEdt;
	
	@FindAll({@FindBy(id="submitButton") , @FindBy(xpath="//input[@id='submitButton']")})
	private WebElement loginBtn;
	
	public WebElement getUserNameEdt() {
		return userNameEdt;
	}
	
	public WebElement getPasswordEdt() {
		return userPasswordEdt;
	}
	
	public WebElement getLoginBtn() {
		return loginBtn;
	}

	/**
	 * To login using predefined username password
	 * @param userName
	 * @param password
	 * @throws Throwable 
	 */
	public void loginToApp() throws Throwable {
		FileUtility fLib = new FileUtility();
		driver.manage().window().maximize();
		waitForPageToLoad(driver);

		driver.get(fLib.getPropertyKeyValue("url"));
		userNameEdt.sendKeys(fLib.getPropertyKeyValue("username"));
		userPasswordEdt.sendKeys(fLib.getPropertyKeyValue("password"));
		loginBtn.click();
	}
	
	
	/**
	 * To login using user defined credentials
	 * @throws Throwable 
	 */
	
	public void loginToApp(WebDriver driver,String url , String username , String password) throws Throwable {
		driver.manage().window().maximize();
		waitForPageToLoad(driver);
		
		driver.get(url);
		userNameEdt.sendKeys(username);
		userPasswordEdt.sendKeys(password);
		loginBtn.click();
		
	}
}
