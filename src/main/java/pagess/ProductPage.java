package pagess;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.interactions.Actions;

public class ProductPage {
	
	WebDriver driver;
	// constructor
	public ProductPage(WebDriver wd) {
		driver = wd;
		PageFactory.initElements(driver, this);
	}

	// Repository of Elements - Signup
	@FindBy(xpath = "//input[@id='twotabsearchtextbox']")
	WebElement SearchBox;
	@FindBy(xpath = "//input[@id='nav-search-submit-button']")
	WebElement SearchIcon;
	@FindBy(xpath = "//div[@cel_widget_id='MAIN-SEARCH_RESULTS-4']/following::img[1]")
	WebElement Product;
	@FindBy(xpath = "//span[@id='productTitle']")
	WebElement ProdDetail_Name;
	@FindBy(xpath = "//input[@id='buy-now-button']")
	WebElement BuyNowBtn;
	@FindBy(xpath = "//span[contains(text(),'Cash on Delivery/Pay on Delivery')]")
	WebElement CashOnDelivery;
	@FindBy(xpath = "//span[@class='a-button-text']/child::span[contains(text(),'Use this payment method')]")
	WebElement UseThisPaymentBtn;
	@FindBy(xpath = "//span[contains(text(),'Place your order')]")
	WebElement PlaceYourOrdertBtn;
	@FindBy(xpath = "//input[contains(@value,'Add to Cart')]")
	WebElement AddToCartBtn;
	@FindBy(xpath = "//form[@id='attach-view-cart-button-form']/child::span/child::span/child::span")
	WebElement Modal_CartBtn;
	@FindBy(xpath = "//div[@id='nav-cart-text-container']/child::span[2]")
	WebElement HeaderCart;
	@FindBy(xpath = "//span[@class='a-truncate-cut']")
	WebElement Cart_ProdName;
	@FindBy(xpath = "//p[@class='a-spacing-mini']/child::span")
	WebElement Cart_Price;
	@FindBy(xpath = "//select[@name='quantity']")
	WebElement Cart_Quantity;
	@FindBy(xpath = "//input[@value='Delete']")
	WebElement Cart_Delete;
	@FindBy(xpath = "//div[@class='sc-list-item-removed-msg']/child::div[1]/child::span")
	WebElement Cart_DeleteMsg;
	@FindBy(xpath = "//div[@class='a-row sc-cart-header']/child::div/child::h1[contains(text(),'Your Amazon Cart is empty.')]")
	WebElement EmptyCart_Heading;
	@FindBy(xpath = "//div[@class='a-row sc-cart-header']/child::div/child::h1[contains(text(),'Shopping Cart')]")
	WebElement Cart_Heading;
	@FindBy(xpath = "//span[@class='a-declarative']/child::input[@value='Delete']")
	List<WebElement> Cart_DeleteList;

	// Methods
	// Search Product
	public void SearchProduct(String Prodname) {
		SearchBox.sendKeys(Prodname);
		SearchIcon.click();
	}

	public void SelectProduct() {
		Product.click();
	}

	public void GetProdDetails() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOf(ProdDetail_Name));
		System.out.println("Product name on product detail page is:" + ProdDetail_Name.getText());
	}

	public void PlaceOrder() {
		// Click Buy Now Button
		BuyNowBtn.click();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		// Select Cash on Delivery Payment Method
		js.executeScript("arguments[0].click();", CashOnDelivery);
		js.executeScript("arguments[0].click();", UseThisPaymentBtn);
		Assert.assertTrue(PlaceYourOrdertBtn.isDisplayed(), "Product is on checkout page");
	}

	public void AddtoCart() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		js.executeScript("arguments[0].scrollIntoView(true);", AddToCartBtn);
		wait.until(ExpectedConditions.visibilityOf(AddToCartBtn));
		// js.executeScript("arguments[0].click();", AddToCartBtn);
		Actions actions = new Actions(driver);
		actions.moveToElement(AddToCartBtn).click().build().perform();
		// Switch to modal
		driver.switchTo().activeElement();
		wait.until(ExpectedConditions.visibilityOf(Modal_CartBtn));
		actions.moveToElement(Modal_CartBtn).click().build().perform();
		wait.until(ExpectedConditions.visibilityOf(Cart_ProdName));
		System.out.println("Product name on Cart is:" + Cart_ProdName.getText());
		System.out.println("Product Price on Cart  page is:" + Cart_Price.getText());
	}

	public void AddQuantity() {
		Select sel = new Select(Cart_Quantity);
		sel.selectByVisibleText("2");
	}

	public void DeleteFromCart() {
		driver.navigate().refresh();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOf(Cart_Delete));
		Cart_Delete.click();
		System.out.println(Cart_DeleteMsg.getText());
	}

	public void OpenHeaderCart() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", HeaderCart);
	}

	public void VerifyCartIetms() throws InterruptedException {
		try {
			if (EmptyCart_Heading.isDisplayed()) {
				System.out.println("Cart is Empty");
			}
		} catch (Exception e) {
			if (Cart_Heading.isDisplayed()) {
				System.out.println("Cart is not Empty");
				for (WebElement E : Cart_DeleteList) {
					try {
						E.click();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						driver.navigate().refresh();
						Cart_Delete.click();
					}
					}
				}
			}
		}
	}

