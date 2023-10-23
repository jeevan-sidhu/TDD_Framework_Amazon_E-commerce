package pom_package;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base_package.Base_Amazon;

public class Login_POM extends Base_Amazon {
	@FindBy(id="ap_email") WebElement email;
	@FindBy(id="continue") WebElement btn;
	@FindBy(id="ap_password") WebElement password;
	@FindBy(id="signInSubmit") WebElement submit;
	@FindBy(id="auth-email-missing-alert") WebElement email_err;
	@FindBy(id="auth-error-message-box") WebElement email_not_exist_err;	
	@FindBy(id="auth-password-missing-alert") WebElement pass_missing_err;
	@FindBy(id="auth-error-message-box") WebElement wrong_pass_err;
	@FindBy(xpath="//*[@id=\"nav-link-accountList-nav-line-1\"]") WebElement account;
	@FindBy(name="rememberMe") WebElement rem_checkbox;
	
	public Login_POM() {
		PageFactory.initElements(driver, this);
	}
	
	public void type_email(String em) {
		email.sendKeys(em);
	}
	
	public void click_continue() {
		btn.click();
	}
	
	public boolean email_err_display() {
		return email_err.isDisplayed();
	}
	
	public boolean email_not_exist_err_display() {
		return email_not_exist_err.isDisplayed();
	}
	
	public boolean pass_display() {
		return password.isDisplayed();
	}
	
	public void type_password(String pass) {
		password.sendKeys(pass);
	}
	
	public void click_submit() {
		submit.click();
	}
	
	public boolean pass_missing_err_display() {
		return pass_missing_err.isDisplayed();
	}
	
	public boolean wrong_pass_err_display() {
		return wrong_pass_err.isDisplayed();
	}
	
	public String get_acc_text() {
		return account.getText();
	}
	
	public void check_remember_me() {
		rem_checkbox.click();
	}
}
