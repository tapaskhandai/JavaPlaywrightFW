package pages;

import com.microsoft.playwright.Page;

public class LoginPage {

	private Page page; //encapsulation

	// constructor
	public LoginPage(Page page) {
		this.page = page;
	}

	// locators
	private String button_login = "//a[@id='login2']";
	private String textbox_username = "#loginusername";
	private String textbox_password = "#loginpassword";
	private String button_submit = "//button[@onclick='logIn()']";

	// page actions or method
	public String getLoginPageTitle() {
		return page.title();
	}

	public String getLoginPageUrl() {
		return page.url();
	}

	public HomePage doLogin(String username, String password) {
		page.locator(button_login).click();
		page.locator(textbox_username).fill(username);
		page.locator(textbox_password).fill(password);
		page.locator(button_submit).click();
		return new HomePage(page);
	}
}
