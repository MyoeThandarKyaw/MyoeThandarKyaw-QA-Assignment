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

public class CheckLogoutSuccessful {
	WebDriver driver;
	public static final String URL = "https://www.saucedemo.com/";
	public static final String loginUserName = "standard_user";
	public static final String loginPassword = "secret_sauce";
	public static final String expectedOnlineShopName = "Swag Labs";
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
	public void checkLogoutSuccessful() throws InterruptedException {

		login_page = new Login_page(driver);
		
		//login to SwagLabs
		login_page.checkLabelAndButtonDisplay();
		login_page.loginToSwagLabs(loginUserName, loginPassword);
		
		//Check Expected and Actual result
		Assert.assertEquals(login_page.getActualOnlineShopName(), expectedOnlineShopName);

		// Click Menu Button
		driver.findElement(By.id("react-burger-menu-btn")).click();
		Thread.sleep(2000);
		// Click Logout Button
		driver.findElement(By.xpath("//a[@id='logout_sidebar_link']")).click();

		// verify successful logout
		login_page.checkLabelAndButtonDisplay();

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
