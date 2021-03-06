package org.yokekhei.fsd.p5;

import java.util.List;

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
public class AdminCourseTests extends BaseTests {

	@Test(dependsOnMethods = "launchBrowser", priority = 0)
	public void testListCourseSuccess() {
		driver.get(this.getBaseUrl() + "/admin");

		WebElement email = driver.findElement(By.cssSelector("input[id=adminEmail]"));
		WebDriverWait wait1 = new WebDriverWait(driver, 30);
		wait1.until(ExpectedConditions.visibilityOf(email));
		email.sendKeys("admin@lacademy.com");

		WebElement password = driver.findElement(By.cssSelector("input[id=adminPassword]"));
		password.sendKeys("Pa$sw0rd");

		WebElement button = driver.findElement(By.xpath("//button[@type='submit']"));
		button.click();

		WebElement table = driver.findElement(By.xpath("//table"));
		WebDriverWait wait2 = new WebDriverWait(driver, 30);
		wait2.until(ExpectedConditions.visibilityOf(table));

		Assert.assertNotNull(table);

		List<WebElement> rows = driver.findElements(By.xpath("//table/tbody/tr/td[1]"));
		Assert.assertEquals(rows.size(), 2);

		WebElement thc2 = driver.findElement(By.xpath("//table/thead/tr[1]/th[2]/span"));
		Assert.assertEquals(thc2.getText(), "Name");

		WebElement thc3 = driver.findElement(By.xpath("//table/thead/tr[1]/th[3]/span"));
		Assert.assertEquals(thc3.getText(), "Date Created");

		WebElement thc4 = driver.findElement(By.xpath("//table/thead/tr[1]/th[4]/span"));
		Assert.assertEquals(thc4.getText(), "Enabled/Disabled");

		WebElement r1c2 = driver.findElement(By.xpath("//table/tbody/tr[1]/td[2]"));
		Assert.assertEquals(r1c2.getText(), "The Complete Java Certification Course");

		WebElement r2c2 = driver.findElement(By.xpath("//table/tbody/tr[2]/td[2]"));
		Assert.assertEquals(r2c2.getText(), "Beginning C++ Programming - From Beginner to Beyond");
	}

	@AfterTest
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}

}
