package org.yokekhei.fsd.capstone.selenium;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

public class UserLoginTests extends BaseTests {

	@Test(dependsOnMethods = "launchBrowser", priority = 0, enabled = false)
	public void testLoginInvalidCredentials() {
		this.accessPage(this.getBaseUrl());

		WebElement userDropDown = driver.findElement(By.xpath("//a[@id='userNavDropDown']"));
		WebDriverWait wait1 = new WebDriverWait(driver, 30);
		wait1.until(ExpectedConditions.visibilityOf(userDropDown));
		userDropDown.click();

		WebElement loginDropItem = driver.findElement(By.xpath("//a[@routerlink='/user/login']"));
		WebDriverWait wait2 = new WebDriverWait(driver, 30);
		wait2.until(ExpectedConditions.visibilityOf(loginDropItem));
		loginDropItem.click();

		WebElement email = driver.findElement(By.cssSelector("input[id=email]"));
		WebDriverWait wait3 = new WebDriverWait(driver, 30);
		wait3.until(ExpectedConditions.visibilityOf(email));
		email.sendKeys("johndoe@gmail.com");

		WebElement password = driver.findElement(By.cssSelector("input[id=password]"));
		password.sendKeys("Pa$sw0rd1");

		WebElement button = driver.findElement(By.xpath("//button[@type='submit']"));
		button.click();

		WebElement alert = driver.findElement(By.xpath("//h2[@id='swal2-title']"));
		WebDriverWait wait4 = new WebDriverWait(driver, 30);
		wait4.until(ExpectedConditions.visibilityOf(alert));

		Assert.assertTrue(alert.getText().contains("Invalid credentials"));

		WebElement alertButton = driver.findElement(By.xpath("//button[@class='swal2-confirm swal2-styled']"));
		alertButton.click();
	}

	@Test(dependsOnMethods = "launchBrowser", priority = 1)
	public void testLoginSuccess() {
		this.accessPage(this.getBaseUrl());

		WebElement userDropDown = driver.findElement(By.xpath("//a[@id='userNavDropDown']"));
		WebDriverWait wait1 = new WebDriverWait(driver, 30);
		wait1.until(ExpectedConditions.visibilityOf(userDropDown));
		Assert.assertEquals(userDropDown.getText(), "Guest");
		userDropDown.click();

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
	}

	@AfterTest
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}

}
