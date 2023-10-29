package br.luiztoni.automacao;

import io.cucumber.java.After;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;
import java.util.Objects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Example2 {

	private final WebDriver driver = new ChromeDriver();

	@Given("I am on the Google page")
	public void i_am_on_the_google_search_page() {
		driver.get("https://www.google.com");
		driver.manage().window().maximize();
	}

	@When("I search for page {string}")
	public void i_search_for_page(String query) {
		WebElement searchBox = driver.findElement(By.name("q"));
		searchBox.sendKeys(query);
		searchBox.submit();
	}

	@And("I click in Wikipedia link")
	public void i_click_in_wikipedia_link() {
		new WebDriverWait(driver, Duration.ofSeconds(5)).until((ExpectedCondition<Boolean>) driver -> {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			String clickInFristLink = "document.querySelector(\"a[jsname='UWckNb']\").click();";
			js.executeScript(clickInFristLink);
			Objects.nonNull(driver.getTitle().toLowerCase());
			return true;
		});
	}

	@And("I search for city {string}")
	public void i_search_for_city(String query) {
		WebElement searchBox = driver.findElement(By.name("search"));
		searchBox.sendKeys(query);
		searchBox.submit();
	}

	@Then("the page title should start with word {string}")
	public void the_page_title_should_start_with(String titleStartsWith) {
		WebElement resultTitle = driver.findElement(By.id("firstHeading"));
		String titleText = resultTitle.getText();
		boolean titleContainsIrece = titleText.contains(titleStartsWith);
		boolean pageContainsJpg = driver.getPageSource().contains(".jpg");
		assertEquals(true, titleContainsIrece);
		assertTrue(pageContainsJpg);
        WebElement el = driver.findElement(By.id("Referências"));
        new Actions(driver)
                .scrollToElement(el)
                .perform();		
	}

	@After()
	public void closeBrowser() {
		driver.quit();
	}
}
