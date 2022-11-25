package launcher;

import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.ProfilesIni;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	public static WebDriver driver;
	public static String projectPath = System.getProperty("user.dir");
	public static Properties p;
	public static Properties mainProp;
	public static Properties childProp;
	public static Properties orProp;
	public static FileInputStream fis;

	public static void init() throws Exception {

		fis = new FileInputStream(projectPath + "\\src\\test\\resources\\environment.properties");
		mainProp = new Properties();
		mainProp.load(fis);
		String e = mainProp.getProperty("env");

		fis = new FileInputStream(projectPath + "\\src\\test\\resources\\" + e + ".properties");
		childProp = new Properties();
		childProp.load(fis);
		String property = childProp.getProperty("amazonurl");
		System.out.println(property);

		fis = new FileInputStream(projectPath + "\\src\\test\\resources\\data.properties");
		p = new Properties();
		p.load(fis);

		fis = new FileInputStream(projectPath + "\\src\\test\\resources\\or.properties");
		orProp = new Properties();
		orProp.load(fis);

	}

	public static void launch(String browserKey) {

		if (p.getProperty(browserKey).equalsIgnoreCase("chrome")) {

			WebDriverManager.chromedriver().setup();

			ChromeOptions option = new ChromeOptions();
			option.addArguments("user-data-di=C:\\Users\\Raja\\AppData\\Local\\Google\\Chrome\\User Data\\Profile 1");
			option.addArguments("--disable-notifications");
			option.addArguments("start-maximized");
			// option.addArguments("--ignore-certificate-errors-spki-list");
			// option.addArguments("proxy-server=https://192.168.10.1:9090");
			driver = new ChromeDriver(option);

		}

		else if (p.getProperty(browserKey).equalsIgnoreCase("firefox")) {

			WebDriverManager.firefoxdriver().setup();

			ProfilesIni p = new ProfilesIni();
			FirefoxProfile profile = p.getProfile("AprilFFProfile");

			FirefoxOptions option = new FirefoxOptions();
			option.setProfile(profile);

			// Handling Notifications

			profile.setPreference("dom.webnotifications.enabled", false);

			// Certificate error Handling

			profile.setAcceptUntrustedCertificates(true);
			profile.setAssumeUntrustedCertificateIssuer(false);

			// How to work with proxy settings
			profile.setPreference("network.proxy.type", 1);
			profile.setPreference("network.proxy.socks", "192.168.10.1");
			profile.setPreference("network.proxy.socks_port", 1744);

			driver = new FirefoxDriver(option);

		}

	}

	public static void navigateUrl(String urlKey) {

		driver.get(childProp.getProperty(urlKey));
	}

	public static void selectOption(String locatorKey, String option) {

		getElement(locatorKey).sendKeys(option);

	}

	public static void typeText(String locatorKey, String text) {

		getElement(locatorKey).sendKeys(text);

	}

	public static void clickElement(String locatorKey) {

		getElement(locatorKey).click();

	}

	public static WebElement getElement(String locatorKey) {

		WebElement element = null;

		// check for element present
		if (!isElementPresent(locatorKey)) {

			// report Failure
			System.out.println("Element is not present: " + locatorKey);
		}

		if (locatorKey.endsWith("_id")) {

			element = driver.findElement(By.id(orProp.getProperty(locatorKey)));
		} else if (locatorKey.endsWith("_name")) {

			element = driver.findElement(By.name(orProp.getProperty(locatorKey)));
		}

		else if (locatorKey.endsWith("_classname")) {

			element = driver.findElement(By.className(orProp.getProperty(locatorKey)));
		}

		else if (locatorKey.endsWith("_xpath")) {

			element = driver.findElement(By.xpath(orProp.getProperty(locatorKey)));
		}

		else if (locatorKey.endsWith("_css")) {

			element = driver.findElement(By.cssSelector(orProp.getProperty(locatorKey)));
		}

		else if (locatorKey.endsWith("_linktext")) {

			element = driver.findElement(By.linkText(orProp.getProperty(locatorKey)));
		}

		else if (locatorKey.endsWith("_partiallinktext")) {

			element = driver.findElement(By.partialLinkText(orProp.getProperty(locatorKey)));
		}
		return element;
	}

	public static boolean isElementPresent(String locatorKey) {

		System.out.println("Checking for Element Presence: " + locatorKey);
		WebDriverWait wait = new WebDriverWait(driver, 30);
		try {
			if (locatorKey.endsWith("_id")) {
				wait.until(ExpectedConditions.presenceOfElementLocated(By.id(orProp.getProperty(locatorKey))));
			}

			else if (locatorKey.endsWith("_name")) {
				wait.until(ExpectedConditions.presenceOfElementLocated(By.name(orProp.getProperty(locatorKey))));
			}

			else if (locatorKey.endsWith("_classname")) {
				wait.until(ExpectedConditions.presenceOfElementLocated(By.className(orProp.getProperty(locatorKey))));
			}

			else if (locatorKey.endsWith("_xpath")) {
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(orProp.getProperty(locatorKey))));
			}

			else if (locatorKey.endsWith("_css")) {
				wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(orProp.getProperty(locatorKey))));
			}

			else if (locatorKey.endsWith("_linktext")) {
				wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText(orProp.getProperty(locatorKey))));
			}

			else if (locatorKey.endsWith("_partiallinktext")) {
				wait.until(ExpectedConditions
						.presenceOfElementLocated(By.partialLinkText(orProp.getProperty(locatorKey))));
			}
		} catch (Exception e) {

			return false;

		}

		return true;
	}

}
