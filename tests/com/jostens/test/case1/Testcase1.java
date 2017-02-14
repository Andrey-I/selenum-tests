package com.jostens.test.case1;

import static org.junit.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class Testcase1 {
	private static WebDriver driver;
	private static String baseUrl;
	
	@BeforeClass
	public static void  setUpBeforeClass() throws Exception {
		String osName = System.getProperty("os.name").toLowerCase();
		if(osName.startsWith("linux")) {
			System.setProperty("webdriver.chrome.driver", "chrome-driver/linux64/chromedriver");
		} else if (osName.startsWith("windows")) {
			System.setProperty("webdriver.chrome.driver", "chrome-driver/win32/chromedriver.exe");
		}		
		driver = new ChromeDriver();
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	    driver.manage().window().maximize();
		baseUrl = "http://demowww.jostens.com/";		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		// driver.quit();
	}

	@Before
	public void setUp() throws Exception {
		System.out.println("Testcase1.setUp()");
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("Testcase1.tearDown()");
	}

	@Test
	public void test1() {
		System.out.println("Testcase1.test1()");
		driver.get(baseUrl);
		gotoSchoolPage("Edina High School");

		WebElement apparelLink = driver.findElement(By.linkText("Class of Apparel"));
		apparelLink.click();

		WebElement classOfPulloverHoodi = driver.findElement(By.cssSelector("span[title='Class of Pullover Hoodie']"));
		classOfPulloverHoodi.click();

//		WebElement sizeElement =  driver.findElement(By.cssSelector("option[data-jos-option-osr="XL"]'));
//		sizeElement.click();
	    new Select(driver.findElement(By.cssSelector("select.jos-with-hint"))).selectByVisibleText("Small");
	    
	    WebElement addToCartButton = driver.findElement(By.linkText("Add to Cart"));
		addToCartButton.click();
		viewCartButton().click();

		WebElement firstname = driver.findElement(By.cssSelector("div.recipientFirstName input"));
		firstname.sendKeys("TestFirstName");
		WebElement lastname = driver.findElement(By.cssSelector("div.recipientLastName input"));
		lastname.sendKeys("TestLastName");

		continueShoppingButton().click();

		apparelLink = driver.findElement(By.linkText("Class of Apparel"));
		apparelLink.click();

		WebElement SeniorJersey = driver.findElement(By.cssSelector("span[title='Senior Jersey']"));
		SeniorJersey.click();
	    new Select(driver.findElement(By.cssSelector("select.jos-with-hint"))).selectByVisibleText("Large");
		addToCartButton = driver.findElement(By.linkText("Add to Cart"));
		addToCartButton.click();
		viewCartButton().click();


		checkoutButton().click();

		guestButton().click();

	}

	private void gotoSchoolPage(String schoolName) {
		driver.findElement(By.id("findSchool")).click();
		
		WebElement input = driver.findElement(By.id("customer-search-modal-name"));		
		input.sendKeys(schoolName);
		WebElement school = driver.findElement(By.cssSelector("a[title='"+ schoolName + "']"));
		school.click();
	}
	
	private WebElement viewCartButton() {
		return driver.findElement(By.linkText("View Cart"));	
	}
	
	private WebElement continueShoppingButton() {
		return driver.findElement(By.cssSelector("a.continueShoppingButton"));
	}
	
	private WebElement guestButton() {
		return driver.findElement(By.cssSelector("a.guestCheckoutButton"));
	}
	private WebElement checkoutButton() {
		return driver.findElement(By.cssSelector("a.checkoutButton"));
	}
}
