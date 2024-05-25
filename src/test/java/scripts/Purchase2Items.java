package scripts;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeClass;
import org.testng.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;

public class Purchase2Items {
	WebDriver driver;
	public static final String URL = "https://www.saucedemo.com/";
	public static final String loginUserName = "standard_user";
	public static final String loginPassword = "secret_sauce";
	public static final String expectedOnlineShopName = "Swag Labs";
	public static final String expectedPrice = "$15.99";
	public static final String expectedMessage = "Thank you for your order!";
	Login_page login_page;
	Home_page home_page;

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
		home_page = new Home_page(driver);
		// login to SwagLabs
		login_page.checkLabelAndButtonDisplay();
		login_page.loginToSwagLabs(loginUserName, loginPassword);

		// Check Expected and Actual result
		Assert.assertEquals(login_page.getActualOnlineShopName(), expectedOnlineShopName);

		// wait 1 second
		Thread.sleep(2000);
		
		
//		String beforesort1 = home_page.getFirstPurchaseItemPrice();
//		System.out.println("firstItemPrice ==== " + beforesort1);
//		String beforesort2 = home_page.getSecondPurchaseItemPrice();
//		System.out.println("firstItemPrice ==== " + beforesort2);
		// Choose option to sort
		home_page.sortByPriceHightoLow();

		// to get first item price
		String firstItemPrice = home_page.getFirstPurchaseItemPrice();
		System.out.println("firstItemPrice ==== " + firstItemPrice);
		Assert.assertEquals(firstItemPrice, expectedPrice);

		// to get second item price
		String secondItemPrice = home_page.getSecondPurchaseItemPrice();
		System.out.println("secondItemPrice ==== " + secondItemPrice);
		Assert.assertEquals(secondItemPrice, expectedPrice);

		double price1 = Double.parseDouble(firstItemPrice.replaceAll("[$,]", ""));
		System.out.println("Price1 ==== " + price1);

		double price2 = Double.parseDouble(secondItemPrice.replaceAll("[$,]", ""));
		System.out.println("Price2 ==== " + price2);

		// Click first Add to Cart button
		home_page.clickAddtoCartButtonforFirstItem();

		// Click first Add to Cart button
		home_page.clickAddtoCartButtonforSecondItem();

		// Click Checkout icon
		home_page.clickCheckoutIcon();

		// Click Checkout button
		home_page.clickCheckoutButton();

		// fill require information
		home_page.fillAllRequireInformation();

		// Click continue button
		home_page.clickContinueButton();

		// get total amount on website
		String totalAmount = home_page.getTotalAmountonWebsite();
		double totalAmountonWebsite = Double.parseDouble(totalAmount.split("(\\$ | \\$\\s*)")[1]);
		System.out.println("TotalAmount on website==== " + totalAmountonWebsite);

		// Sum Price 1 and Price 2
//		String priceTotal = String.valueOf(price1+price2);
//		String actualTotalAmount="$"+priceTotal;
		double actualTotalAmount = price1 + price2;
		System.out.println("Actual Total Amount ==== " + actualTotalAmount);

		// Check total amount is the same as Total amount = Price1+Price2
		Assert.assertEquals(totalAmountonWebsite, actualTotalAmount);

		// Click finish button
		home_page.clickFinishButton();

		// to verify purchase complete
		String actualMessage = home_page.getPurchaseCompleteMessage(); 
		System.out.println("ActualMessage ==== " + actualMessage);
		Assert.assertEquals(actualMessage, expectedMessage);

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
