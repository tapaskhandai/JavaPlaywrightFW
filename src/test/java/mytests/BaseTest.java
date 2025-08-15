package mytests;

import java.util.Properties;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentTest;
import com.microsoft.playwright.Page;

import factory.PlaywrightFactory;
import pages.HomePage;
import pages.LoginPage;
import listeners.ExtentReportListener;

public class BaseTest {
	
	PlaywrightFactory pfactory;
	Page page;
	protected Properties prop;
	protected LoginPage loginpage;
	protected HomePage homepage;
	
	
	
	@BeforeTest //will run before all the @Test methods gets executed
	public void setUp() {
//		ExtentTest extentTest = ExtentReportListener.extent.createTest("Test Setup");
//	    ExtentReportListener.test.set(extentTest);
		pfactory=new PlaywrightFactory();
		prop=pfactory.setProperties();
		page=pfactory.initBrowser(prop);
		loginpage=new LoginPage(page);
	//	homepage=new HomePage(page);
	}
	
	@AfterTest ////will run after all the @Test methods gets executed
	public void tearDown() {
		page.context().browser().close();
	}

}
