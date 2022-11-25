package launcher;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserLauncher2 {

	public static void main(String[] args) {

		WebDriver driver;

		WebDriverManager.chromedriver().setup();
		// System.setProperty("webdriver.chrome.driver","C:/Users/Raja/Downloads/sel/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://www.flipkart.com");

		WebDriverManager.firefoxdriver().setup();
		// System.setProperty("webdriver.gecko.driver","C:/Users/Raja/Downloads/sel/geckodriver.exe");
		driver = new FirefoxDriver();
		driver.navigate().to("https://www.bestbuy.com");

	}

}
