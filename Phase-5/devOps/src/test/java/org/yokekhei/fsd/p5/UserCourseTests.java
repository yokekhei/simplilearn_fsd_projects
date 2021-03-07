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
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DevOpsApplication.class, webEnvironment = WebEnvironment.DEFINED_PORT)
public class UserCourseTests extends BaseTests {

	@Test(dependsOnMethods = "launchBrowser", priority = 0)
	@Parameters({ "browserType" })
	public void testCourseCatalogSuccess(String browserType) {
		driver.get(this.getBaseUrl());

		WebElement email = driver.findElement(By.cssSelector("input[id=loginEmail]"));
		WebDriverWait wait1 = new WebDriverWait(driver, 30);
		wait1.until(ExpectedConditions.visibilityOf(email));
		email.sendKeys("johndoe@gmail.com");

		WebElement password = driver.findElement(By.cssSelector("input[id=loginPassword]"));
		password.sendKeys("Pa$sw0rd");

		WebElement button = driver.findElement(By.xpath("//button[@name='loginBtn']"));
		button.click();

		WebElement cardImgTop = driver
				.findElement(By.xpath("//img[@alt='Beginning C++ Programming - From Beginner to Beyond']"));
		WebDriverWait wait2 = new WebDriverWait(driver, 30);
		wait2.until(ExpectedConditions.visibilityOf(cardImgTop));

		List<WebElement> cardImgTops = driver.findElements(By.xpath("//img[@class='card-img-top']"));
		Assert.assertEquals(cardImgTops.size(), 2);

		List<WebElement> cardTitles = driver.findElements(By.xpath("//h6[@class='card-title']"));
		Assert.assertEquals(cardTitles.size(), 2);
		Assert.assertEquals(cardTitles.get(0).getText(), "The Complete Java Certification Course");
		Assert.assertEquals(cardTitles.get(1).getText(), "Beginning C++ Programming - From Beginner to Beyond");
	}

	@AfterTest
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}

}
