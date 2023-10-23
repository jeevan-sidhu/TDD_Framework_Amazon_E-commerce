package pom_package;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base_package.Base_Amazon;

public class Orders_POM extends Base_Amazon {
	@FindBy(xpath="//div[@data-card-identifier=\"YourOrders\"]") WebElement Your_Orders;
	@FindBy(xpath="//*[@id=\"a-page\"]/section/div/div[3]/ul/li[2]/a") WebElement buy_again;
	@FindBy(xpath="//*[@id=\"a-page\"]/section/div/div[3]/ul/li[3]/a") WebElement ny_shipped;
	@FindBy(xpath="//*[@id=\"a-page\"]/section/div/div[3]/ul/li[4]/a") WebElement cancelled_orders;
	@FindBy(id="time-filter") WebElement time_filter;
	@FindBy(xpath="//div[starts-with(@id,'CardInstance')]") List<WebElement> buy_again_recom;
	@FindBy(id="ordersContainer") WebElement ord_container;
	
	public Orders_POM() {
		PageFactory.initElements(driver, this);
	}
	
	public void click_orders() {
		Your_Orders.click();
	}
	
	public void click_buy_again() {
		buy_again.click();
	}
	
	public void click_ny_shipped() {
		ny_shipped.click();
	}
	
	public void click_cancelled_orders() {
		cancelled_orders.click();
	}
	
	public boolean time_filter_display() {
		return time_filter.isDisplayed();
	}
	
	public boolean buy_again_recommendations_display() {
		System.out.println(buy_again_recom.get(1).getText());
		return buy_again_recom.get(1).isDisplayed();
	}
	
	public boolean orders_container_display() {
		return ord_container.isDisplayed();
	}

}
