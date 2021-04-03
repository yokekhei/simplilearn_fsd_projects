package org.yokekhei.fsd.capstone.selenium;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class UserRegisterTests extends BaseTests {

	FileInputStream fis;
	private XSSFWorkbook wb;
	private XSSFSheet sheet;

	@Test(dependsOnMethods = "launchBrowser", priority = 0)
	@Parameters({ "browserType" })
	public void testRegisterSuccess(String browserType) {
		this.accessPage(this.getBaseUrl());

		String userNameData = getUserName(browserType);
		Assert.assertNotNull(userNameData);

		String emailData = getEmail(browserType);
		Assert.assertNotNull(emailData);

		String addressLine1Data = getAddressLine1(browserType);
		Assert.assertNotNull(addressLine1Data);

		String addressLine2Data = getAddressLine2(browserType);
		Assert.assertNotNull(addressLine2Data);

		String cityData = getCity(browserType);
		Assert.assertNotNull(cityData);

		String postcodeData = getPostcode(browserType);
		Assert.assertNotNull(postcodeData);

		String phoneData = getPhone(browserType);
		Assert.assertNotNull(phoneData);

		WebElement userDropDown = driver.findElement(By.xpath("//a[@id='userNavDropDown']"));
		WebDriverWait wait1 = new WebDriverWait(driver, 30);
		wait1.until(ExpectedConditions.visibilityOf(userDropDown));
		userDropDown.click();

		WebElement registerDropItem = driver.findElement(By.xpath("//a[@routerlink='/user/register']"));
		WebDriverWait wait2 = new WebDriverWait(driver, 30);
		wait2.until(ExpectedConditions.visibilityOf(registerDropItem));
		registerDropItem.click();

		WebElement userName = driver.findElement(By.cssSelector("input[id=username]"));
		userName.sendKeys(userNameData);

		WebElement email = driver.findElement(By.cssSelector("input[id=email]"));
		email.sendKeys(emailData);

		WebElement password = driver.findElement(By.cssSelector("input[id=password]"));
		password.sendKeys("Pa$sw0rd");

		WebElement addressLine1 = driver.findElement(By.cssSelector("input[id=addressLine1]"));
		addressLine1.sendKeys(addressLine1Data);

		WebElement addressLine2 = driver.findElement(By.cssSelector("input[id=addressLine2]"));
		addressLine2.sendKeys(addressLine2Data);

		WebElement city = driver.findElement(By.cssSelector("input[id=city]"));
		city.sendKeys(cityData);

		WebElement postcode = driver.findElement(By.cssSelector("input[id=postcode]"));
		postcode.sendKeys(postcodeData);

		WebElement phone = driver.findElement(By.cssSelector("input[id=phone]"));
		phone.sendKeys(phoneData);

		WebElement button = driver.findElement(By.xpath("//button[@type='submit']"));
		button.click();

		driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);

		WebElement alert = driver.findElement(By.xpath("//div[@id='successAlert']//small"));
		WebDriverWait wait3 = new WebDriverWait(driver, 30);
		wait3.until(ExpectedConditions.visibilityOf(alert));

		Assert.assertTrue(alert.getText().contains("Congratulations! You have signed up successfully."));
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

	private String getAddressLine1(String browserType) {
		String addressLine1 = null;

		try {
			fis = new FileInputStream(TestUtils.TEST_DATA_FILE_PATH);
			wb = new XSSFWorkbook(fis);
			sheet = wb.getSheet("user-register");

			for (int i = 0; i <= sheet.getLastRowNum(); i++) {
				String bwsrType = sheet.getRow(i).getCell(0).getStringCellValue();

				if (browserType.toLowerCase().contains(bwsrType)) {
					addressLine1 = sheet.getRow(i).getCell(3).getStringCellValue();
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

		return addressLine1;
	}

	private String getAddressLine2(String browserType) {
		String addressLine2 = null;

		try {
			fis = new FileInputStream(TestUtils.TEST_DATA_FILE_PATH);
			wb = new XSSFWorkbook(fis);
			sheet = wb.getSheet("user-register");

			for (int i = 0; i <= sheet.getLastRowNum(); i++) {
				String bwsrType = sheet.getRow(i).getCell(0).getStringCellValue();

				if (browserType.toLowerCase().contains(bwsrType)) {
					addressLine2 = sheet.getRow(i).getCell(4).getStringCellValue();
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

		return addressLine2;
	}

	private String getCity(String browserType) {
		String city = null;

		try {
			fis = new FileInputStream(TestUtils.TEST_DATA_FILE_PATH);
			wb = new XSSFWorkbook(fis);
			sheet = wb.getSheet("user-register");

			for (int i = 0; i <= sheet.getLastRowNum(); i++) {
				String bwsrType = sheet.getRow(i).getCell(0).getStringCellValue();

				if (browserType.toLowerCase().contains(bwsrType)) {
					city = sheet.getRow(i).getCell(5).getStringCellValue();
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

		return city;
	}

	private String getPostcode(String browserType) {
		String postcode = null;

		try {
			fis = new FileInputStream(TestUtils.TEST_DATA_FILE_PATH);
			wb = new XSSFWorkbook(fis);
			sheet = wb.getSheet("user-register");

			for (int i = 0; i <= sheet.getLastRowNum(); i++) {
				String bwsrType = sheet.getRow(i).getCell(0).getStringCellValue();

				if (browserType.toLowerCase().contains(bwsrType)) {
					postcode = Double.toString(sheet.getRow(i).getCell(6).getNumericCellValue());
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

		return postcode;
	}

	private String getPhone(String browserType) {
		String phone = null;

		try {
			fis = new FileInputStream(TestUtils.TEST_DATA_FILE_PATH);
			wb = new XSSFWorkbook(fis);
			sheet = wb.getSheet("user-register");

			for (int i = 0; i <= sheet.getLastRowNum(); i++) {
				String bwsrType = sheet.getRow(i).getCell(0).getStringCellValue();

				if (browserType.toLowerCase().contains(bwsrType)) {
					phone = sheet.getRow(i).getCell(7).getStringCellValue();
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

		return phone;
	}

}
