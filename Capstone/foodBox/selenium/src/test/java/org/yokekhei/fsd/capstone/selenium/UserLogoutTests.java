package org.yokekhei.fsd.capstone.selenium;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class UserLogoutTests extends BaseTests {

	@Test(dependsOnMethods = "launchBrowser", priority = 0, enabled = false)
	@Parameters({ "browserType" })
	public void testLogoutSuccess(String browserType) {
		this.accessPage(this.getBaseUrl());

		WebElement userDropDown1 = driver.findElement(By.xpath("//a[@id='userNavDropDown']"));
		WebDriverWait wait1 = new WebDriverWait(driver, 30);
		wait1.until(ExpectedConditions.visibilityOf(userDropDown1));
		userDropDown1.click();

		WebElement loginDropItem = driver.findElement(By.xpath("//a[@routerlink='/user/login']"));
		WebDriverWait wait2 = new WebDriverWait(driver, 30);
		wait2.until(ExpectedConditions.visibilityOf(loginDropItem));
		loginDropItem.click();

		WebElement email = driver.findElement(By.cssSelector("input[id=email]"));
		WebDriverWait wait3 = new WebDriverWait(driver, 30);
		wait3.until(ExpectedConditions.visibilityOf(email));
		email.sendKeys("johndoe@gmail.com");

		WebElement password = driver.findElement(By.cssSelector("input[id=password]"));
		password.sendKeys("Pa$sw0rd");

		WebElement button = driver.findElement(By.xpath("//button[@type='submit']"));
		button.click();

		driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);

		WebElement userDropDown2 = driver.findElement(By.xpath("//a[@id='userNavDropDown']"));
		WebDriverWait wait4 = new WebDriverWait(driver, 30);
		wait4.until(ExpectedConditions.visibilityOf(userDropDown2));
		Assert.assertEquals(userDropDown2.getText(), "johndoe");
		userDropDown2.click();

		WebElement logoutDropItem = driver.findElement(By.xpath("//a[@routerlink='/user/logout']"));
		WebDriverWait wait5 = new WebDriverWait(driver, 30);
		wait5.until(ExpectedConditions.visibilityOf(logoutDropItem));
		logoutDropItem.click();

		WebElement signInButton = driver.findElement(By.xpath("//button[@type='submit']"));
		WebDriverWait wait6 = new WebDriverWait(driver, 30);
		wait6.until(ExpectedConditions.visibilityOf(signInButton));

		Assert.assertNotNull(signInButton);
		Assert.assertEquals(signInButton.getText(), "SIGN IN");

		WebElement userDropDown3 = driver.findElement(By.xpath("//a[@id='userNavDropDown']"));
		Assert.assertEquals(userDropDown3.getText(), "Guest");
	}

	@AfterTest
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}

}
