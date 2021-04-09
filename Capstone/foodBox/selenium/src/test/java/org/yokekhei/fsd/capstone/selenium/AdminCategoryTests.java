package org.yokekhei.fsd.capstone.selenium;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

public class AdminCategoryTests extends BaseTests {

	@Test(dependsOnMethods = "launchBrowser", priority = 0)
	public void testListCategorySuccess() {
		this.accessPage(this.getBaseUrl() + "/admin");

		WebElement email = driver.findElement(By.cssSelector("input[id=email]"));
		WebDriverWait wait1 = new WebDriverWait(driver, 30);
		wait1.until(ExpectedConditions.visibilityOf(email));
		email.sendKeys("admin@foodbox.com");

		WebElement password = driver.findElement(By.cssSelector("input[id=password]"));
		password.sendKeys("Pa$sw0rd");

		WebElement button = driver.findElement(By.xpath("//button[@type='submit']"));
		button.click();

		driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);

		WebElement table = driver.findElement(By.xpath("//table"));
		WebDriverWait wait2 = new WebDriverWait(driver, 30);
		wait2.until(ExpectedConditions.visibilityOf(table));

		Assert.assertNotNull(table);

		List<WebElement> rows = driver.findElements(By.xpath("//table/tbody/tr/td[1]"));
		Assert.assertEquals(rows.size(), 4);

		WebElement thc2 = driver.findElement(By.xpath("//table/thead/tr[1]/th[2]/span"));
		Assert.assertEquals(thc2.getText(), "Name");

		WebElement thc3 = driver.findElement(By.xpath("//table/thead/tr[1]/th[3]/span"));
		Assert.assertEquals(thc3.getText(), "Date Created");

		WebElement thc4 = driver.findElement(By.xpath("//table/thead/tr[1]/th[4]/span"));
		Assert.assertEquals(thc4.getText(), "Enabled/Disabled");

		WebElement r1c2 = driver.findElement(By.xpath("//table/tbody/tr[1]/td[2]"));
		Assert.assertEquals(r1c2.getText(), "Chinese");
	}

	@AfterTest
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}

}
