package es.codeurjc.bof;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

class BofApplicationTests {

	WebDriver driver;

	@BeforeEach
	public void setupTest() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless");
		driver = new ChromeDriver(options);
	}

	@AfterEach
	public void teardown() {
		if (driver != null) {
			driver.quit();
		}
	}

	@Test
	public void anonymousTest() throws InterruptedException {
		driver.get("http://localhost:4200");
		WebElement searchButton = driver.findElement(By.id("searchButton"));
		searchButton.click();
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(30));
		WebElement link = wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Arroz de magro y setas")));
		link.click();
		WebElement text = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("product-name")));
		assertTrue(text.getText().contains("Arroz de magro y setas"));
	}

	@Test
	public void userTest() throws InterruptedException {
		driver.get("http://localhost:4200");
		WebElement singupButton = driver.findElement(By.id("signupButton"));
		WebElement loginButton = driver.findElement(By.id("loginButton"));
		WebElement searchButton = driver.findElement(By.id("searchButton"));

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
		
		searchButton.click();
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(30));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Arroz de magro y setas"))).click();
		driver.findElement(By.className("add-button")).click();
		driver.findElement(By.id("date-input")).sendKeys("2024-12-16");
		driver.findElement(By.className("accept-button")).click();

		WebElement profileButton = driver.findElement(By.id("profileButton"));
		profileButton.click();
	}

	@Test
	public void adminTest() throws InterruptedException {
		driver.get("http://localhost:4200");
		WebElement loginButton = driver.findElement(By.id("login-Button"));
		WebElement searchButton = driver.findElement(By.id("search-Button"));
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(30));

		loginButton.click();
		driver.findElement(By.id("username-input")).sendKeys("admin");
		driver.findElement(By.id("password-input")).sendKeys("pass");
		driver.findElement(By.className("login-button")).click();

		WebElement newProductButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("newProductButton")));
		newProductButton.click();
		driver.findElement(By.id("name-input")).sendKeys("Selenium Product");
		driver.findElement(By.id("info-input")).sendKeys("Ingrediente 1, Ingrediente 2, .. ,Ingrediente N");
		driver.findElement(By.id("weight-input")).sendKeys("450");
		driver.findElement(By.id("price-input")).sendKeys("10");
		driver.findElement(By.id("cal-input")).sendKeys("500");
		driver.findElement(By.id("protein-input")).sendKeys("25");
		driver.findElement(By.id("fat-input")).sendKeys("10");
		driver.findElement(By.id("carbo-input")).sendKeys("80");
		driver.findElement(By.className("accept")).click();

		searchButton.click();
		WebElement link = wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Selenium Product")));
		link.click();
		WebElement text = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("product-name")));
		assertTrue(text.getText().contains("Selenium Product"));

		searchButton.click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("delete-Selenium Product"))).click();
		wait.until(ExpectedConditions.alertIsPresent());
		Alert alert = driver.switchTo().alert();
		alert.accept();

		searchButton.click();
		List<WebElement> products = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("product-name")));
		Boolean textFound = products.stream().anyMatch(product -> product.getText().contains("Selenium Product"));
		assertFalse(textFound);
	}
}
