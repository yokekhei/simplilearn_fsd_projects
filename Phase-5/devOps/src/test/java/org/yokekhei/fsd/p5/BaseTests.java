package org.yokekhei.fsd.p5;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

@SpringBootTest(classes = DevOpsApplication.class, webEnvironment = WebEnvironment.DEFINED_PORT)
public class BaseTests extends AbstractTestNGSpringContextTests {

	protected WebDriver driver;

	@LocalServerPort
	private int serverPort;

	@Parameters({ "BrowserType" })
	@Test
	public void launchBrowser(String browserType) {
		String driverPath = "";
		String browser = browserType.toLowerCase();

		if (browser.contains("chrome")) {
			driverPath = "selenium/chromedriver";

			if (TestUtils.isWindows()) {
				driverPath += ".exe";
			}

			System.setProperty("webdriver.chrome.driver", driverPath);
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--ignore-ssl-errors=yes");
			options.addArguments("--ignore-certificate-errors");

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
			driverPath = "selenium/geckodriver";

			if (TestUtils.isWindows()) {
				driverPath += ".exe";
			}

			System.setProperty("webdriver.gecko.driver", driverPath);
			FirefoxOptions options = new FirefoxOptions();
			options.addArguments("--ignore-ssl-errors=yes");
			options.addArguments("--ignore-certificate-errors");

			if (browser.contains("headless")) {
				options.setHeadless(true);
			}

			driver = new FirefoxDriver(options);
		}

		Assert.assertNotEquals(driverPath, "");
		Assert.assertNotNull(driver);

		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.get("https://localhost:" + serverPort);

		try {
			Thread.sleep(4000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}
