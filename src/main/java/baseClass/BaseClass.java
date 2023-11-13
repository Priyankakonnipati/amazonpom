package baseClass;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
        public static WebDriver driver;
        public static Properties Prop = new Properties(); // read file from properties file
        public static FileReader fr;
        @SuppressWarnings("deprecation")
		@BeforeClass
	public void SetUp() throws IOException {
                 if (driver == null) {
			FileReader fr = new FileReader(
			System.getProperty("user.dir") + "\\src\\test\\resources\\configFiles\\Config.Properties");
			Prop.load(fr);
		}
		if (Prop.getProperty("Browser").equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			// driver.get(Prop.getProperty("TestUrl"));
		}
		else if (Prop.getProperty("Browser").equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			// driver.get(Prop.getProperty("TestUrl"));
		}
                else if (Prop.getProperty("Browser").equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			// driver.get(Prop.getProperty("TestUrl"));
		}
		driver.manage().window().maximize();
		driver.get(Prop.getProperty("TestUrl"));
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	@AfterClass
	public void TearDown() {
		driver.close();
	}
}