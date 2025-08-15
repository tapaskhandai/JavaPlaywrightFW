package factory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Properties;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.BrowserType.LaunchOptions;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

import listeners.ExtentReportListener;

public class PlaywrightFactory {

	Playwright playwright;
	Browser browser;
	BrowserContext context;
	Page page;
	Properties prop;

	private static ThreadLocal<Browser> tlBrowser = new ThreadLocal<Browser>();
	private static ThreadLocal<BrowserContext> tlBrowserContext = new ThreadLocal<BrowserContext>();
	private static ThreadLocal<Page> tlPage = new ThreadLocal<Page>();
	private static ThreadLocal<Playwright> tlPlaywright = new ThreadLocal<Playwright>();

	public static Playwright getPlayWright() {

		return tlPlaywright.get();
	}

	public static BrowserContext getBrowserContext() {

		return tlBrowserContext.get();
	}

	public static Browser getBrowser() {

		return tlBrowser.get();
	}

	public static Page getPage() {

		return tlPage.get();
	}

	public Page initBrowser(Properties prop) {

		// playwright = Playwright.create();
		tlPlaywright.set(Playwright.create());

		switch (prop.getProperty("browser").trim().toLowerCase()) {
		case "chromium":
			// browser = playwright.chromium().launch(new
			// BrowserType.LaunchOptions().setHeadless(false));
			tlBrowser.set(getPlayWright().chromium().launch(new BrowserType.LaunchOptions().setHeadless(false)));
			break;
		case "chrome":
			tlBrowser.set(getPlayWright().chromium().launch(new LaunchOptions().setChannel("chrome").setHeadless(false)));
			break;
		case "edge":
			tlBrowser.set(getPlayWright().chromium().launch(new LaunchOptions().setChannel("msedge").setHeadless(false)));
			break;
		default:
			tlBrowser.set(getPlayWright().chromium().launch(new LaunchOptions().setChannel("chrome").setHeadless(false)));
			break;
		}
		tlBrowserContext.set(getBrowser().newContext());
		tlPage.set(getBrowserContext().newPage());
		getPage().navigate(prop.getProperty("url").trim());
		
//		BrowserContext context = browser.newContext();
//		Page page = context.newPage();
//		page.navigate(prop.getProperty("url").trim());
//		return page;
		
		return getPage();
	}

	public Properties setProperties() {
        
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(
					"C:\\Users\\tapas\\eclipse-workspace\\JavaPlaywrightFW\\src\\test\\resources\\config\\config.properties");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		prop = new Properties();
		try {
			prop.load(fis);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}
	public static String takeScreenshot() {
		String path=System.getProperty("user.dir")+"/screenshot/"+System.currentTimeMillis()+".png";
		getPage().screenshot(new Page.ScreenshotOptions().setPath(Paths.get(path)).setFullPage(true));
		return path;
	}
}
