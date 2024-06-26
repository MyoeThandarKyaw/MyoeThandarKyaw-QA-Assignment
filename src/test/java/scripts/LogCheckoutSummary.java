package scripts;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeClass;
import org.testng.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogCheckoutSummary {
	WebDriver driver;
	public static final String URL = "https://www.saucedemo.com/";
	public static final String loginUserName = "standard_user";
	public static final String loginPassword = "secret_sauce";
	public static final String expectedOnlineShopName = "Swag Labs";
	public static final String expectedPrice = "$15.99";
	public static final String expectedMessage = "Thank you for your order!";
	public static final String first_Name = "Myoe Thandar";
	public static final String last_Name = "Kyaw";
	public static final String postal_Code = "+95";
	public static final Logger logger = LogManager.getLogger();
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
	public void logCheckoutSummary() throws InterruptedException {		
		login_page = new Login_page(driver);
		home_page = new Home_page(driver);
        //login to SwagLabs
		login_page.checkLabelAndButtonDisplay();
		login_page.loginToSwagLabs(loginUserName, loginPassword);
		
		logger.info("loginUserName => " + loginUserName);
		logger.info("loginPassword => " + loginPassword);
		logger.info("login online shop website");
		
		String actualOnlineShopName = login_page.getActualOnlineShopName();
		//Check Expected and Actual result
		Assert.assertEquals(actualOnlineShopName, expectedOnlineShopName);
		logger.info("Online Shop Name => " + actualOnlineShopName);
		
		// wait 1 second
		//Thread.sleep(1000);

		//Choose option to sort
		home_page.sortByPriceHightoLow();
		logger.info("Sorting from High to Low Price");

		//to get First Item Price
		String firstItemPrice = home_page.getFirstPurchaseItemPrice();
		System.out.println("firstItemPrice ==== " + firstItemPrice);
		Assert.assertEquals(firstItemPrice, expectedPrice);
		logger.info("First Item Price => " + firstItemPrice);
		
		
		// to get Second Item Price
		String secondItemPrice = home_page.getSecondPurchaseItemPrice();
		System.out.println("secondItemPrice ==== " + secondItemPrice);
		Assert.assertEquals(secondItemPrice, expectedPrice);
		logger.info("Second Item Price => " + secondItemPrice);
		
		//Change String to Double value and remove $
		double price1 = Double.parseDouble(firstItemPrice.replaceAll("[$,]", ""));
		System.out.println("price1 ==== " + price1);
	
		double price2 = Double.parseDouble(secondItemPrice.replaceAll("[$,]", ""));
		System.out.println("price2 ==== " + price2);
				

		// Click first Add to Cart button
		home_page.clickAddtoCartButtonforFirstItem();
		logger.info("Click Add to cart button for Item 1");

		// Click first Add to Cart button
		home_page.clickAddtoCartButtonforSecondItem();
		logger.info("Click Add to cart button for Item 2");

		// Click Checkout icon
		home_page.clickCheckoutIcon();
		logger.info("Click Checkout icon");
		
		
		// Click Checkout button
		home_page.clickCheckoutButton();
		logger.info("Click Checkout Button");

		// fill require information
		home_page.fillAllRequireInformation();

		logger.info("Buyer Information ");
		logger.info("First Name => " + first_Name);
		logger.info("Last Name => " + last_Name);
		logger.info("PostalCode => " + postal_Code);
		

		// Click Continue button
		home_page.clickContinueButton();
		logger.info("Click Continue Button");
		
		logger.info("Choose Item Information ");
		logger.info("First Item Information => "+ home_page.getFirstItemAllInformation());
		logger.info("Second Item Information => "+home_page.getSecondItemAllInformation());
		logger.info("Payment Information:");
		logger.info(home_page.getPaymentInformation());
		logger.info("Shipping Information:");
		logger.info(home_page.getShippingInformation());
		logger.info("Price Total:");
		logger.info(home_page.getSummarySubTotal());
		logger.info(home_page.getSummaryTax());
		logger.info(home_page.getSummaryTotal());
		
		// Check total amount is the same as Total amount = Price1+Price2
		String totalAmount = home_page.getTotalAmountonWebsite();
		double totalAmountonWebsite = Double.parseDouble(totalAmount.split("(\\$ | \\$\\s*)")[1]);
		System.out.println("TotalAmount on website==== " + totalAmountonWebsite);
		logger.info("TotalAmount on website => "+totalAmountonWebsite);
		
		// Sum Price 1 and Price 2
		double actualTotalAmount = price1 + price2;
		System.out.println("Actual Total Amount ==== " + actualTotalAmount);
		Assert.assertEquals(totalAmountonWebsite, actualTotalAmount);
		logger.info("ActualTotalAmount => "+actualTotalAmount);
		
		Assert.assertEquals(totalAmountonWebsite, actualTotalAmount);

		// Click finish button
		home_page.clickFinishButton();
		logger.info("Click Finish Button");

		// to verify purchase complete
		String actualMessage = home_page.getPurchaseCompleteMessage(); 
		System.out.println("actualMessage ==== " + actualMessage);
		Assert.assertEquals(actualMessage, expectedMessage);
		logger.info("Verify purchase complete => " + actualMessage);

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
