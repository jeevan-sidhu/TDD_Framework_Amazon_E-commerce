package pom_package;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base_package.Base_Amazon;

public class Search_POM extends Base_Amazon {
	@FindBy(xpath="//div[@data-asin and @data-component-type=\"s-search-result\"]") List<WebElement> all_prod;
	@FindBy(id="add-to-cart-button") WebElement add;
	@FindBy(id="sw-gtc") WebElement goto_cart;
	@FindBy(id="productTitle") WebElement prod;
	@FindBy(xpath="//span[@class=\"s-pagination-strip\"]//a") List<WebElement> pagination_strip_bttns;
	@FindBy(xpath="//span[@class=\"s-pagination-strip\"]//span[@class=\"s-pagination-item s-pagination-selected\"]") WebElement current_page;
	@FindBy(id="buy-now-button") WebElement buy_now;
	@FindBy(xpath="//*[@id=\"header\"]/div/div[3]/h1") WebElement checkout_label;
	@FindBy(id="nav-flyout-searchAjax") WebElement suggestion_box;
	@FindBy(xpath="//div[@tabindex=\"0\"]/div/../span") WebElement result_page_label;
	@FindBy(xpath="//div[@data-asin and @data-component-type=\"s-search-result\"]//span[@class=\"a-size-base-plus a-color-base\"]") List<WebElement> brand_list;
	@FindBy(xpath="//div[@data-asin and @data-component-type=\"s-search-result\"]//span[@class=\"a-size-base s-underline-text\"]") List<WebElement> reviews_list;
	@FindBy(xpath="//div[@data-asin and @data-component-type=\"s-search-result\"]/div[not(descendant::*[@class=\"a-section a-spacing-none a-spacing-top-small\"])]//span[@class=\"a-price\"]/span[@class]") List<WebElement> price_list;
	@FindBy(id="p_72/11192170011") WebElement rating_filter;
	@FindBy(xpath="//*[@id=\"p_n_deal_type/23565508011\"]/span/a") WebElement discount_filter;
	@FindBy(xpath="//*[@id=\"brandsRefinements\"]//ul/span/li/span/a") List<WebElement> brand_filter;
	@FindBy(xpath="//*[@id=\"priceRefinements\"]/ul//a") List<WebElement> price_filter;
	@FindBy(id="a-autoid-0-announce") WebElement sort_by_btn;
	@FindBy(xpath="//a[@aria-hidden and contains(@id,\"s-result-sort\")]") List<WebElement> sort_by_list;
	
	public Search_POM() {
		PageFactory.initElements(driver, this);
	}
	
	public int num_of_prod() {
		return all_prod.size();
	}
	
	public void add_to_cart() {
		add.click();
	}
	
	public void go_to_cart() {
		goto_cart.click();
	}
	
	public void click_prod(int index) {
		all_prod.get(index-1).click();
	}
	
	public void click_prod() {
		prod.click();
	}
	
	public void click_buy_now() {
		buy_now.click();
	}
	
	public String get_prod_title() {
		return prod.getText();
	}
	
	public void click_next_page() throws InterruptedException {
		pagination_strip_bttns.get(pagination_strip_bttns.size()-1).click();
		Thread.sleep(2000);
	}
	
	public void click_previous_page() throws InterruptedException {
		pagination_strip_bttns.get(0).click();
		Thread.sleep(2000);
	}
	
	public String get_current_page() {
		return current_page.getAttribute("aria-label");
	}
	
	public String verify_checkout_page_display() {
		String result;
		try {
			result = checkout_label.getAttribute("innerText");			
		}
		catch(NoSuchElementException e) {
			result = "Sign-in to checkout";
		}
		return result;
	}
	
	public boolean check_suggestion_box_display() {
		return suggestion_box.isDisplayed();
	}
	
	public String check_result_page_display() {
		return result_page_label.getText();
	}
	
	public List<String> get_product_codes() {
		List<String> prod_codes = new ArrayList<>();
		for(WebElement el : all_prod) {
			prod_codes.add(el.getAttribute("data-asin"));
		}
		return prod_codes;
	}
	
