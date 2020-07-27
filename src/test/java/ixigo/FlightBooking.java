package ixigo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class FlightBooking {
	WebDriver driver;

	@Test(priority = 1)
	public void searchFlight() {
		try {

			driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
			driver.findElement(By.xpath("(//input[@type='text'])[1]")).click();

			List<WebElement> fromCities = driver.findElements(By.xpath("//div[@class='city']")); //
			Reporter.log("List of Cities", true);
			driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
			String from = "BLR - Bengaluru, India";
			for (WebElement city : fromCities) {
				if (city.getText().equalsIgnoreCase(from)) {
					driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
					city.click();
					Reporter.log("from city selected", true);
					break;
				}

			}

			driver.findElement(By.xpath("(//input[@type='text'])[2]")).click();
			List<WebElement> toCities = driver.findElements(By.xpath("//div[@class='city']"));
			String To = "MAA - Chennai, India";
			for (WebElement toCity : toCities) {
				if (toCity.getText().equalsIgnoreCase(To)) {
					toCity.click();
					Reporter.log("To City selected", true);
					break;
				}

			}

			// select travel date
			driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
			driver.findElement(By.xpath("(//input[@type='text'])[3]")).click();
			driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
			driver.findElement(By.xpath("(//button[@type='button'])[2]")).click();
			driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
			driver.findElement(By.xpath("(//button[@type='button'])[2]")).click();
			driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
			List<WebElement> travelDates = driver.findElements(By.cssSelector("div[class*='day']"));
			String travelDate = "3";
			for (WebElement date : travelDates) {
				if (date.getText().equalsIgnoreCase(String.valueOf(travelDate))) {
					date.click();
					Reporter.log("Travel date selected", true);
					break;
				}

			}

			// Return Travel Date

			driver.findElement(By.xpath("(//input[@type='text'])[4]")).click();
			driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
			driver.findElement(By.xpath("(//button[@type='button'])[4]")).click();
			driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
			driver.findElement(By.xpath("(//button[@type='button'])[4]")).click();
			driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
			List<WebElement> returnDates = driver.findElements(By.cssSelector("div[class*='day']"));
			String returnDate = "30";
			for (WebElement returndate : returnDates) {
				if (returndate.getText().equalsIgnoreCase(returnDate)) {
					returndate.click();
					Reporter.log("Return date selected", true);
					break;
				}

			}
		} catch (NoSuchElementException ne) {
			System.out.println(ne.getMessage());
		}

	}

	@Test(priority = 2)
	public void travellerDetails() {

		// Traveller Class
		try {
			driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
			driver.findElement(By.xpath("//div[contains(text(),'Travellers | Class')]")).click();
			Reporter.log("Travellers details", true);

			List<WebElement> adults = driver.findElements(By.cssSelector("span[data-val]"));
			String NoOfadult = "2";

			for (WebElement adult : adults) {
				if (adult.getText().equalsIgnoreCase(NoOfadult)) {
					adult.click();
					Reporter.log("Adults selected", true);
					break;
				}

			}

			driver.findElement(By.xpath("(//div[@class='u-ib items u-v-align-middle']/span)[11]")).click();
			driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
			driver.findElement(
					By.xpath("//div[@class='orgn u-ib u-v-align-bottom u-text-left']/following-sibling::div[5]"))
					.click();
			Reporter.log("Child selected", true);

		} catch (NoSuchElementException ne1) {
			System.out.println(ne1.getMessage());
		}
	}

	@Test(priority = 3)
	public void applyFilter() {

		// more filters
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES);
		WebDriverWait wait = new WebDriverWait(driver, 300);

		wait.until(ExpectedConditions
				.visibilityOf(driver.findElement(By.xpath("//div[contains(text(),'MORE FILTERS')]"))));

		driver.findElement(By.xpath("//div[contains(text(),'MORE FILTERS')]")).click();
		Reporter.log("Clicked on more filter", true);

		// Stops

		List<WebElement> stops = driver.findElements(By.cssSelector("div[class*='stop-info']"));
		for (WebElement stop : stops) {
			System.out.println(stop.getText());
			if (stop.getText().equalsIgnoreCase("Non stop")) {
				driver.findElement(By.xpath("(//span[@class='ixi-icon-tick check-icon'])[1]")).click();
				Reporter.log("How many stopage", true);
				break;
			}
		}

		// timings

		List<WebElement> timings = driver.findElements(By.cssSelector("div[class='lbl']"));
		for (WebElement timing : timings) {
			if (timing.getText().equalsIgnoreCase("Morning")) {
				driver.findElement(By.xpath("(//button[@class='c-btn u-link secondary enabled'])[2]")).click();
				Reporter.log("Timing filter", true);
				break;
			}
		}

		// Airlines
		List<WebElement> airlines = driver.findElements(By.xpath("//div[@class='arln-info']/div"));
		for (WebElement airline : airlines) {
			if (airline.getText().equalsIgnoreCase("IndiGo")) {
				driver.findElement(By.xpath("(//span[@class='ixi-icon-tick check-icon'])[5]")).click();
				Reporter.log("Airlines selected", true);
				break;
			}
		}

		// Apply filters
		driver.findElement(By.xpath("//button[contains(text(),'APPLY')]")).click();
		Reporter.log("clicked on Apply filter", true);

	}

	@Test(priority = 4)
	public void selectFlight() {

		try {

			List<WebElement> prices = driver.findElements(By.cssSelector("div[class='price']"));
			ArrayList<Integer> arrprices = new ArrayList<Integer>();
			for (WebElement price : prices) {
				int fromprice = Integer.parseInt(price.getText().split("₹")[0]);
				arrprices.add(fromprice);
			}
			Reporter.log("Lowest price", true);
			System.out.println(Collections.min(arrprices));
		}

		catch (NumberFormatException nfe) {
			System.out.println(nfe.getMessage());
		}

		// fetching pnr number

		WebElement pnrs = driver.findElement(By.xpath("(//div[@class='u-text-ellipsis'])[7]"));
		System.out.println(pnrs.getText());
		String pnrnumber = pnrs.getText().split("  ")[1];
		;
		Reporter.log("PNR Number", true);
		System.out.println(pnrnumber);

		// trip date

		System.out.println(driver.findElement(By.xpath("(//span[@class='trp-date'])[2]")).getText());
		Reporter.log("Trip Date", true);
		

	}

	@BeforeClass
	public void launchApplication() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\vio\\RiturajQA\\Driver\\chromedriver.exe");

		driver = new ChromeDriver();
		Reporter.log("driver initiated", true);
		driver.get("https://www.ixigo.com/");
		Reporter.log("Application opened", true);
		driver.manage().window().maximize();
		Reporter.log("Maximize the browser", true);
	}

	@AfterClass
	public void tearDown() throws IOException {
		driver.quit();
		Reporter.log("quit the browser", true);
		Runtime.getRuntime().exec("taskkill /F /IM ChromeDriver.exe");
		Reporter.log("Kill the driver.exe", true);
	}

}
