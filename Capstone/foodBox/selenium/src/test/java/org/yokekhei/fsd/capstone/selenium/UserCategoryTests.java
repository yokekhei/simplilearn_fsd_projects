package org.yokekhei.fsd.capstone.selenium;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class UserCategoryTests extends BaseTests {

	@Test(dependsOnMethods = "launchBrowser", priority = 0)
	@Parameters({ "browserType" })
	public void testCategoryCatalogSuccess(String browserType) {
		this.accessPage(this.getBaseUrl());

		WebElement cardImgTop = driver.findElement(By.xpath("//img[@alt='Chinese']"));
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(cardImgTop));

		List<WebElement> cardImgTops = driver.findElements(By.xpath("//img[@class='card-img-top']"));
		Assert.assertEquals(cardImgTops.size(), 4);

		List<WebElement> cardTitles = driver.findElements(By.xpath("//h5[@class='card-title text-center']"));
		Assert.assertEquals(cardTitles.size(), 4);
		Assert.assertEquals(cardTitles.get(0).getText(), "Chinese");
	}

	@AfterTest
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}

}
