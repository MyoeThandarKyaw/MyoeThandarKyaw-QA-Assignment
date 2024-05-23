package clickCheckoutButton;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class Home_page {
	public static final String first_Name = "Myoe Thandar";
	public static final String last_Name = "Kyaw";
	public static final String postal_Code = "+95";
	WebDriver driver;

	@FindBy(xpath = "//select[@class='product_sort_container']")
	public WebElement selectOption;

	@FindBy(xpath = "(//div[@class='inventory_item_price'])[1]")
	public WebElement firstItemPrice;

	@FindBy(xpath = "(//div[@class='inventory_item_price'])[2]")
	public WebElement secondItemPrice;

	@FindBy(xpath = "(//div[@class='inventory_item_price'])[3]")
	public WebElement firstPurchaseItemPrice;

	@FindBy(xpath = "(//div[@class='inventory_item_price'])[4]")
	public WebElement secondPurchaseItemPrice;

	@FindBy(xpath = "(//button[text()='Add to cart'])[3]")
	public WebElement addtoCartButtonforFirstItem;

	@FindBy(xpath = "(//button[text()='Add to cart'])[3]")
	public WebElement addtoCartButtonforSecondItem;

	@FindBy(xpath = "//a[@class='shopping_cart_link']")
	public WebElement checkoutIcon;

	@FindBy(id = "checkout")
	public WebElement checkoutButton;

	@FindBy(id = "first-name")
	public WebElement firstName;

	@FindBy(id = "last-name")
	public WebElement lastName;

	@FindBy(id = "postal-code")
	public WebElement postalCode;

	@FindBy(id = "continue")
	public WebElement continueButton;

	@FindBy(className = "summary_subtotal_label")
	public WebElement totalAmountonWebsite;
	
	@FindBy(id = "finish")
	public WebElement finishButton;

	public Home_page(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Choose option to sort High to Low
	public void sortByPriceHightoLow() {
		Select chooseOption = new Select(selectOption);
		chooseOption.selectByValue("hilo");

	}

	// Choose option to sort Low to High
	public void sortByPriceLowtoHigh() {
		Select chooseOption = new Select(selectOption);
		chooseOption.selectByValue("lohi");

	}

	// getFirstItemPrice
	public String getFirstItemPrice() {
		return firstItemPrice.getText();

	}

	// getSecondItemPrice
	public String getSecondItemPrice() {
		return secondItemPrice.getText();

	}

	// getFirstPurchaseItem
	public String getFirstPurchaseItemPrice() {
		return firstPurchaseItemPrice.getText();

	}

	// getSecondPurchaseItem
	public String getSecondPurchaseItemPrice() {
		return secondPurchaseItemPrice.getText();

	}

	// Click Add to cart button for first item
	public void clickAddtoCartButtonforFirstItem() {
		addtoCartButtonforFirstItem.click();

	}

	// Click Add to cart button for first item
	public void clickAddtoCartButtonforSecondItem() {
		addtoCartButtonforSecondItem.click();
	}

	// Click Add to cart button for first item
	public void clickCheckoutIcon() {
		checkoutIcon.click();
	}

	// click on checkout button
	public void clickCheckoutButton() {
		checkoutButton.click();

	}

	// Fill all require information
	public void fillAllRequireInformation() {
		firstName.sendKeys(first_Name);
		lastName.sendKeys(last_Name);
		postalCode.sendKeys(postal_Code);

	}

	// click on continue button
	public void clickContinueButton() {
		continueButton.click();

	}

	//getTotalAmountonWebsite 
	public String getTotalAmountonWebsite() {
		return totalAmountonWebsite.getText();
	}
	
	// click on continue button
		public void clickFinishButton() {
			finishButton.click();

		}

}
