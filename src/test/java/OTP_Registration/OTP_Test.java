package OTP_Registration;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;


public class OTP_Test {

	public static void main(String[] args) {
		System.setProperty("webdriver.gecko.driver", "geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		driver.get("https://www.amazon.ca");
		driver.findElement(By.xpath("//*[@id=\"nav-signin-tooltip\"]/div/a")).click();
		driver.findElement(By.id("ap_customer_name")).sendKeys("Jeevan Sidhu");
		driver.findElement(By.id("ap_email")).sendKeys("jvn.sidhu89@gmail.com");
		driver.findElement(By.id("ap_password")).sendKeys("jvn@1234");
		driver.findElement(By.id("ap_password_check")).sendKeys("jvn@1234");
		driver.findElement(By.id("continue")).click();
		driver.findElement(By.id("cvf-submit-otp-button")).click();
		
		WebDriverWait wait =new WebDriverWait(driver, Duration.ofSeconds(60));
		wait.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return (d.findElement(By.id("cvf-input-code")).getAttribute ("value").length() >= 6);
            }
		});
            
        driver.findElement(By.id("cvf-submit-otp-button")).click();
	}

}
