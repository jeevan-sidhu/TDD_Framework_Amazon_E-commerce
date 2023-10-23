package test_layer;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import base_package.Base_Amazon;
import pom_package.Account_POM;
import pom_package.Login_POM;
import pom_package.Search_POM;

public class Search_Test extends Base_Amazon {
	Login_POM log;
	Account_POM acc;
	Search_POM src;
	List<String> list_1 = new ArrayList<>();
	List<String> list_2 = new ArrayList<>();
	
	@BeforeTest
	public void start() {
		initialize();
		log = new Login_POM();
		acc = new Account_POM();
		src = new Search_POM();
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
	public void Search_TC1_search_suggestions() {
		acc.search("T shirts");
		boolean actual = src.check_suggestion_box_display();
		Assert.assertEquals(actual, true);
	}
	
	@Test(priority=2)
	public void Search_TC2_search_result() {
		acc.search("T shirts");
		acc.click_search_btn();
		String actual = src.check_result_page_display();
		Assert.assertEquals(actual, "Results");
	}
	
	@Test(priority=3)
	public void Search_TC3_Number_of_products() {
		acc.search("T shirts");
		acc.click_search_btn();
		int actual = src.num_of_prod();
		Assert.assertEquals(actual, 60);
	}
	
	@Test(priority=4)
	public void Search_TC4_click_next_page() throws InterruptedException {
		acc.search("T shirts");
		acc.click_search_btn();
		src.click_next_page();
		String actual = src.get_current_page();
		Assert.assertEquals(actual, "Current page, page 2");
	}
	
	@Test(priority=5)
	public void Search_TC5_Pagination() throws InterruptedException {
		acc.search("T shirts");
		acc.click_search_btn();
		list_1 = src.get_product_codes();
		src.click_next_page();
		list_2 = src.get_product_codes();
		int actual = src.check_duplicates(list_1, list_2);
		Assert.assertEquals(actual, 0, "Duplicates found on next page:");
	}
	
	@Test(priority=6)
	public void Search_TC7_Filter_by_Customer_Rating() throws InterruptedException {
		acc.search("T shirts");
		acc.click_search_btn();
		list_1 = src.get_product_codes();
		src.apply_rating_filter();
		list_2 = src.get_product_codes();
		boolean actual = src.check_duplicates(list_1, list_2)<list_1.size();
		Assert.assertEquals(actual, true, "Same products displayed");
	}
	
	@Test(priority=7)
	public void Search_TC8_Filter_by_Online_Saving() throws InterruptedException {
		acc.search("T shirts");
		acc.click_search_btn();
		list_1 = src.get_product_codes();
		src.apply_discount_filter();
		list_2 = src.get_product_codes();
		boolean actual = src.check_duplicates(list_1, list_2)<list_1.size();
		Assert.assertEquals(actual, true, "Same products displayed");
	}
	
	@Test(priority=8)
	public void Search_TC10_Filter_by_Brand() throws InterruptedException {
		acc.search("T shirts");
		acc.click_search_btn();
		src.apply_brand_filter(0);
		boolean actual = src.verify_brand_filter();
		Assert.assertEquals(actual, true, "Results include multiple brands");
	}
	
	@Test(priority=9)
	public void Search_TC11_Filter_by_Price() throws InterruptedException {
		acc.search("T shirts");
		acc.click_search_btn();
		String prc = src.apply_price_filter(0);
		boolean actual = src.verify_price_filter(prc);
		Assert.assertEquals(actual, true, "Prices out of filter range: "+prc);
	}
	
	@Test(priority=10)
	public void Search_TC21_Filter_by_Customer_Rating_and_Online_Saving() throws InterruptedException {
		acc.search("T shirts");
		acc.click_search_btn();
		list_1 = src.get_product_codes();
		src.apply_rating_filter();
		list_2 = src.get_product_codes();
		SoftAssert sf = new SoftAssert();
		boolean actual = src.check_duplicates(list_1, list_2)<list_1.size();
		sf.assertEquals(actual, true, "Customer rating Filter failed");
		list_1=list_2;
		src.apply_discount_filter();
		list_2 = src.get_product_codes();
		actual = src.check_duplicates(list_1, list_2)<list_1.size();
		sf.assertEquals(actual, true, "Discount Filter failed");
		sf.assertAll();;
	}
	
	@Test(priority=11)
	public void Search_TC23_Filter_by_Customer_Rating_and_Brand() throws InterruptedException {
		acc.search("T shirts");
		acc.click_search_btn();
		list_1 = src.get_product_codes();
		src.apply_rating_filter();
		list_2 = src.get_product_codes();
		SoftAssert sf = new SoftAssert();
		boolean actual = src.check_duplicates(list_1, list_2)<list_1.size();
		sf.assertEquals(actual, true, "Customer rating Filter failed");
		src.apply_brand_filter(0);
		actual = src.verify_brand_filter();
		sf.assertEquals(actual, true, "Results include multiple brands");
		sf.assertAll();
	}
	
	@Test(priority=12)
	public void Search_TC24_Filter_by_Customer_Rating_and_Price() throws InterruptedException {
		acc.search("T shirts");
		acc.click_search_btn();
		list_1 = src.get_product_codes();
		src.apply_rating_filter();
		list_2 = src.get_product_codes();
		SoftAssert sf = new SoftAssert();
		boolean actual = src.check_duplicates(list_1, list_2)<list_1.size();
		sf.assertEquals(actual, true, "Customer rating Filter failed");
		String prc =src.apply_price_filter(0);
		actual = src.verify_price_filter(prc);
		sf.assertEquals(actual, true, "Prices out of filter range: "+prc);
		sf.assertAll();
	}
	
	@Test(priority=13)
	public void Search_TC28_Filter_by_Online_Saving_and_Brand() throws InterruptedException {
		acc.search("T shirts");
		acc.click_search_btn();
		list_1 = src.get_product_codes();
		src.apply_discount_filter();
		list_2 = src.get_product_codes();
		SoftAssert sf = new SoftAssert();
		boolean actual = src.check_duplicates(list_1, list_2)<list_1.size();
		sf.assertEquals(actual, true, "Discount Filter failed");
		src.apply_brand_filter(0);
		actual = src.verify_brand_filter();
		sf.assertEquals(actual, true, "Results include multiple brands");
		sf.assertAll();
	}
	
	@Test(priority=14)
	public void Search_TC29_Filter_by_Online_Saving_and_Price() throws InterruptedException {
		acc.search("T shirts");
		acc.click_search_btn();
		list_1 = src.get_product_codes();
		src.apply_discount_filter();
		list_2 = src.get_product_codes();
		SoftAssert sf = new SoftAssert();
		boolean actual = src.check_duplicates(list_1, list_2)<list_1.size();
		sf.assertEquals(actual, true, "Discount Filter failed");
		String prc =src.apply_price_filter(0);
		actual = src.verify_price_filter(prc);
		sf.assertEquals(actual, true, "Prices out of filter range: "+prc);
		sf.assertAll();
	}
	
	@Test(priority=15)
	public void Search_TC36_Filter_by_Brand_and_Price() throws InterruptedException {
		acc.search("T shirts");
		acc.click_search_btn();
		src.apply_brand_filter(0);
		SoftAssert sf = new SoftAssert();
		boolean actual = src.verify_brand_filter();
		sf.assertEquals(actual, true, "Results include multiple brands");
		String prc =src.apply_price_filter(0);
		actual = src.verify_price_filter(prc);
		sf.assertEquals(actual, true, "Prices out of filter range: "+prc);
		sf.assertAll();
	}
	
	@Test(priority=16)
	public void Search_TC42_Apply_all_filters() throws InterruptedException {
		acc.search("T shirts");
		acc.click_search_btn();
		list_1 = src.get_product_codes();
		src.apply_rating_filter();
		list_2 = src.get_product_codes();
		SoftAssert sf = new SoftAssert();
		boolean actual = src.check_duplicates(list_1, list_2)<list_1.size();
		sf.assertEquals(actual, true, "Customer rating Filter failed");
		list_1=list_2;
		src.apply_discount_filter();
		list_2 = src.get_product_codes();
		actual = src.check_duplicates(list_1, list_2)<list_1.size();
		sf.assertEquals(actual, true, "Discount Filter failed");
		src.apply_brand_filter(0);
		actual = src.verify_brand_filter();
		sf.assertEquals(actual, true, "Results include multiple brands");
		String prc = src.apply_price_filter(0);
		actual = src.verify_price_filter(prc);
		sf.assertEquals(actual, true, "Prices out of filter range: "+prc);
		sf.assertAll();
	}
	
	@Test(priority=17)
	public void Search_TC44_Sort_by_price_low_to_high() throws InterruptedException {
		acc.search("T shirts");
		acc.click_search_btn();
		src.sort_by(1);
		boolean actual = src.verify_sort_by_price_low_to_high();
		Assert.assertEquals(actual, true, "Sort by Price: Low to High failed");
	}
	
	@Test(priority=18)
	public void Search_TC45_Sort_by_price_high_to_low() throws InterruptedException {
		acc.search("T shirts");
		acc.click_search_btn();
		src.sort_by(2);
		boolean actual = src.verify_sort_by_price_high_to_low();
		Assert.assertEquals(actual, true, "Sort by Price: High to Low failed");
	}
	
	@Test(priority=19)
	public void Search_TC46_Sort_by_Avg_customer_review() throws InterruptedException {
		acc.search("T shirts");
		acc.click_search_btn();
		src.sort_by(3);
		boolean actual = src.verify_sort_by_customer_reviews();
		Assert.assertEquals(actual, true, "Sort by Avg. Customer Review failed");
	}
	
	@Test(priority=20)
	public void Search_TC57_Sort_by_Price_Low_to_High_and_Filter_by_Customer_Rating() throws InterruptedException {
		acc.search("T shirts");
		acc.click_search_btn();
		src.sort_by(1);
		list_1 = src.get_product_codes();
		src.apply_rating_filter();
		list_2 = src.get_product_codes();
		boolean actual = src.check_duplicates(list_1, list_2)<list_1.size() && src.verify_sort_by_price_low_to_high();
		Assert.assertEquals(actual, true);
	}
	
	@Test(priority=21)
	public void Search_TC58_Sort_by_Price_Low_to_High_and_Filter_by_Online_Saving() throws InterruptedException {
		acc.search("T shirts");
		acc.click_search_btn();
		src.sort_by(1);
		list_1 = src.get_product_codes();
		src.apply_discount_filter();
		list_2 = src.get_product_codes();
		boolean actual = src.check_duplicates(list_1, list_2)<list_1.size() && src.verify_sort_by_price_low_to_high();
		Assert.assertEquals(actual, true);
	}
	
	@Test(priority=22)
	public void Search_TC60_Sort_by_Price_Low_to_High_and_Filter_by_Brand() throws InterruptedException {
		acc.search("T shirts");
		acc.click_search_btn();
		src.sort_by(1);
		src.apply_brand_filter(0);
		boolean actual = src.verify_brand_filter() && src.verify_sort_by_price_low_to_high();
		Assert.assertEquals(actual, true);
	}
	
	@Test(priority=23)
	public void Search_TC61_Sort_by_Price_Low_to_High_and_Filter_by_Price() throws InterruptedException {
		acc.search("T shirts");
		acc.click_search_btn();
		src.sort_by(1);
		String prc = src.apply_price_filter(0);
		boolean actual = src.verify_price_filter(prc) && src.verify_sort_by_price_low_to_high();
		Assert.assertEquals(actual, true);
	}
	
	@Test(priority=24)
	public void Search_TC65_Sort_by_Price_High_to_Low_and_Filter_by_Customer_Rating() throws InterruptedException {
		acc.search("T shirts");
		acc.click_search_btn();
		src.sort_by(2);
		list_1 = src.get_product_codes();
		src.apply_rating_filter();
		list_2 = src.get_product_codes();
		boolean actual = src.check_duplicates(list_1, list_2)<list_1.size() && src.verify_sort_by_price_high_to_low();
		Assert.assertEquals(actual, true);
	}
	
	@Test(priority=25)
	public void Search_TC66_Sort_by_Price_High_to_Low_and_Filter_by_Online_Saving() throws InterruptedException {
		acc.search("T shirts");
		acc.click_search_btn();
		src.sort_by(2);
		list_1 = src.get_product_codes();
		src.apply_discount_filter();
		list_2 = src.get_product_codes();
		boolean actual = src.check_duplicates(list_1, list_2)<list_1.size() && src.verify_sort_by_price_high_to_low();
		Assert.assertEquals(actual, true);
	}
	
	@Test(priority=26)
	public void Search_TC68_Sort_by_Price_High_to_Low_and_Filter_by_Brand() throws InterruptedException {
		acc.search("T shirts");
		acc.click_search_btn();
		src.sort_by(2);
		src.apply_brand_filter(0);
		boolean actual = src.verify_brand_filter() && src.verify_sort_by_price_low_to_high();
		Assert.assertEquals(actual, true);
	}
	
	@Test(priority=27)
	public void Search_TC69_Sort_by_Price_High_to_Low_and_Filter_by_Price() throws InterruptedException {
		acc.search("T shirts");
		acc.click_search_btn();
		src.sort_by(2);
		String prc = src.apply_price_filter(0);
		boolean actual = src.verify_price_filter(prc) && src.verify_sort_by_price_high_to_low();
		Assert.assertEquals(actual, true);
	}
	
	@Test(priority=28)
	public void Search_TC73_Sort_by_Avg_Customer_Review_and_Filter_by_Customer_Rating() throws InterruptedException {
		acc.search("T shirts");
		acc.click_search_btn();
		src.sort_by(3);
		list_1 = src.get_product_codes();
		src.apply_rating_filter();
		list_2 = src.get_product_codes();
		boolean actual = src.check_duplicates(list_1, list_2)<list_1.size() && src.verify_sort_by_customer_reviews();
		Assert.assertEquals(actual, true);
	}
	
	@Test(priority=29)
	public void Search_TC74_Sort_by_Avg_Customer_Review_and_Filter_by_Online_Saving() throws InterruptedException {
		acc.search("T shirts");
		acc.click_search_btn();
		src.sort_by(3);
		list_1 = src.get_product_codes();
		src.apply_discount_filter();
		list_2 = src.get_product_codes();
		boolean actual = src.check_duplicates(list_1, list_2)<list_1.size() && src.verify_sort_by_customer_reviews();
		Assert.assertEquals(actual, true);
	}
	
	@Test(priority=30)
	public void Search_TC76_Sort_by_Avg_Customer_Review_and_Filter_by_Brand() throws InterruptedException {
		acc.search("T shirts");
		acc.click_search_btn();
		src.sort_by(3);
		src.apply_brand_filter(0);
		boolean actual = src.verify_brand_filter() && src.verify_sort_by_customer_reviews();
		Assert.assertEquals(actual, true);
	}
	
	@Test(priority=31)
	public void Search_TC77_Sort_by_Avg_Customer_Review_and_Filter_by_Price() throws InterruptedException {
		acc.search("T shirts");
		acc.click_search_btn();
		src.sort_by(3);
		String prc = src.apply_price_filter(0);
		boolean actual = src.verify_price_filter(prc) && src.verify_sort_by_customer_reviews();
		Assert.assertEquals(actual, true);
	}
	
	@Test(priority=32)
	public void Search_TC97_Pagination_with_Sort_by_Price_Low_to_High_and_Filter_by_Customer_Rating() throws InterruptedException {
		acc.search("T shirts");
		acc.click_search_btn();
		src.sort_by(1);
		src.apply_rating_filter();
		list_1 = src.get_product_codes();
		src.click_next_page();
		list_2 = src.get_product_codes();
		int actual = src.check_duplicates(list_1, list_2);
		Assert.assertEquals(actual, 0, "Duplicates found on next page:");
	}
	
	@Test(priority=33)
	public void Search_TC98_Pagination_with_Sort_by_Price_Low_to_High_and_Filter_by_Online_Saving() throws InterruptedException {
		acc.search("T shirts");
		acc.click_search_btn();
		src.sort_by(1);
		src.apply_discount_filter();
		list_1 = src.get_product_codes();
		src.click_next_page();
		list_2 = src.get_product_codes();
		int actual = src.check_duplicates(list_1, list_2);
		Assert.assertEquals(actual, 0, "Duplicates found on next page:");
	}
	
	@Test(priority=34)
	public void Search_TC100_Pagination_with_Sort_by_Price_Low_to_High_and_Filter_by_Brand() throws InterruptedException {
		acc.search("T shirts");
		acc.click_search_btn();
		src.sort_by(1);
		src.apply_brand_filter(0);
		list_1 = src.get_product_codes();
		src.click_next_page();
		list_2 = src.get_product_codes();
		int actual = src.check_duplicates(list_1, list_2);
		Assert.assertEquals(actual, 0, "Duplicates found on next page:");
	}
	
	@Test(priority=35)
	public void Search_TC101_Pagination_with_Sort_by_Price_Low_to_High_and_Filter_by_Price() throws InterruptedException {
		acc.search("T shirts");
		acc.click_search_btn();
		src.sort_by(1);
		src.apply_price_filter(0);
		list_1 = src.get_product_codes();
		src.click_next_page();
		list_2 = src.get_product_codes();
		int actual = src.check_duplicates(list_1, list_2);
		Assert.assertEquals(actual, 0, "Duplicates found on next page:");
	}
	
	@Test(priority=36)
	public void Search_TC105_Pagination_with_Sort_by_Price_High_to_Low_and_Filter_by_Customer_Rating() throws InterruptedException {
		acc.search("T shirts");
		acc.click_search_btn();
		src.sort_by(2);
		src.apply_rating_filter();
		list_1 = src.get_product_codes();
		src.click_next_page();
		list_2 = src.get_product_codes();
		int actual = src.check_duplicates(list_1, list_2);
		Assert.assertEquals(actual, 0, "Duplicates found on next page:");
	}
	
	@Test(priority=37)
	public void Search_TC106_Pagination_with_Sort_by_Price_High_to_Low_and_Filter_by_Online_Saving() throws InterruptedException {
		acc.search("T shirts");
		acc.click_search_btn();
		src.sort_by(2);
		src.apply_discount_filter();
		list_1 = src.get_product_codes();
		src.click_next_page();
		list_2 = src.get_product_codes();
		int actual = src.check_duplicates(list_1, list_2);
		Assert.assertEquals(actual, 0, "Duplicates found on next page:");
	}
	
	@Test(priority=38)
	public void Search_TC108_Pagination_with_Sort_by_Price_High_to_Low_and_Filter_by_Brand() throws InterruptedException {
		acc.search("T shirts");
		acc.click_search_btn();
		src.sort_by(2);
		src.apply_brand_filter(0);
		list_1 = src.get_product_codes();
		src.click_next_page();
		list_2 = src.get_product_codes();
		int actual = src.check_duplicates(list_1, list_2);
		Assert.assertEquals(actual, 0, "Duplicates found on next page:");
	}
	
	@Test(priority=39)
	public void Search_TC109_Pagination_with_Sort_by_Price_High_to_Low_and_Filter_by_Price() throws InterruptedException {
		acc.search("T shirts");
		acc.click_search_btn();
		src.sort_by(2);
		src.apply_price_filter(0);
		list_1 = src.get_product_codes();
		src.click_next_page();
		list_2 = src.get_product_codes();
		int actual = src.check_duplicates(list_1, list_2);
		Assert.assertEquals(actual, 0, "Duplicates found on next page:");
	}
	
	@Test(priority=40)
	public void Search_TC113_Pagination_with_Sort_by_Avg_Customer_Review_and_Filter_by_Customer_Rating() throws InterruptedException {
		acc.search("T shirts");
		acc.click_search_btn();
		src.sort_by(3);
		src.apply_rating_filter();
		list_1 = src.get_product_codes();
		src.click_next_page();
		list_2 = src.get_product_codes();
		int actual = src.check_duplicates(list_1, list_2);
		Assert.assertEquals(actual, 0, "Duplicates found on next page:");
	}
	
	@Test(priority=41)
	public void Search_TC114_Pagination_with_Sort_by_Avg_Customer_Review_and_Filter_by_Online_Saving() throws InterruptedException {
		acc.search("T shirts");
		acc.click_search_btn();
		src.sort_by(3);
		src.apply_discount_filter();
		list_1 = src.get_product_codes();
		src.click_next_page();
		list_2 = src.get_product_codes();
		int actual = src.check_duplicates(list_1, list_2);
		Assert.assertEquals(actual, 0, "Duplicates found on next page:");
	}
	
	@Test(priority=42)
	public void Search_TC116_Pagination_with_Sort_by_Avg_Customer_Review_and_Filter_by_Brand() throws InterruptedException {
		acc.search("T shirts");
		acc.click_search_btn();
		src.sort_by(3);
		src.apply_brand_filter(0);
		list_1 = src.get_product_codes();
		src.click_next_page();
		list_2 = src.get_product_codes();
		int actual = src.check_duplicates(list_1, list_2);
		Assert.assertEquals(actual, 0, "Duplicates found on next page:");
	}
	
	@Test(priority=43)
	public void Search_TC117_Pagination_with_Sort_by_Avg_Customer_Review_and_Filter_by_Price() throws InterruptedException {
		acc.search("T shirts");
		acc.click_search_btn();
		src.sort_by(3);
		src.apply_price_filter(0);
		list_1 = src.get_product_codes();
		src.click_next_page();
		list_2 = src.get_product_codes();
		int actual = src.check_duplicates(list_1, list_2);
		Assert.assertEquals(actual, 0, "Duplicates found on next page:");
	}
	
	@AfterMethod
	public void open_homepage() {
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
