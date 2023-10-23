package pom_package;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base_package.Base_Amazon;

public class Cart_POM extends Base_Amazon {
	@FindBy(xpath="//div[@data-asin]") List<WebElement> cart_items;
	@FindBy(xpath="//img[@class=\"sc-product-image\"]") List<WebElement> cart_item_images;
	@FindBy(xpath="//span[@class=\"sc-action-quantity\"]") List<WebElement> item_qty;
	@FindBy(xpath="//*[@id=\"sc-subtotal-amount-activecart\"]/span") WebElement subtotal;
	@FindBy(xpath="//span[@class=\"a-size-medium a-color-base sc-price sc-white-space-nowrap sc-product-price a-text-bold\"]") List<WebElement> price_list;
	@FindBy(xpath="//input[@value=\"Delete\"]") List<WebElement> del_bttns;
	@FindBy(id="a-autoid-1-announce") WebElement qty_btn;
	@FindBy(xpath="//a[@aria-hidden and @id]") List<WebElement> qty_list;
	
	public Cart_POM() {
		PageFactory.initElements(driver, this);
	}
	
	public int num_of_items_in_cart() {
		return cart_items.size();
	}
	
	public void click_item_in_cart(int i) {
		cart_item_images.get(i-1).click();
	}
	
	public List<String> get_items_in_cart() {
		List<String> cart_items_title = new ArrayList<>();
		for(WebElement el : cart_items) {
			 String str = el.getText();
			 String[] res = str.split("[,]", 2);
			 cart_items_title.add(res[0]);
		}
		return cart_items_title;
	}
	
	public int get_qty(int i) {
		return Integer.parseInt(item_qty.get(i).getAttribute("data-old-value"));
	}
	
	public float get_item_price(int i) {
		return Float.parseFloat(price_list.get(i).getText().substring(1));
	}
	
	public float get_subtotal() {
		try {
			return Float.parseFloat(subtotal.getText().substring(1));
		}
		catch(NoSuchElementException e) {
			return 0;
		}
	}
	
	public void del_all_items_in_cart() throws InterruptedException {
		while(del_bttns.size()!=0) {
			del_bttns.get(0).click();
			Thread.sleep(2000);
		}
	}
	
	public void del_items_in_cart(int num) {
		int total_items = del_bttns.size();
		for(int i=0; i<num;i++) {
			del_bttns.get(0).click();
			while(del_bttns.size()!=total_items-1) {
				continue;
			}
			total_items-=1;
			System.out.println("1 item deleted from cart");
		}
	}
	
	public void select_quantity(int i) {
		boolean flag=true;
		while(flag) {
			try {
				qty_btn.click();
				Actions actions = new Actions(driver);
				actions.moveToElement(qty_list.get(i)).click().perform();
				flag=false;
			}
			catch(IndexOutOfBoundsException e) {
				flag=true;
			}
		}
	}
	
}