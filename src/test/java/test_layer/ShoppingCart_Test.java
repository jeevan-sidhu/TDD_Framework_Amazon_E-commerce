package test_layer;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import base_package.Base_Amazon;
import pom_package.Account_POM;
import pom_package.Cart_POM;
import pom_package.Login_POM;
import pom_package.Search_POM;

public class ShoppingCart_Test extends Base_Amazon {
	Login_POM log;
	Account_POM acc;
	Search_POM src;
	Cart_POM crt;
	
	@BeforeTest
	public void start() {
		initialize();
		log = new Login_POM();
		acc = new Account_POM();
		src = new Search_POM();
		crt = new Cart_POM();
	}
	
	@BeforeClass
	public void login() {
		acc.click_signin();
		log.type_email(prop.getProperty("email"));
		log.click_continue();
		log.type_password(prop.getProperty("password"));
		log.click_submit();
	}
	
	@Test(priority=1)
	public void Shopping_Cart_TC1_Add_item_to_cart_AND_TC7_Click_on_item_in_cart() throws InterruptedException {
		acc.search("T shirts");
		acc.click_search_btn();
		src.click_prod(1);
		String expected = src.get_prod_title();
		System.out.println("Selected: "+expected);
		src.add_to_cart();
		src.go_to_cart();
		Thread.sleep(2000);
		crt.click_item_in_cart(1);
		switch_to_tab(2);
		String actual = src.get_prod_title();
		System.out.println("Found: "+actual);
		driver.close();
		switch_to_tab(1);
		Assert.assertEquals(actual, expected);
	}
	
	@Test(priority=2)
	public void Shopping_Cart_TC2_increase_quantity() throws InterruptedException {
		acc.search("T shirts");
		acc.click_search_btn();
		src.click_prod(1);
		src.add_to_cart();
		src.go_to_cart();
		crt.select_quantity(3);
		String actual = String.format("%.2f", (crt.get_item_price(0))*3);
		Thread.sleep(1000);
		String expected = String.format("%.2f",crt.get_subtotal());
		Assert.assertEquals(actual, expected);
	}
	
	@Test(priority=3)
	public void Shopping_Cart_TC3_add_same_item_multiple_times() {
		acc.search("T shirts");
		acc.click_search_btn();
		src.click_prod(1);
		src.add_to_cart();
		driver.navigate().back();
		src.click_prod();
		src.add_to_cart();
		src.go_to_cart();
		boolean actual = crt.num_of_items_in_cart()==1 && crt.get_qty(0)==2;
		System.out.println("Items: "+crt.num_of_items_in_cart());
		System.out.println("Qty1: "+crt.get_qty(0));
		Assert.assertEquals(actual, true);
	}
	
	@Test(priority=4)
	public void Shopping_Cart_TC4_add_diiferent_type_multiple_times() throws InterruptedException {
		acc.search("T shirts");
		acc.click_search_btn();
		src.click_prod(1);
		src.add_to_cart();
		acc.search("Jackets");
		acc.click_search_btn();
		src.click_prod(1);
		Thread.sleep(2000);
		src.add_to_cart();
		src.go_to_cart();
		boolean actual = crt.num_of_items_in_cart()==2 && crt.get_qty(0)==1 && crt.get_qty(0)==1;
		System.out.println("items: "+crt.num_of_items_in_cart());
		System.out.println("Qty1: "+crt.get_qty(0));
		System.out.println("Qty2: "+crt.get_qty(1));
		Assert.assertEquals(actual, true);
	}
	
	@Test(priority=5)
	public void Shopping_Cart_TC5_remove_some_items() throws InterruptedException {
		acc.search("T shirts");
		acc.click_search_btn();
		src.click_prod(1);
		src.add_to_cart();
		acc.search("Jackets");
		acc.click_search_btn();
		src.click_prod(1);
		Thread.sleep(2000);
		src.add_to_cart();
		src.go_to_cart();
		float prc1 = crt.get_item_price(0);
		float total_before = crt.get_subtotal();
		System.out.println("Subtotal before del: "+total_before);
		System.out.println("Price of item 1: "+prc1);
		crt.del_items_in_cart(1);
		String actual = String.format("%.2f", crt.get_subtotal());
		System.out.println("Subtotal after del: "+actual);
		String expected = String.format("%.2f", total_before-prc1);
		Assert.assertEquals(actual, expected);
	}
	
	@Test(priority=6)
	public void Shopping_Cart_TC6_remove_all_items() throws InterruptedException {
		acc.search("T shirts");
		acc.click_search_btn();
		src.click_prod(1);
		src.add_to_cart();
		acc.search("Jackets");
		acc.click_search_btn();
		src.click_prod(1);
		Thread.sleep(2000);
		src.add_to_cart();
		src.go_to_cart();
		crt.del_all_items_in_cart();
		String actual = String.format("%.2f", crt.get_subtotal());
		Assert.assertEquals(actual, "0.00");
	}
	
	@AfterMethod
	public void open_homepage() throws InterruptedException {
		crt.del_all_items_in_cart();
		driver.get(prop.getProperty("url"));
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
