package test_layer;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base_package.Base_Amazon;
import pom_package.Account_POM;
import pom_package.Address_POM;
import pom_package.Login_POM;
import test_data.Data_Provider;

public class Addresses_Test extends Base_Amazon {
	Login_POM log;
	Account_POM acc;
	Address_POM adr;
	
	@BeforeTest
	public void start() {
		initialize();
		log = new Login_POM();
		acc = new Account_POM();
		adr = new Address_POM();		
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
	public void Go_To_Addresses() {
		acc.click_account();
		adr.click_your_addresses();
	}
	
	@Test(priority=1)
	public void Address_TC1_Click_Your_Addresses() {		
		boolean actual = adr.check_add_address();
		Assert.assertEquals(actual, true);
	}
	
	@Test(priority=2)
	public void Address_TC2_Click_Add_Address() {
		adr.click_add_address();
		String actual = adr.get_new_address_label();
		Assert.assertEquals(actual, "Add a new address");
	}
	
	@Test(priority=3)
	public void Address_TC3_Check_Country() {
		adr.click_add_address();
		String actual = adr.get_country();
		Assert.assertEquals(actual, "Canada");
	}
	
	@Test(priority=4)
	public void Address_TC4_Blank_full_name() {
		adr.click_add_address();
		adr.type_full_name("");
		adr.type_phone_num("");
		boolean actual = adr.check_full_name_error();
		Assert.assertEquals(actual, true);
	}
	
	@Test(priority=5)
	public void Address_TC5_Valid_full_name() {
		adr.click_add_address();
		adr.type_full_name("Jeevan");
		adr.type_phone_num("");
		boolean actual = adr.check_full_name_error();
		Assert.assertEquals(actual, false);
	}
	
	@DataProvider()
	public Object[][] getdata(){
		return Data_Provider.read_data("addr_verify_phone");		
	}
	
	@Test(priority=6, dataProvider="getdata")
	public void Address_TC6_TC7_TC8_TC9_Invalid_Phone_Number(String name, String phone, String addr) {
		adr.click_add_address();
		adr.type_full_name(name);
		adr.type_phone_num(phone);
		adr.type_address(addr);
		boolean actual = adr.check_phone_num_error();
		Assert.assertEquals(actual, true);
	}
	
	@Test(priority=7)
	public void Address_TC10_Valid_Phone_Number() {
		adr.click_add_address();
		adr.type_full_name("Jeevan");
		adr.type_phone_num("6476351234");
		adr.type_address("");
		boolean actual = adr.check_phone_num_error();
		Assert.assertEquals(actual, false);
	}
	
	@Test(priority=8)
	public void Address_TC11_Blank_Address() {
		adr.click_add_address();
		adr.type_full_name("Jeevan");
		adr.type_phone_num("6476351234");
		adr.type_address("");
		adr.type_city("");
		boolean actual = adr.check_address_error();
		Assert.assertEquals(actual, true);
	}
	
	@Test(priority=9)
	public void Address_TC12_Valid_Address() {
		adr.click_add_address();
		adr.type_full_name("Jeevan");
		adr.type_phone_num("6476351234");
		adr.type_address("68, Donald Ficht Cres");
		adr.type_city("");
		boolean actual = adr.check_address_error();
		Assert.assertEquals(actual, false);
	}
	
	@Test(priority=10)
	public void Address_TC13_Valid_City_and_Province() {
		adr.click_add_address();
		adr.type_full_name("Jeevan");
		adr.type_phone_num("6476351234");
		adr.type_address("68, Donald Ficht Cres");
		adr.type_city("Brampton");
		adr.select_province(8);
		boolean actual = adr.check_city_error();
		Assert.assertEquals(actual, false);
	}
	
	@Test(priority=11)
	public void Address_TC14_Invalid_City_and_Province() {
		adr.click_add_address();
		adr.type_full_name("Jeevan");
		adr.type_phone_num("6476351234");
		adr.type_address("68, Donald Ficht Cres");
		adr.type_city("Brampton");
		adr.select_province(0);
		boolean actual = adr.check_city_error();
		Assert.assertEquals(actual, true);
	}
	
	@Test(priority=12)
	public void Address_TC15_blank_City() {
		adr.click_add_address();
		adr.type_full_name("Jeevan");
		adr.type_phone_num("6476351234");
		adr.type_address("68, Donald Ficht Cres");
		adr.type_city("");
		adr.select_province(8);
		boolean actual = adr.check_city_error();
		Assert.assertEquals(actual, true);
	}
	
	@Test(priority=13)
	public void Address_TC16_Valid_Postal_Code() {
		adr.click_add_address();
		adr.type_full_name("Jeevan");
		adr.type_phone_num("6476351234");
		adr.type_address("68, Donald Ficht Cres");
		adr.type_city("Brampton");
		adr.select_province(8);
		adr.type_postal_code("L7A0B7");
		adr.click_add_preferences();
		boolean actual = adr.check_postal_code_error();
		Assert.assertEquals(actual, false);
	}
	
	@Test(priority=14)
	public void Address_TC17_Invalid_Postal_Code() {
		adr.click_add_address();
		adr.type_full_name("Jeevan");
		adr.type_phone_num("6476351234");
		adr.type_address("68, Donald Ficht Cres");
		adr.type_city("Brampton");
		adr.select_province(8);
		adr.type_postal_code("ABCDEF");
		adr.click_add_preferences();
		boolean actual = adr.check_postal_code_error();
		Assert.assertEquals(actual, true);
	}
	
	@Test(priority=15)
	public void Address_TC18_blank_Postal_Code() {
		adr.click_add_address();
		adr.type_full_name("Jeevan");
		adr.type_phone_num("6476351234");
		adr.type_address("68, Donald Ficht Cres");
		adr.type_city("Brampton");
		adr.select_province(8);
		adr.type_postal_code("");
		adr.click_add_preferences();
		boolean actual = adr.check_postal_code_error();
		Assert.assertEquals(actual, true);
	}
	
	@Test(priority=16)
	public void Address_TC19_make_default() {
		adr.click_add_address();
		adr.type_full_name("Jeevan");
		adr.type_phone_num("6476351234");
		adr.type_address("68, Donald Ficht Cres");
		adr.type_city("Brampton");
		adr.select_province(8);
		adr.type_postal_code("L7A0B7");
		adr.click_default_chkbox();
		boolean actual = adr.check_default_chkbox();
		Assert.assertEquals(actual, true);
	}
	
	@Test(priority=17)
	public void Address_TC20_TC21_delivery_instructions() {
		adr.click_add_address();
		adr.type_full_name("Jeevan");
		adr.type_phone_num("6476351234");
		adr.type_address("68, Donald Ficht Cres");
		adr.type_city("Brampton");
		adr.select_province(8);
		adr.type_postal_code("L7A0B7");
		adr.click_add_preferences();
		adr.select_property_type(1);
		adr.type_security_code(1, "1234");
		adr.click_weekend_delivery();
		adr.select_weekend_delivery_option(2);
		adr.select_weekend_delivery_option(3);
		String sec_code = adr.get_security_code();
		String wk_del_bttn1_status = adr.check_weekend_delivery_button_status(2);
		String wk_del_bttn2_status = adr.check_weekend_delivery_button_status(3);		
		boolean actual = (sec_code.equals("1234")) && (wk_del_bttn1_status.equals("true")) && (wk_del_bttn2_status.equals("true"));	
		Assert.assertEquals(actual, true);
	}
	
	@Test(priority=18)
	public void Address_TC22_add_address_with_make_default() throws InterruptedException {
		adr.click_add_address();
		adr.type_full_name("Jeevan");
		adr.type_phone_num("6476351234");
		adr.type_address("68, Donald Ficht Cres");
		adr.type_city("Brampton");
		adr.select_province(8);
		adr.type_postal_code("L7A0B7");
		adr.click_default_chkbox();
		adr.click_submit_address();
		adr.select_original_address();
		adr.save_address();
		boolean actual = adr.check_set_as_default();
		adr.delete_address();
		Assert.assertEquals(actual, false);
	}
	
	@Test(priority=19)
	public void Address_TC23_add_address_without_make_default() throws InterruptedException {
		adr.click_add_address();
		adr.type_full_name("Jeevan");
		adr.type_phone_num("6476351234");
		adr.type_address("68, Donald Ficht Cres");
		adr.type_city("Brampton");
		adr.select_province(8);
		adr.type_postal_code("L7A0B7");
		adr.click_submit_address();
		adr.select_original_address();
		adr.save_address();
		boolean actual = adr.check_set_as_default();
		adr.delete_address();
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
