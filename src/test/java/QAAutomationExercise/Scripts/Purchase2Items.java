package QAAutomationExercise.Scripts;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeClass;
import org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;

public class Purchase2Items {
	WebDriver driver;
	public static final String URL = "https://www.saucedemo.com/";
	public static final String loginUserName = "standard_user";
	public static final String loginPassword = "secret_sauce";
	public static final String expectedOnlineShopName = "Swag Labs";
	public static final String expectedPrice = "$15.99";
	public static final String first_Name = "Myoe Thandar";
	public static final String last_Name = "Kyaw";
	public static final String postal_Code = "+95";
	public static final String expectedMessage = "Thank you for your order!";
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
	public void purchase2Items() throws InterruptedException {

		login_page = new Login_page(driver);
        //login to SwagLabs
		login_page.checkLabelAndButtonDisplay();
		login_page.loginToSwagLabs(loginUserName, loginPassword);

		//Check Expected and Actual result
		Assert.assertEquals(login_page.getActualOnlineShopName(), expectedOnlineShopName);

		// wait 1 second
		Thread.sleep(1000);

		// Choose option to sort
		Select selectOption = new Select(driver.findElement(By.xpath("//select[@class='product_sort_container']")));
		selectOption.selectByValue("hilo");

		// to get First Item Price
		String firstItemPrice = driver.findElement(By.xpath("(//div[@class='inventory_item_price'])[3]")).getText();
		System.out.println("FirstItemPrice ==== " + firstItemPrice);
		Assert.assertEquals(firstItemPrice, expectedPrice);

		// to get Second Item Price
		String secondItemPrice = driver.findElement(By.xpath("(//div[@class='inventory_item_price'])[4]")).getText();
		System.out.println("SecondItemPrice ==== " + secondItemPrice);
		Assert.assertEquals(secondItemPrice, expectedPrice);

		double price1 = Double.parseDouble(firstItemPrice.replaceAll("[$,]", ""));
		System.out.println("Price1 ==== " + price1);

		double price2 = Double.parseDouble(secondItemPrice.replaceAll("[$,]", ""));
		System.out.println("Price2 ==== " + price2);
		// Assert.assertTrue(price1>price2);

		// Click first Add to Cart button
		driver.findElement(By.xpath("(//button[text()='Add to cart'])[3]")).click();

		// Click first Add to Cart button
		driver.findElement(By.xpath("(//button[text()='Add to cart'])[3]")).click();

		// Click Checkout icon
		driver.findElement(By.xpath("//a[@class='shopping_cart_link']")).click();

		// Click Checkout button
		driver.findElement(By.id("checkout")).click();

		// fill require information
		WebElement firstName = driver.findElement(By.id("first-name"));
		firstName.sendKeys(first_Name);
		WebElement lastName = driver.findElement(By.id("last-name"));
		lastName.sendKeys(last_Name);
		WebElement postalCode = driver.findElement(By.id("postal-code"));
		postalCode.sendKeys(postal_Code);

		// Click finish button
		driver.findElement(By.id("continue")).click();

		// Check total amount is the same as Total amount = Price1+Price2
		String totalAmount = driver.findElement(By.className("summary_subtotal_label")).getText();
		//String totalAmount = driver.findElement(By.xpath("//div[@class='summary_subtotal_label']")).getText();
		// System.out.println("TotalAmount on website==== " + totalAmount);
		double totalAmountonWebsite = Double.parseDouble(totalAmount.split("(\\$ | \\$\\s*)")[1]);
		System.out.println("TotalAmount on website==== " + totalAmountonWebsite);

		// Sum Price 1 and Price 2
//		String priceTotal = String.valueOf(price1+price2);
//		String actualTotalAmount="$"+priceTotal;
		double actualTotalAmount = price1 + price2;
		System.out.println("Actual Total Amount ==== " + actualTotalAmount);
		Assert.assertEquals(totalAmountonWebsite, actualTotalAmount);

		// Click finish button
		driver.findElement(By.id("finish")).click();

		// to verify purchase complete
		String actualMessage = driver.findElement(By.xpath("//h2[@class='complete-header']")).getText();
		System.out.println("ActualMessage ==== " + actualMessage);
		Assert.assertEquals(actualMessage, expectedMessage);

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
