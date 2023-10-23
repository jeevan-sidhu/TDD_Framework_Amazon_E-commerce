package test_layer;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import base_package.Base_Amazon;
import pom_package.Account_POM;
import pom_package.Registration_POM;

public class Registration_Test extends Base_Amazon {
	Account_POM acc;
	Registration_POM reg;
	
	@BeforeTest
	public void start() {
		initialize();
		acc = new Account_POM();
		reg = new Registration_POM();
	}
	
	@BeforeMethod
	public void open_registration_page() {
		acc.click_signup();
	}
	
	@Test(priority=1)
	public void Registration_TC1_blank_your_name_field() {
		reg.type_your_name("");
		reg.type_mob_email("");
		boolean actual = reg.name_err_display();
		Assert.assertEquals(actual, true);
	}
	
	@Test(priority=2)
	public void Registration_TC2_valid_your_name_field() {
		reg.type_your_name("Jeevan");
		reg.type_mob_email("");
		boolean actual = reg.name_err_display();
		Assert.assertEquals(actual, false);
	}
	
	@Test(priority=3)
	public void Registration_TC3_blank_mobile_email() {
		reg.type_your_name("Jeevan");
		reg.type_mob_email("");
		reg.type_password("");
		boolean actual = reg.email_err_display();
		Assert.assertEquals(actual, true);
	}
	
	@Test(priority=4)
	public void Registration_TC4_Verify_Mobile_number() {
		reg.type_your_name("Jeevan Sidhu");
		reg.type_mob_email("11");
		reg.type_password("");
		String actual = reg.get_btn_txt();
		Assert.assertEquals(actual, "Verify mobile number");
	}
	
	@Test(priority=5)
	public void Registration_TC5_Verify_email() {
		reg.type_your_name("Jeevan Sidhu");
		reg.type_mob_email("jvn");
		reg.type_password("");
		String actual = reg.get_btn_txt();
		Assert.assertEquals(actual, "Verify email");
	}
	
	@Test(priority=6)
	public void Registration_TC6_blank_password() {
		reg.type_your_name("Jeevan Sidhu");
		reg.type_mob_email("jvn");
		reg.type_password("");
		reg.type_pass_again("");
		boolean actual = reg.pass_err_display();
		Assert.assertEquals(actual, true);
	}
	
	@Test(priority=7)
	public void Registration_TC7_invalid_password_less_than_6() {
		reg.type_your_name("Jeevan Sidhu");
		reg.type_mob_email("jvn");
		reg.type_password("123");
		reg.type_pass_again("");
		boolean actual = reg.pass_invalid_err_display();
		Assert.assertEquals(actual, true);
	}
	
	@Test(priority=8)
	public void Registration_TC8_valid_password_atleast_6() {
		reg.type_your_name("Jeevan Sidhu");
		reg.type_mob_email("jvn");
		reg.type_password("123456");
		reg.type_pass_again("");
		boolean actual = reg.pass_invalid_err_display();
		Assert.assertEquals(actual, false);
	}
	
	@Test(priority=9)
	public void Registration_TC9_invalid_password_again() {
		reg.type_your_name("Jeevan Sidhu");
		reg.type_mob_email("jvn");
		reg.type_password("123456");
		reg.type_pass_again("abc123");
		boolean actual = reg.pass2_err_display();
		Assert.assertEquals(actual, true);
	}
	
	@Test(priority=10)
	public void Registration_TC10_valid_password_again() {
		reg.type_your_name("Jeevan Sidhu");
		reg.type_mob_email("jvn");
		reg.type_password("123456");
		reg.type_pass_again("123456");
		boolean actual = reg.pass2_err_display();
		Assert.assertEquals(actual, false);
	}
	
	@Test(priority=11)
	public void Registration_TC11_existing_customer() {
		reg.type_your_name("Jeevan Sidhu");
		reg.type_mob_email("jvn.sidhu@gmail.com");
		reg.type_password("123456");
		reg.type_pass_again("123456");
		reg.submit();
		String actual = reg.exist_acc_err_txt();
		Assert.assertEquals(actual, "Are you returning customer");
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
