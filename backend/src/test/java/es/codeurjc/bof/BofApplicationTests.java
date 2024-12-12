package es.codeurjc.bof;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

class BofApplicationTests {

	WebDriver driver;

	@BeforeEach
	public void setupTest() {
		driver = new FirefoxDriver();
	}
/*
	@AfterEach
	public void teardown() {
		if (driver != null) {
			driver.quit();
		}
	}
*/
	@Test
	public void test() throws InterruptedException {
		driver.get("http://localhost:4200");
		WebElement searchButton = driver.findElement(By.id("searchButton"));
		searchButton.click();
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(30));
		WebElement link = wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Arroz de magro y setas")));
		link.click();
		WebElement text = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("product-name")));
		System.out.println(text.getText());
		assertTrue(text.getText().contains("Arroz de magro y setas"));
	}

	@Test
	public void test2() throws InterruptedException {
		driver.get("http://localhost:4200");
		WebElement singupButton = driver.findElement(By.id("signupButton"));
		WebElement loginButton = driver.findElement(By.id("loginButton"));

		singupButton.click();
		driver.findElement(By.id("username-input")).sendKeys("SeleniumTest");
		driver.findElement(By.id("email-input")).sendKeys("selenium01@test.com");
		driver.findElement(By.id("phoneNumber-input")).sendKeys("987654321");
		driver.findElement(By.id("password-input")).sendKeys("pepinillo");
		driver.findElement(By.className("sign-button")).click();

		loginButton.click();
		driver.findElement(By.id("username-input")).sendKeys("SeleniumTest");
		driver.findElement(By.id("password-input")).sendKeys("pepinillo");
		driver.findElement(By.className("login-button")).click();

		WebElement profileButton = driver.findElement(By.id("profileButton"));
		profileButton.click();
	}
}
