package scripts;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeClass;
import org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;

public class ValidateLockedOutUser {
	WebDriver driver;
	public static final String URL = "https://www.saucedemo.com/";
	public static final String loginUserName = "locked_out_user";
	public static final String loginPassword = "secret_sauce";
	public static final String expectedErrorMessage = "Epic sadface: Sorry, this user has been locked out.";
	Login_page login_page;
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", "resources\\chromedriver.exe");

		// Instantiate a ChromeDriver class.
		ChromeOptions options = new ChromeOptions();

		options.addArguments("--remote-allow-origins=*");

		driver = new ChromeDriver(options);
		// Maximize the browser
		driver.manage().window().maximize();
	}

	@BeforeMethod
	public void beforeMethod() {
		driver.get(URL);
	}

	@Test
	public void validateLockedOutUser() throws InterruptedException {
		
		login_page = new Login_page(driver);
        //login to SwagLabs
		login_page.checkLabelAndButtonDisplay();
		login_page.loginToSwagLabs(loginUserName, loginPassword);
		
		String actualErrorMessage = driver.findElement(By.xpath("//h3[contains(text(),'Epic sadface: Sorry, this user has been locked out')]")).getText();
		System.out.println("actualErrorMessage ==== " + actualErrorMessage);
		Assert.assertEquals(actualErrorMessage, expectedErrorMessage);

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
