package launcher;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BrowserLauncher {

	public static void main(String[] args) {

		// WebDriverManager.chromedriver().setup();
		System.setProperty("webdriver.chrome.driver", "C:/Users/Raja/Downloads/sel/chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		// WebDriverManager.firefoxdriver().setup();
		System.setProperty("webdriver.gecko.driver", "C:/Users/Raja/Downloads/sel/geckodriver.exe");
		WebDriver driver1 = new FirefoxDriver();

	}

}
