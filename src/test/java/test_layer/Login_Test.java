package test_layer;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import base_package.Base_Amazon;
import pom_package.Account_POM;
import pom_package.Login_POM;

public class Login_Test extends Base_Amazon {
	Account_POM acc;
	Login_POM log;
	
	@BeforeTest
	public void start() {
		initialize();
		acc = new Account_POM();
		log = new Login_POM();
	}
	
	@BeforeMethod
	public void open_login_page() {
		acc.click_signin();
	}
	
	@Test(priority=1)
	public void Sign_in_TC1_blank_email_address() {
		log.type_email("");
		log.click_continue();
		boolean actual = log.email_err_display();
		Assert.assertEquals(actual, true);
	}
	
	@Test(priority=2)
	public void Sign_in_TC2_invalid_email_address() {
		log.type_email("jvn.sidhu1989@gmail.com");
		log.click_continue();
		boolean actual = log.email_not_exist_err_display();
		Assert.assertEquals(actual, true);
	}
	
	@Test(priority=3)
	public void Sign_in_TC3_valid_email_address() {
		log.type_email("jvn.sidhu@gmail.com");
		log.click_continue();
		boolean actual = log.pass_display();
		Assert.assertEquals(actual, true);
	}
	
	@Test(priority=4)
	public void Sign_in_TC4_blank_password() {
		log.type_email("jvn.sidhu@gmail.com");
		log.click_continue();
		log.click_submit();
		boolean actual = log.pass_missing_err_display();
		Assert.assertEquals(actual, true);
	}
	
	@Test(priority=5)
	public void Sign_in_TC5_incorrect_password() {
		log.type_email("jvn.sidhu@gmail.com");
		log.click_continue();
		log.type_password("123456");
		log.click_submit();
		boolean actual = log.wrong_pass_err_display();
		Assert.assertEquals(actual, true);
	}
	
	@Test(priority=6)
	public void Sign_in_TC6_correct_password() {
		log.type_email(prop.getProperty("email"));
		log.click_continue();
		log.type_password(prop.getProperty("password"));
		log.click_submit();
		String actual = log.get_acc_text();
		Assert.assertEquals(actual, "Hello, town");
	}
	
	@Test(priority=7)
	public void Sign_in_TC7_keep_me_signed_in_checked() {
		log.type_email(prop.getProperty("email"));
		log.click_continue();
		log.type_password(prop.getProperty("password"));
		log.check_remember_me();
		log.click_submit();
		driver.close();
		driver=new FirefoxDriver();
		driver.get(prop.getProperty("url"));
		Login_POM log = new Login_POM();
		String actual = log.get_acc_text();
		Assert.assertEquals(actual, "Hello, town");
	}
	
	@AfterMethod
	public void open_homepage() {
		driver.get(prop.getProperty("url"));
	}	
	
	@AfterTest
	public void close() {
		driver.close();
	}

}
