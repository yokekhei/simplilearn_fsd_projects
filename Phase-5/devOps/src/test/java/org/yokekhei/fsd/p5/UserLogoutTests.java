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
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DevOpsApplication.class, webEnvironment = WebEnvironment.DEFINED_PORT)
public class UserLogoutTests extends BaseTests {

	@Test(dependsOnMethods = "launchBrowser", priority = 0)
	@Parameters({ "browserType" })
	public void testLogoutSuccess(String browserType) {
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
		userDropDown.click();

		WebElement logoutDropItem = driver.findElement(By.xpath("//div//ul//li//div//a[@href='/logout']"));
		logoutDropItem.click();

		WebElement signInButton = driver.findElement(By.xpath("//button[@name='loginBtn']"));
		WebDriverWait wait3 = new WebDriverWait(driver, 30);
		wait3.until(ExpectedConditions.visibilityOf(signInButton));

		Assert.assertNotNull(signInButton);
		Assert.assertEquals(signInButton.getText(), "Sign in");
	}

	@AfterTest
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}

}
