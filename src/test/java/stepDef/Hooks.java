package stepDef;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Parameters;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import utils.Constants;

public class Hooks {
	public static WebDriver driver;
//	@Parameters("browser")
	@Before
	public void setUp(Scenario scenario) {
		setUpDriver("chrome");
		driver.get(Constants.url);
	}
	
	@After
	public void tearDown() {
		driver.quit();
	}
	
	public void setUpDriver(String browser) {
		if (browser.equalsIgnoreCase("chrome")) {
//			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("firefox")) {
//			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if (browser.equalsIgnoreCase("edge")) {
//			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
	}
	
}
