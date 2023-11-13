package pagess;
import java.time.Duration;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WishList {
	WebDriver driver;
	// constructor
	public WishList(WebDriver wd) {
		driver = wd;
		PageFactory.initElements(driver, this);
	}
	
	// Repository of Elements - Signup

	@FindBy(xpath = "//a[contains(text(),'Create a List')]")
	WebElement CreateListLink;
	@FindBy(xpath = "//input[@name='list-name']")
	WebElement ListName;
	@FindBy(xpath = "//span[contains(text(),'Create List')]")
	WebElement CreateListBtn;
	@FindBy(xpath = "//span[@id='profile-list-name']")
	WebElement WishListHeading;
	@FindBy(xpath = "//input[@title='Add to Wish List']")
	WebElement AddtoWishList;
	@FindBy(xpath = "//a[contains(text(),'View Your List')]")
	WebElement ViewYourList;

	// Methods
	public void CreateWishList() throws InterruptedException {
		// Click on Create List Link
		CreateListLink.click();
		driver.switchTo().activeElement();
		// Enter List Name
		ListName.clear();
		ListName.sendKeys("List_2");
		// Click on Create List Button
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", CreateListBtn);
		System.out.println("New list heading is:" + WishListHeading.getText());
		Thread.sleep(2000);
	}

	public void AddProducttoWishList() {
		// Click on Add to wish list button
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", AddtoWishList);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOf(AddtoWishList));
		Actions actions = new Actions(driver);
		actions.moveToElement(AddtoWishList).click().build().perform();
		// Switch to modal
		driver.switchTo().activeElement();
		// Click on View Your List Button
		wait.until(ExpectedConditions.visibilityOf(ViewYourList));
		actions.moveToElement(ViewYourList).click().build().perform();
	}
}