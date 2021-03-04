package org.yokekhei.fsd.p5;

import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DevOpsApplication.class, webEnvironment = WebEnvironment.DEFINED_PORT)
public class UserLoginTests extends BaseTests {

	@Test(dependsOnMethods = "launchBrowser", priority = 0)
	public void testLoginSuccess() {
		driver.get(this.getBaseUrl());

		WebElement email = driver.findElement(By.cssSelector("input[id=loginEmail]"));
		WebDriverWait wait1 = new WebDriverWait(driver, 30);
		wait1.until(ExpectedConditions.visibilityOf(email));
		email.sendKeys("johndoe@gmail.com");

		WebElement password = driver.findElement(By.cssSelector("input[id=loginPassword]"));
		password.sendKeys("Pa$sw0rd");

		WebElement button = driver.findElement(By.xpath("//button[@name='loginBtn']"));
		button.click();

		WebElement userDropDown = driver.findElement(By.xpath("//a[@id='dropdown01']"));
		WebDriverWait wait2 = new WebDriverWait(driver, 30);
		wait2.until(ExpectedConditions.visibilityOf(userDropDown));

		Assert.assertEquals(userDropDown.getText(), "johndoe");
	}

	@Test(dependsOnMethods = "launchBrowser", priority = 1)
	public void testLoginInvalidCredentials() {
		driver.get(this.getBaseUrl());

		WebElement email = driver.findElement(By.cssSelector("input[id=loginEmail]"));
		WebDriverWait wait1 = new WebDriverWait(driver, 30);
		wait1.until(ExpectedConditions.visibilityOf(email));
		email.sendKeys("johndoe@gmail.com");

		WebElement password = driver.findElement(By.cssSelector("input[id=loginPassword]"));
		password.sendKeys("Pa$sw0rd1");

		WebElement button = driver.findElement(By.xpath("//button[@name='loginBtn']"));
		button.click();

		WebElement alert = driver.findElement(By.xpath("//div[@id='failAlert']"));
		WebDriverWait wait2 = new WebDriverWait(driver, 30);
		wait2.until(ExpectedConditions.visibilityOf(alert));

		Assert.assertTrue(alert.getText().contains("Invalid credentials"));
	}

	@AfterTest
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}

}
