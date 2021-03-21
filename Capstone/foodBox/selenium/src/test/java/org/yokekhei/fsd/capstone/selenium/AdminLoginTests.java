package org.yokekhei.fsd.capstone.selenium;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

public class AdminLoginTests extends BaseTests {

	@Test(dependsOnMethods = "launchBrowser", priority = 0)
	public void testLoginSuccess() {
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

		WebElement adminDropDown = driver.findElement(By.xpath("//a[@id='adminNavDropDown']"));
		WebDriverWait wait2 = new WebDriverWait(driver, 30);
		wait2.until(ExpectedConditions.visibilityOf(adminDropDown));
		Assert.assertEquals(adminDropDown.getText(), "admin");
	}

	@AfterTest
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}

}
