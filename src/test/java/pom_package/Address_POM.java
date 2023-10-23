package pom_package;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base_package.Base_Amazon;

public class Address_POM extends Base_Amazon {
	JavascriptExecutor js = (JavascriptExecutor) driver;
	@FindBy(xpath="//*[@id=\"nav-link-accountList-nav-line-1\"]") WebElement account;
	@FindBy(xpath="//div[@data-card-identifier=\"AddressesAnd1Click\"]") WebElement Your_Addresses;
	@FindBy(id="ya-myab-address-add-link") WebElement add_addr;
	@FindBy(xpath="//*[@id=\"a-page\"]/div[1]/div/div[2]/h2") WebElement new_addr_label;
	@FindBy(id="address-ui-widgets-countryCode") WebElement country;
	@FindBy(id="address-ui-widgets-enterAddressFullName") WebElement full_name;
	@FindBy(id="address-ui-widgets-enterAddressFullName-full-validation-alerts") WebElement full_name_err;
	@FindBy(id="address-ui-widgets-enterAddressPhoneNumber") WebElement phone_num;
	@FindBy(id="address-ui-widgets-enterAddressPhoneNumber-full-validation-alerts") WebElement phone_num_err;
	@FindBy(id="address-ui-widgets-enterAddressLine1") WebElement addr_line1;
	@FindBy(id="address-ui-widgets-enterAddressLine1-full-validation-alerts") WebElement addr_line1_err;
	@FindBy(id="address-ui-widgets-enterAddressCity") WebElement city;
	@FindBy(id="address-ui-widgets-enterAddressCity-full-validation-alerts") WebElement city_err;
	@FindBy(id="address-ui-widgets-enterAddressPostalCode") WebElement postal_code;
	@FindBy(id="address-ui-widgets-enterAddressPostalCode-full-validation-alerts") WebElement postal_code_err;
	@FindBy(xpath="//span[@data-action=\"a-dropdown-button\"]") List<WebElement> dropdown_bttns;
	@FindBy(xpath="//a[@aria-hidden and @id]") List<WebElement> province_list;
	@FindBy(xpath="//*[@id=\"address-ui-widgets-enterAddressFormContainer\"]/div[4]/a") WebElement add_preference;
	@FindBy(id="address-ui-widgets-use-as-my-default") WebElement default_chkbox;
	@FindBy(xpath="//button[@aria-label]") List<WebElement> property_type;
	@FindBy(xpath="//input[@name=\"securityCode\"]") List<WebElement> security_code_boxes;
	@FindBy(xpath="//button[@name=\"open\"]") List<WebElement> weekend_delivery_yes_bttns;
	@FindBy(xpath="//button[@name=\"open\"]/../..") List<WebElement> weekend_delivery_bttns_status;
	@FindBy(xpath="//*[@id=\"address-ui-widgets-delivery-instructions-desktop-widget-html-container\"]/div/div/div[1]/div[4]/div[3]/a/span/div/div[1]/span") WebElement weekend_delivery;
	@FindBy(xpath="//*[@id=\"address-ui-widgets-delivery-instructions-desktop-widget-html-container\"]/div/div/div[1]/div[4]/div[1]/a/span/div/div[2]/div[1]/span[2]") WebElement security_code;
	@FindBy(xpath="//*[@id=\"address-ui-widgets-form-submit-button\"]/span/input") WebElement addr_submit_btn;
	@FindBy(id="address-ui-widgets-original-address-block_id-input") WebElement orig_addr_radio_bttn;
	@FindBy(id="address-ui-widgets-suggested-address-block_id-input") WebElement sugg_addr_radio_bttn;
	@FindBy(name="address-ui-widgets-saveOriginalOrSuggestedAddress") WebElement save_addr_bttn;
	@FindBy(id="ya-myab-set-default-shipping-btn-0") WebElement set_as_default;
	@FindBy(id="ya-myab-address-delete-btn-0") WebElement remove_addr;
	@FindBy(xpath="//*[@id=\"deleteAddressModal-0-submit-btn\"]/span/input") WebElement confirm_del_addr;
	
	public Address_POM() {
		PageFactory.initElements(driver, this);
	}
	
