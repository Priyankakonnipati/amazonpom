package testCases;

	import org.testng.annotations.Test;
	import baseClass.BaseClass;
	import pagess.LoginPage;
	import pagess.ProductPage;
	import pagess.WishList;

	public class AddProductToWishList extends BaseClass {
		@Test
		public void AddProduct_Wishlist() throws InterruptedException {
			LoginPage Login_Ob = new LoginPage(driver);
			ProductPage ProductPage_Ob = new ProductPage(driver);
			WishList WishList_Ob = new WishList(driver);
			// Hover on Account And List text
			// click on Sign in button
			Login_Ob.ClickSignin();
			// Enter user name and password, click on sign in Button
			Login_Ob.Login("pk.konnipati@gmail.com", "12345678");
			// Hover on Account And List text
			// click on your wish list
			Login_Ob.ClickYourWishList();
			// Create Wish List
			WishList_Ob.CreateWishList();
			// Search Product
			ProductPage_Ob.SearchProduct("Mobile");
			// Click on Product Name
			ProductPage_Ob.SelectProduct();
			// Go to Product Detail Page
			Login_Ob.RedirectToProdDetailPage();
			// Add product to wish list
			WishList_Ob.AddProducttoWishList();
		}
	}

