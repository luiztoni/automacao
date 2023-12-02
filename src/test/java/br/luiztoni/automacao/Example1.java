package br.luiztoni.automacao;

import io.cucumber.java.After;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Example1 {

	private final WebDriver driver = new ChromeDriver();

	@Given("I am on the Google search page")
	public void i_am_on_the_google_search_page() {
		driver.manage().window().maximize();
		driver.get("https://www.google.com");
	}

	@When("I search for {string}")
	public void i_search_for(String query) {
		WebElement element = driver.findElement(By.name("q"));
		element.sendKeys(query);
		element.submit();
	}

	@Then("the page title should start with {string}")
	public void the_page_title_should_start_with(String titleStartsWith) {
		new WebDriverWait(driver, Duration.ofSeconds(5)).until((ExpectedCondition<Boolean>) driver -> driver.getTitle().toLowerCase().startsWith(titleStartsWith));
	}

	@After()
	public void closeBrowser() {
		driver.quit();
	}
}