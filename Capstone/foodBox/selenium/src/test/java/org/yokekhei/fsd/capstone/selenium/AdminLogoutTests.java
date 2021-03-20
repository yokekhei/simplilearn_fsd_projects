package org.yokekhei.fsd.capstone.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class AdminLogoutTests extends BaseTests {

	@Test(dependsOnMethods = "launchBrowser", priority = 0, enabled = false)
	@Parameters({ "browserType" })
	public void testLogoutSuccess(String browserType) {
		this.accessPage(this.getBaseUrl() + "/admin");

		WebElement email = driver.findElement(By.cssSelector("input[id=email]"));
		WebDriverWait wait1 = new WebDriverWait(driver, 30);
		wait1.until(ExpectedConditions.visibilityOf(email));
		email.sendKeys("admin@foodbox.com");

		WebElement password = driver.findElement(By.cssSelector("input[id=password]"));
		password.sendKeys("Pa$sw0rd");

		WebElement button = driver.findElement(By.xpath("//button[@type='submit']"));
		button.click();

		WebElement adminDropDown = driver.findElement(By.xpath("//a[@id='adminNavDropDown']"));
		WebDriverWait wait2 = new WebDriverWait(driver, 30);
		wait2.until(ExpectedConditions.visibilityOf(adminDropDown));

		WebElement logoutDropItem = driver.findElement(By.xpath("//a[@routerlink='/admin/logout']"));
		WebDriverWait wait3 = new WebDriverWait(driver, 30);
		wait3.until(ExpectedConditions.visibilityOf(logoutDropItem));
		logoutDropItem.click();

		WebElement headerText = driver.findElement(By.xpath("//h4"));
		WebDriverWait wait4 = new WebDriverWait(driver, 30);
		wait4.until(ExpectedConditions.visibilityOf(headerText));

		Assert.assertNotNull(headerText);
		Assert.assertEquals(headerText.getText(), "Admin Sign in");
	}

	@AfterTest
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}

}
