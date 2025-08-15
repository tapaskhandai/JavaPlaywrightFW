package mytests;

import org.testng.Assert;
import org.testng.annotations.Test;

import constant.AppConstants;
import pages.LoginPage;

public class LoginPageTest extends BaseTest {

	LoginPage loginpage;

	@Test
	public void verifyLoginPageTitle() {
		loginpage = new LoginPage(page);
		String loginPageTitle = loginpage.getLoginPageTitle();
		Assert.assertEquals(loginPageTitle, AppConstants.LOGIN_PAGE_TITLE, "Title not matching");
	}

	@Test
	public void verifyLoginPageUrl() {
		String loginPageUrl = loginpage.getLoginPageUrl();
		Assert.assertTrue(!loginPageUrl.isEmpty(), "Url is empty");
	}
	
	@Test
	public void verifyLoginSuccessful() {
		
		loginpage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
	}

}
