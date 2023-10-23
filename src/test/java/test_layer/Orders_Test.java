package test_layer;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import base_package.Base_Amazon;
import pom_package.Account_POM;
import pom_package.Login_POM;
import pom_package.Orders_POM;

public class Orders_Test extends Base_Amazon {
	Login_POM log;
	Account_POM acc;
	Orders_POM ord;
	
	@BeforeTest
	public void start() {
		initialize();
		log = new Login_POM();
		acc = new Account_POM();
		ord = new Orders_POM();
	}
	
	@BeforeClass
	public void login() {
		acc.click_signin();
		log.type_email(prop.getProperty("email"));
		log.click_continue();
		log.type_password(prop.getProperty("password"));
		log.click_submit();
	}
	
	@BeforeMethod
	public void Go_To_Orders() {
		acc.click_account();
		ord.click_orders();
	}	
	
	@Test(priority=1)
	public void Orders_TC1_Click_Your_Orders() {		
		boolean actual = ord.time_filter_display();
		Assert.assertEquals(actual, true);
	}
	
	@Test(priority=2)
	public void Orders_TC2_Click_Buy_Again() {
		ord.click_buy_again();
		boolean actual = ord.buy_again_recommendations_display();
		Assert.assertEquals(actual, true);
	}
	
	@Test(priority=3)
	public void Orders_TC3_Click_Not_Yet_Shipped() {
		ord.click_ny_shipped();
		boolean actual = ord.orders_container_display();
		Assert.assertEquals(actual, true);
	}
	
	@Test(priority=4)
	public void Orders_TC4_Click_Cancelled_Orders() {
		ord.click_cancelled_orders();
		boolean actual = ord.orders_container_display();
		Assert.assertEquals(actual, true);
	}
	
	@AfterClass
	public void logout() {
		acc.click_signout();
	}	
	
	@AfterTest
	public void close() {
		driver.close();
	}

}
