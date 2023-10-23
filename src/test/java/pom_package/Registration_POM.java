package pom_package;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base_package.Base_Amazon;

public class Registration_POM extends Base_Amazon {
	@FindBy(id="ap_customer_name") WebElement your_name;
	@FindBy(id="ap_email") WebElement mob_email;
	@FindBy(id="ap_password") WebElement password;
	@FindBy(id="ap_password_check") WebElement pass_again;
	@FindBy(id="continue") WebElement btn;
	@FindBy(id="auth-continue-announce") WebElement btn_txt;
	@FindBy(id="auth-customerName-missing-alert") WebElement name_error;
	@FindBy(id="auth-email-missing-alert") WebElement email_error;
	@FindBy(id="auth-password-missing-alert") WebElement pass_error;
	@FindBy(id="auth-password-invalid-password-alert") WebElement pass_invalid_error;	
	@FindBy(id="auth-password-mismatch-alert") WebElement pass2_error;
	@FindBy(id="register-mase-inlineerror") WebElement exist_acc_error;
	@FindBy(xpath="//*[@id=\"authportal-main-section\"]/div[2]/div/div[2]/h4[1]") WebElement exist_acc_error2;
	
	public Registration_POM() {
		PageFactory.initElements(driver, this);
	}
	
	public void type_your_name(String name) {
		your_name.sendKeys(name);
	}
	
	public void type_mob_email(String mob) {
		mob_email.sendKeys(mob);
	}
	
	public void type_password(String pass) {
		password.sendKeys(pass);
	}
	
	public void type_pass_again(String pass) {
		pass_again.sendKeys(pass);
	}
	
	public void submit() {
		btn.click();
	}
	
	public String get_btn_txt() {
		return btn_txt.getText();
	}
	
	public boolean name_err_display() {
		return name_error.isDisplayed();
	}
	
	public boolean email_err_display() {
		return email_error.isDisplayed();
	}
	
	public boolean pass_err_display() {
		return pass_error.isDisplayed();
	}
	
	public boolean pass_invalid_err_display() {
		return pass_invalid_error.isDisplayed();
	}
	
	public boolean pass2_err_display() {
		return pass2_error.isDisplayed();
	}
	
	public String exist_acc_err_txt() {
		try {
			return exist_acc_error.getText();
		}
		catch(NoSuchElementException e) {
			return exist_acc_error2.getText();
		}
	}
}
