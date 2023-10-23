package base_package;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Base_Amazon {
	public static WebDriver driver;
	public static WebDriverWait wait;
	public static Properties prop = new Properties();
	
	public Base_Amazon() {
		try {
			FileInputStream file = new FileInputStream("C:\\Users\\jvnsi\\eclipse-workspace\\Amazon_Project\\src\\test\\java\\Env_variables\\config.properties");
			prop.load(file);		
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	public static void initialize() {
		String wb = prop.getProperty("browser");
		if (wb.equalsIgnoreCase("Firefox")){
			System.setProperty("webdriver.gecko.driver", "geckodriver.exe");
			driver = new FirefoxDriver();
		}
		else if (wb.equalsIgnoreCase("Chrome")){
			System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
			driver = new ChromeDriver();
		}
		wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.parseInt(prop.getProperty("implicit_wait"))));
		driver.get(prop.getProperty("url"));
	}
	public static void switch_to_tab(int i) {
		List<String> tabs = new ArrayList<String> (driver.getWindowHandles());
		 driver.switchTo().window(tabs.get(i-1));
	}
}
