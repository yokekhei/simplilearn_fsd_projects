package org.yokekhei.fsd.p5;

import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;
import org.testng.annotations.Test;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DevOpsApplication.class, webEnvironment = WebEnvironment.DEFINED_PORT)
public class UserLoginTests extends BaseTests {

	@Test(dependsOnMethods = "launchBrowser", priority = 0)
	public void testLoginSuccess() {
		WebElement email = driver.findElement(By.cssSelector("input[name=email]"));
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(email));

		email.sendKeys("johndoe");
		WebElement password = driver.findElement(By.cssSelector("input[name=password]"));
		password.sendKeys("Pa$sw0rd");
	}

	@Test(dependsOnMethods = "launchBrowser", priority = 1)
	public void testLoginFail() {
		WebElement email = driver.findElement(By.cssSelector("input[name=email]"));
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(email));

		email.sendKeys("dummy");
		WebElement password = driver.findElement(By.cssSelector("input[name=password]"));
		password.sendKeys("dummy12345");
	}

}
