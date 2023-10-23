package test_layer;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import base_package.Base_Amazon;
import pom_package.Account_POM;
import pom_package.Login_POM;
import pom_package.Search_POM;

public class Guest_Test extends Base_Amazon {
	Login_POM log;
	Account_POM acc;
	Search_POM src;
	
	@BeforeTest
	public void start() {
		initialize();
		log = new Login_POM();
		acc = new Account_POM();
		src = new Search_POM();
	}
	
	@Test(priority=1)
	public void Guest_TC1_Navigate_Products() throws InterruptedException {
		acc.search("T shirts");
		acc.click_search_btn();
		src.click_next_page();
		SoftAssert sf = new SoftAssert();
		sf.assertEquals(src.get_current_page(), "Current page, page 2");
		src.click_previous_page();
		sf.assertEquals(src.get_current_page(), "Current page, page 1");
		sf.assertAll();
	}
	
	@Test(priority=2)
	public void Guest_TC2_Buy_Product() {
		acc.search("T shirts");
		acc.click_search_btn();
		src.click_prod(1);
		src.click_buy_now();
		String actual = src.verify_checkout_page_display();
		Assert.assertEquals(actual, "Checkout (1 item)");
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
