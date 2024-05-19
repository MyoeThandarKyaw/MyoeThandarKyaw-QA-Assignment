package QAAutomationExercise.Scripts;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeClass;
import org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;

public class SortByPrice_HightoLow {
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
	public void sortByPrice_HightoLow() throws InterruptedException {
		
		login_page = new Login_page(driver);
        //login to SwagLabs
		login_page.checkLabelAndButtonDisplay();
		login_page.loginToSwagLabs(loginUserName, loginPassword);
		
		//Check Expected and Actual result
		Assert.assertEquals(login_page.getActualOnlineShopName(), expectedOnlineShopName);
		
		//wait 1 second
		Thread.sleep(1000);
		
		//Choose option to sort
		Select selectOption = new Select(driver.findElement(By.xpath("//select[@class='product_sort_container']")));	
		selectOption.selectByValue("hilo");
		
		//to get first price
		String firstPrice= driver.findElement(By.xpath("(//div[@class='inventory_item_price'])[1]")).getText();
		System.out.println("firstPrice ==== "+firstPrice);
		
		//to get second price
		String secondPrice= driver.findElement(By.xpath("(//div[@class='inventory_item_price'])[2]")).getText();
		System.out.println("secondPrice ==== "+secondPrice);
		
		
		double price1= Double.parseDouble(firstPrice.replaceAll("[$,]", ""));
		System.out.println("price1 ==== "+price1);
		
		double price2= Double.parseDouble(secondPrice.replaceAll("[$,]", ""));
		System.out.println("price2 ==== "+price2);
		Assert.assertTrue(price1>price2);

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
