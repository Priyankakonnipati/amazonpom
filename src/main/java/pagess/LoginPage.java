package pagess;

import static org.testng.Assert.assertTrue;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
        WebDriver driver;
	// constructor
	public LoginPage(WebDriver wd) {
		driver = wd;
		PageFactory.initElements(driver, this);
	}

	// Repository of Elements - Signup
	@FindBy(xpath = "//a[@id='nav-link-accountList']")
	WebElement AccountnLists;
	@FindBy(xpath = "//div[@id='nav-flyout-ya-signin']/child::a/child::span")
	WebElement HoverSigninBtn;
	@FindBy(xpath = "//input[@id='ap_email']")
	WebElement EmailorMob;
	@FindBy(xpath = "//input[@id='continue']")
	WebElement ContinueBtn;
	@FindBy(xpath = "//input[@id='ap_password']")
	WebElement Password;
	@FindBy(xpath = "//input[@id='signInSubmit']")
	WebElement LoginSignBtn;
	@FindBy(xpath = "//div[@class='nav-line-1-container']/child::span")
	WebElement LoginUsername;
	@FindBy(xpath = "//span[contains(text(),'Sign Out')]")
	WebElement Signout;
	@FindBy(xpath = "//div[contains(text(),'Your Account')]/following::a[3]/child::span")
	WebElement YourWishList;

	// Methods
	// Sign in
	public void ClickSignin() {
		// Hover on Accounts&List Text
		Actions action = new Actions(driver);
		action.moveToElement(AccountnLists).build().perform();
		// Click on Signin Button
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", HoverSigninBtn);
	}

	// Login
	public void Login(String email, String pass) {
		// Enter email
		EmailorMob.sendKeys(email);
		// Click on Continue
		ContinueBtn.click();
		// Enter Password
		Password.sendKeys(pass);
		// Click on Signin Btn
		LoginSignBtn.click();
	}

	// Verify user is logged in or not
	// if there is a signout option present then user user is logged in
	public void VerifyUserLogin() {
		// Hover on Accounts&ListText
		Actions action = new Actions(driver);
		action.moveToElement(AccountnLists).build().perform();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOf(Signout));
		assertTrue(Signout.isDisplayed());
		System.out.println("User is Logged in");
	}

	// SignOut
	public void Signout() {
		Signout.click();
		if (EmailorMob.isDisplayed()) {
			System.out.println("User is Signout");
		} else {
			System.out.println("User is not Signout");
		}
	}

	// move to product detail page - window handler
	public void RedirectToProdDetailPage() {
		String MainWinhandle = driver.getWindowHandle();
		Set<String> AllWinHandles = driver.getWindowHandles();
		for (String ChildWinHandle : AllWinHandles) {
			if (!MainWinhandle.equalsIgnoreCase(ChildWinHandle)) {
				driver.switchTo().window(ChildWinHandle);
		}}}

	// Sign in
	public void ClickYourWishList() {
		// Hover on Accounts&List Text
		Actions action = new Actions(driver);
		action.moveToElement(AccountnLists).build().perform();
		// Click on YourWishList Menu
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", YourWishList);
	}}