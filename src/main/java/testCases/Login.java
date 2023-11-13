package testCases;
	
	import org.testng.annotations.Test;
	import baseClass.BaseClass;
	import pagess.LoginPage;

	public class Login extends BaseClass {
	        @Test
		public void Login() throws InterruptedException {
	                LoginPage Login_Ob = new LoginPage(driver);
			// Hover on Account And List text
			// click on Sign in button
			Login_Ob.ClickSignin();
			// Enter user name and password, click on sign in Button
	                Login_Ob.Login("7793973374", "1234567");
			// Verify user is logged in on not
			Login_Ob.VerifyUserLogin();
	                // Logout
			Login_Ob.Signout();
		}
}
