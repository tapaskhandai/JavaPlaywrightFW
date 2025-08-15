package pages;

import com.microsoft.playwright.Page;

public class HomePage {

	private Page page; // encapsulation

	// constructor
	public HomePage(Page page) {
		this.page = page;
	}

	private String label_productStore = "//a[@id='nava']";
	private String link_cart = "//a[@id='cartur']";
	private String category_phone = "//div[@id='contcont']//a[2]";
	private String samsung_galaxy = "//a[text()='Samsung galaxy s6']";

	// page actions or method
	public boolean isLabelProdutStoreDisplayed() {

		return page.locator(label_productStore).isVisible();
	}

	public boolean iscategoryPhoneDisplayed() {

		return page.locator(category_phone).isVisible();
	}

	public boolean isSamsungGalaxyDisplayed() {

		return page.locator(samsung_galaxy).isVisible();
	}

	public void clickOnLinkCart() {
		page.locator(link_cart).click();
	}
}
