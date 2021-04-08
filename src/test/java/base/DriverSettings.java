package base;

import java.io.File;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

//import org.openqa.selenium.Dimension;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import utils.FileReference;


public class DriverSettings
{
	/**
	 * Overloaded browser setup with browser name and time-out being
	 * sent as parameter
	 * @param browserName : name of the browser (like: firefox, ie, chrome) case insensitive
	 * @param timeUnitInSecond : Timeout second for implicit time-out
	 */
	public void setUpDriver(String browserName, int timeUnitInSecond)
	{
		final FirefoxOptions options = new FirefoxOptions();
		switch(browserName.trim().toLowerCase())
		{
		case "firefox" :
			System.setProperty("webdriver.gecko.driver", FileReference.driversFilePath+File.separator+"geckodriver.exe");
			System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE, "true");
			options.addPreference("browser.popups.showPopupBlocker", false);
			options.addPreference("security.sandbox.content.level", 5);
			Driver.driver = new FirefoxDriver(options);
			break;

		case "chrome" :
      // no need as we are installing the 2
			//System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
			HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
			chromePrefs.put("profile.default_content_settings.popups", 0);
			ChromeOptions option = new ChromeOptions();
			option.setExperimentalOption("prefs", chromePrefs);
			option.setAcceptInsecureCerts(false); // never set this to true
			// set chrome as Headless
			option.setHeadless(true);//option.addArguments("--headless"); //or set this
      option.addArguments("--no-sandbox"); // as we are running as root
      option.addArguments("--disable-dev-shm-usage"); // as we are running as root
			Driver.driver = new ChromeDriver(option);
			break;

		default : 
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "\\geckodriver.exe");
			System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE, "true");
			options.addPreference("browser.popups.showPopupBlocker", false);
			options.addPreference("security.sandbox.content.level", 5);
			Driver.driver = new FirefoxDriver(options);
			break;
		}
		setWait(timeUnitInSecond);
		setBrowserAtMaxSize();
	}

	/**
	 * Sets the implicit time out as parameter
	 * @param timeUnitInSecond : Timeout second for implicit time-out
	 */
	protected static void setWait(int timeUnitInSecond)
	{
		Driver.driver.manage().timeouts().implicitlyWait(timeUnitInSecond, TimeUnit.SECONDS);
	}

	/**
	 * Sets the browser at maximum size of the screen
	 */
	protected void setBrowserAtMaxSize()
	{
		Driver.driver.manage().window().maximize();
	}

	/**
	 * Closes the driver, deletes all cookies
	 */
	public void closeDriver()
	{
//		Driver.driver.close();
		Driver.driver.quit();
	}

	public void closeWindow()
	{
		Driver.driver.close();
	}
}
