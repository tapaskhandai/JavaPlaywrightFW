package mytests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.HomePage;

public class HomePageTest extends BaseTest {

	HomePage homepage;

	@Test(priority = 1)
	public void checkLoginSuccessful() {
		
		homepage = loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Test(priority = 2)
	public void verifyElementsvisible() {
		//homepage=new HomePage(page);
		Assert.assertTrue(homepage.iscategoryPhoneDisplayed());
		Assert.assertTrue(homepage.isLabelProdutStoreDisplayed());
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Assert.assertTrue(homepage.isSamsungGalaxyDisplayed());
	}

	@Test(priority = 3)
	public void verifyCartLinkClicked() {
		homepage.clickOnLinkCart();
	}

}
