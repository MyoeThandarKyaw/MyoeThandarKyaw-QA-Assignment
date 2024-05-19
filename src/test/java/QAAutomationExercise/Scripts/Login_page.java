package QAAutomationExercise.Scripts;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Login_page {
	WebDriver driver;

	@FindBy(id = "user-name")
	public WebElement userNameInput;

	@FindBy(id = "password")
	public WebElement passwordInput;

	@FindBy(id = "login-button")
	public WebElement login_button;
	
	@FindBy(xpath = "//div[@class='app_logo']")
	public WebElement actualOnlineShopName;

	public Login_page(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// click on login button
	public void onClick() {
		login_button.click();

	}

	public void checkLabelAndButtonDisplay() {
		userNameInput.isDisplayed();
		passwordInput.isDisplayed();
		login_button.isDisplayed();

	}

	public void loginToSwagLabs(String username, String password) throws InterruptedException {

		
		Thread.sleep(1000);

		userNameInput.sendKeys(username);

		passwordInput.sendKeys(password);

		onClick();
	}
	
	//get Login Website
	public String getActualOnlineShopName() {
		return actualOnlineShopName.getText();

	}

}
