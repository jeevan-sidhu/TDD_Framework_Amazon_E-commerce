package pom_package;

import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import base_package.Base_Amazon;

public class Account_POM extends Base_Amazon {
	@FindBy(xpath="//*[@id=\"nav-signin-tooltip\"]/div/a") WebElement signup;
	@FindBy(xpath="//*[@id=\"nav-signin-tooltip\"]/a") WebElement signin;
	@FindBy(xpath="//*[@id=\"nav-link-accountList-nav-line-1\"]") WebElement account;
	@FindBy(id="nav-item-signout") WebElement signout;
	@FindBy(id="nav-cart") WebElement cart;
	@FindBy(id="twotabsearchtextbox") WebElement search;
	@FindBy(id="nav-search-submit-button") WebElement search_btn;
	
	public Account_POM() {
		PageFactory.initElements(driver, this);
	}
	
	public void click_signup() {
		boolean flag=true;
		while(flag) {
			try {
				signup.click();
				flag=false;
			}
			catch(NoSuchElementException e) {
				driver.navigate().refresh();
				flag=true;
			}
		}		
	}
	
	public void click_signin() {
		boolean flag=true;
		while(flag) {
			try {
				signin.click();
				flag=false;
			}
			catch(NoSuchElementException e) {
				driver.navigate().refresh();
				flag=true;
			}
		}
	}
	
	public void click_account() {
		account.click();
	}
	
	public void click_signout() {
		boolean flag=true;
		while(flag) {
			try {
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("arguments[0].scrollIntoView(true);", account);
				Actions act = new Actions(driver);
				act.moveToElement(account).build().perform();
				js.executeScript("arguments[0].scrollIntoView(true);", signout);
				wait.until(ExpectedConditions.elementToBeClickable(signout)).click();
				flag=false;
			}
			catch(ElementNotInteractableException e) {
				e.printStackTrace();
				flag=true;
			}
		}
	}
	
	public void click_cart() {
		cart.click();
	}
	
	public void search(String txt) {
		boolean flag=true;
		while(flag) {
			try {
				search.sendKeys(txt);
				flag=false;
			}
			catch(NoSuchElementException e) {
				driver.navigate().refresh();
				flag=true;
			}
		}
	}
	
	public void click_search_btn() {
		search_btn.click();
	}
}
