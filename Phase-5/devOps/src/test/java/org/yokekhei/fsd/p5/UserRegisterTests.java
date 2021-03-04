package org.yokekhei.fsd.p5;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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
public class UserRegisterTests extends BaseTests {

	FileInputStream fis;
	private XSSFWorkbook wb;
	private XSSFSheet sheet;

	@Test(dependsOnMethods = "launchBrowser", priority = 0)
	@Parameters({ "browserType" })
	public void testRegisterSuccess(String browserType) {
		driver.get(this.getBaseUrl());

		String userNameData = getUserName(browserType);
		Assert.assertNotNull(userNameData);

		String emailData = getEmail(browserType);
		Assert.assertNotNull(userNameData);

		WebElement signUpTab = driver.findElement(By.xpath("//a[@id='register-tab']"));
		WebDriverWait wait1 = new WebDriverWait(driver, 30);
		wait1.until(ExpectedConditions.visibilityOf(signUpTab));
		signUpTab.click();

		WebElement userName = driver.findElement(By.cssSelector("input[id=registerUserName]"));
		userName.sendKeys(userNameData);

		WebElement email = driver.findElement(By.cssSelector("input[id=registerEmail]"));
		email.sendKeys(emailData);

		WebElement password = driver.findElement(By.cssSelector("input[id=registerPassword]"));
		password.sendKeys("Pa$sw0rd");

		WebElement button = driver.findElement(By.xpath("//button[@name='registerBtn']"));
		button.click();

		WebElement alert = driver.findElement(By.xpath("//div[@id='successAlert']"));
		WebDriverWait wait2 = new WebDriverWait(driver, 30);
		wait2.until(ExpectedConditions.visibilityOf(alert));

		Assert.assertTrue(alert.getText().contains("Congratulations! You have signed up successfully."));
	}

	@Test(dependsOnMethods = "launchBrowser", priority = 1)
	public void testRegisterPasswordFail() {
		driver.get(this.getBaseUrl());

		WebElement signUpTab = driver.findElement(By.xpath("//a[@id='register-tab']"));
		WebDriverWait wait1 = new WebDriverWait(driver, 30);
		wait1.until(ExpectedConditions.visibilityOf(signUpTab));
		signUpTab.click();

		WebElement userName = driver.findElement(By.cssSelector("input[id=registerUserName]"));
		userName.sendKeys("marry");

		WebElement email = driver.findElement(By.cssSelector("input[id=registerEmail]"));
		email.sendKeys("marry@gmail.com");

		WebElement password = driver.findElement(By.cssSelector("input[id=registerPassword]"));
		password.sendKeys("dummy");

		WebElement button = driver.findElement(By.xpath("//button[@name='registerBtn']"));
		button.click();

		WebElement alert = driver.findElement(By.xpath("//div[@id='failAlert']"));
		WebDriverWait wait2 = new WebDriverWait(driver, 30);
		wait2.until(ExpectedConditions.visibilityOf(alert));

		Assert.assertTrue(alert.getText().contains("Password must have at least one upper case"));
	}

	@AfterTest
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}

	private String getUserName(String browserType) {
		String userName = null;

		try {
			fis = new FileInputStream(TestUtils.TEST_DATA_FILE_PATH);
			wb = new XSSFWorkbook(fis);
			sheet = wb.getSheet("user-register");

			for (int i = 0; i <= sheet.getLastRowNum(); i++) {
				String bwsrType = sheet.getRow(i).getCell(0).getStringCellValue();

				if (browserType.toLowerCase().contains(bwsrType)) {
					userName = sheet.getRow(i).getCell(1).getStringCellValue();
					break;
				}
			}
		} catch (IOException e) {
		} finally {
			try {
				wb.close();
				fis.close();
			} catch (IOException e) {
			} finally {
				wb = null;
				fis = null;
			}
		}

		return userName;
	}

	private String getEmail(String browserType) {
		String email = null;

		try {
			fis = new FileInputStream(TestUtils.TEST_DATA_FILE_PATH);
			wb = new XSSFWorkbook(fis);
			sheet = wb.getSheet("user-register");

			for (int i = 0; i <= sheet.getLastRowNum(); i++) {
				String bwsrType = sheet.getRow(i).getCell(0).getStringCellValue();

				if (browserType.toLowerCase().contains(bwsrType)) {
					email = sheet.getRow(i).getCell(2).getStringCellValue();
					break;
				}
			}
		} catch (IOException e) {
		} finally {
			try {
				wb.close();
				fis.close();
			} catch (IOException e) {
			} finally {
				wb = null;
				fis = null;
			}
		}

		return email;
	}

}