	public void click_your_addresses() {
		boolean flag=true;
		while(flag) {
			try {
				Your_Addresses.click();
				add_addr.isDisplayed();
				flag=false;
			}
			catch(NoSuchElementException e) {
				account.click();
				flag=true;
			}
		}
	}
	
	public boolean check_add_address() {
		return add_addr.isDisplayed();
	}
	
	public void click_add_address() {
		boolean flag=true;
		while(flag) {
			try {
				add_addr.click();
				full_name.isDisplayed();
				flag=false;
			}
			catch(NoSuchElementException e) {
				account.click();
				click_your_addresses();
				flag=true;
			}
		}
	}
	
	public String get_new_address_label() {
		return new_addr_label.getText();
	}
	
	public String get_country() {
		return country.getText();
	}
	
	public void type_full_name(String txt) {
		full_name.sendKeys(txt);
	}
	
	public void type_phone_num(String txt) {
		phone_num.sendKeys(txt);
	}
	
	public void type_address(String txt) {
		addr_line1.sendKeys(txt);
	}
	
	public void type_city(String txt) {
		city.sendKeys(txt);
	}
	
	public void select_province(int i) {
		boolean flag=true;
		while(flag) {
			try {
				dropdown_bttns.get(1).click();
				Actions action = new Actions(driver);
				action.moveToElement(province_list.get(i)).click().perform();
				flag=false;
			}
			catch(IndexOutOfBoundsException e) {
				flag=true;
			}
		}
	}
	
	public void type_postal_code(String txt) {
		js.executeScript("arguments[0].scrollIntoView();", postal_code);
		postal_code.sendKeys(txt);
	}
	
	public boolean check_full_name_error() {
		try {
			return full_name_err.isDisplayed();
		}
		catch(NoSuchElementException e) {
			return false;
		}
	}
	
	public boolean check_phone_num_error() {
		try {
			return phone_num_err.isDisplayed();
		}
		catch(NoSuchElementException e) {
			return false;
		}		
	}
	
	public boolean check_address_error() {
		try {
			return addr_line1_err.isDisplayed();
		}
		catch(NoSuchElementException e) {
			return false;
		}
	}
	
	public boolean check_city_error() {
		try {
			return city_err.isDisplayed();
		}
		catch(NoSuchElementException e) {
			return false;
		}
	}
	
	public boolean check_postal_code_error() {
		try {
			return postal_code_err.isDisplayed();
		}
		catch(NoSuchElementException e) {
			return false;
		}
	}
	
	public void click_add_preferences() {
		add_preference.click();

	}
	
	public void click_default_chkbox() {
		default_chkbox.click();
	}
	
	public void select_property_type(int i) {
		js.executeScript("arguments[0].scrollIntoView();", property_type.get(i));
		property_type.get(i).click();
	}
	
	public boolean check_default_chkbox() {
		return default_chkbox.isSelected();
	}
	
	public void type_security_code(int i, String txt) {
		js.executeScript("arguments[0].scrollIntoView();", security_code_boxes.get(i));
		security_code_boxes.get(i).sendKeys(txt);
	}
	
	public String get_security_code() {
		return security_code.getText();
	}
	
	public void click_weekend_delivery() {
		weekend_delivery.click();
	}
	
	public void select_weekend_delivery_option(int i) {
		weekend_delivery_yes_bttns.get(i).click();
	}
	
	public String check_weekend_delivery_button_status(int i) {
		return weekend_delivery_bttns_status.get(i).getAttribute("aria-checked");
	}
	
	public void click_submit_address() {
		addr_submit_btn.click();
	}
	
	public void select_original_address() {
		orig_addr_radio_bttn.click();
	}
	
	public void select_suggested_address() {
		sugg_addr_radio_bttn.click();
	}
	
	public void save_address() {
		save_addr_bttn.click();
		boolean flag=true;
		while(flag) {
			try {
				add_addr.isDisplayed();
				flag=false;
			}
			catch(NoSuchElementException e) {
				account.click();
				click_your_addresses();
				flag=true;
			}
		}
	}
	
	public boolean check_set_as_default() {
		try {
			return set_as_default.isDisplayed();
		}
		catch(NoSuchElementException e) {
			return false;
		}
	}
	
	public void delete_address() throws InterruptedException {
		remove_addr.click();
		Thread.sleep(2000);
		confirm_del_addr.click();
	}
	
}
