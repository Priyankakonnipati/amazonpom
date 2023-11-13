package testCases;
	
	import org.testng.annotations.Test;

	import baseClass.BaseClass;
	import pagess.LoginPage;
	import pagess.ProductPage;

	public class PlaceOrder extends BaseClass {
		@Test
	public void PlaceOrder() throws InterruptedException {
			LoginPage Login_Ob = new LoginPage(driver);
			ProductPage ProductPage_Ob = new ProductPage(driver);
			// Hover on Account And List text
			// click on Sign in button
			Login_Ob.ClickSignin();
			// Enter user name and password, click on sign in Button
			Login_Ob.Login("7793973374", "1234567");
			// click on header cart button
			ProductPage_Ob.OpenHeaderCart();
			// check cart items
			ProductPage_Ob.VerifyCartIetms();
			// Search Product
			ProductPage_Ob.SearchProduct("Mobile");
			// Click on Product Name
			ProductPage_Ob.SelectProduct();
			// Go to Product Detail Page
			Login_Ob.RedirectToProdDetailPage();
			// Place Order
			ProductPage_Ob.PlaceOrder();
		}
	}
