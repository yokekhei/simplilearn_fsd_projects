package org.yokekhei.fsd.capstone.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class BaseTests {

	private static final int serverPort = 4200;

	protected WebDriver driver;

	@Test
	@Parameters({ "browserType" })
	public void launchBrowser(String browserType) {
		String driverPath = "";
		String browser = browserType.toLowerCase();

		if (browser.contains("chrome")) {
			driverPath = "bin/chromedriver";

			if (TestUtils.isWindows()) {
				driverPath += ".exe";
			}

			System.setProperty("webdriver.chrome.driver", driverPath);
			ChromeOptions options = new ChromeOptions();

			if (browser.contains("headless")) {
				options.addArguments("--headless");
				options.addArguments("window-size=1400,1500");
				options.addArguments("--disable-gpu");
				options.addArguments("--no-sandbox");
				options.addArguments("start-maximized");
				options.addArguments("enable-automation");
				options.addArguments("--disable-infobars");
				options.addArguments("--disable-dev-shm-usage");
			}

			driver = new ChromeDriver(options);
		} else if (browser.contains("firefox")) {
			driverPath = "bin/geckodriver";

			if (TestUtils.isWindows()) {
				driverPath += ".exe";
			}

			System.setProperty("webdriver.gecko.driver", driverPath);
			FirefoxOptions options = new FirefoxOptions();

			if (browser.contains("headless")) {
				options.setHeadless(true);
			}

			driver = new FirefoxDriver(options);
		}

		Assert.assertNotEquals(driverPath, "");
		Assert.assertNotNull(driver);

		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
	}

	protected String getBaseUrl() {
		return "http://localhost:" + serverPort;
	}

	protected void accessPage(String url) {
		driver.get(url);

		try {
			Thread.sleep(4000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