	public int check_duplicates(List<String> l1, List<String> l2) {
		List<String>l3 = new ArrayList<>(l1);
		l3.retainAll(l2);
		return l3.size();
	}
	
	public void check_price_list() {
		for(WebElement el:price_list) {
			System.out.println(Float.parseFloat(el.getAttribute("innerText").substring(1)));
		}
	}
	
	public void apply_rating_filter() throws InterruptedException {
		rating_filter.click();
		Thread.sleep(2000);
	}
	
	public void apply_discount_filter() throws InterruptedException {
		discount_filter.click();
		Thread.sleep(2000);
	}
	
	public void apply_brand_filter(int i) throws InterruptedException {
		brand_filter.get(i).click();
		Thread.sleep(2000);
	}
	
	public boolean verify_brand_filter() {
		List<String> brands = new ArrayList<>();
		for(WebElement el : brand_list) {
			brands.add(el.getText());
		}
		return brands.stream().distinct().count()==1;
	}
	
	public String apply_price_filter(int i) throws InterruptedException {
		String txt = price_filter.get(i).getAttribute("innerText");
		price_filter.get(i).click();
		Thread.sleep(2000);
		return txt;
	}
	
	public List<Float> get_price_list() {
		List<Float> prc_list = new ArrayList<>();
		for(WebElement el : price_list) {
			prc_list.add(Float.parseFloat(el.getAttribute("innerText").substring(1).replaceAll(",", "")));
		}
		return prc_list;
	}
	
	public boolean verify_price_filter(String prc) {
		List<Float> prices = get_price_list();
		boolean result=true;
		switch(prc){
		case "Under $25":
			for(Float p:prices) {
				if(p>=25) {
					result=false;
					break;
				}
			}
			break;
		case "$25 to $50":
			for(Float p:prices) {
				if(p<25 || p>50) {
					result=false;
					break;
				}
			}
			break;
		case "$50 to $100":
			for(Float p:prices) {
				if(p<50 || p>100) {
					result=false;
					break;
				}
			}
			break;
		case "$100 to $200":
			for(Float p:prices) {
				if(p<100 || p>200) {
					result=false;
					break;
				}
			}
			break;
		case "$200 & Above":
			for(Float p:prices) {
				if(p<200) {
					result=false;
					break;
				}
			}
			break;
		}
		return result;
	}
	
	public boolean verify_sort_by_price_high_to_low() {
		List<Float> prc_list = get_price_list();
		if(prc_list.isEmpty()) {
			return false;
		}
		for (int i = 0; i < prc_list.size()-1; i++) {
	        if (prc_list.get(i) < prc_list.get(i+1))
	            return false;
	    }
	    return true;
	}
	
	public boolean verify_sort_by_price_low_to_high() {
		List<Float> prc_list = get_price_list();
		if(prc_list.isEmpty()) {
			return false;
		}
		for (int i = 0; i < prc_list.size()-1; i++) {
	        if (prc_list.get(i) > prc_list.get(i+1))
	            return false;
	    }
	    return true;
	}
	
	public List<Integer> get_reviews_list() {
		List<Integer> rtg = new ArrayList<>();
		for(WebElement el : reviews_list) {
			rtg.add(Integer.parseInt(el.getAttribute("innerText").replaceAll(",", "")));
		}
		return rtg;
	}
	
	public boolean verify_sort_by_customer_reviews() {
		List<Integer> rtg_list = get_reviews_list();
		if(rtg_list.isEmpty()) {
			return false;
		}
		for (int i = 0; i < rtg_list.size()-1; i++) {
	        if (rtg_list.get(i) < rtg_list.get(i+1))
	            return false;
	    }
	    return true;
	}
	
	public void sort_by(int i) throws InterruptedException {
		boolean flag=true;
		while(flag) {
			try {
				Actions actions = new Actions(driver);
				actions.moveToElement(sort_by_btn).click().perform();
				Thread.sleep(1000);
				actions.moveToElement(sort_by_list.get(i)).click().perform();
				flag=false;
			}
			catch(IndexOutOfBoundsException e) {
				flag=true;
			}
		}
		Thread.sleep(3000);
	}

}
