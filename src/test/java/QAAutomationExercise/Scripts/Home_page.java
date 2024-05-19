package QAAutomationExercise.Scripts;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class Home_page {
	WebDriver driver;

	@FindBy(xpath = "//select[@class='product_sort_container']")
	public WebElement selectOption;

	@FindBy(xpath = "(//div[@class='inventory_item_price'])[1]")
	public WebElement firstItemPrice;

	@FindBy(xpath = "(//div[@class='inventory_item_price'])[2]")
	public WebElement secondItemPrice;

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

}
