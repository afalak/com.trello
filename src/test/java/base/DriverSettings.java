package base;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverSettings {
  /**
   * Overloaded browser setup with browser name and time-out being sent as
   * parameter
   * 
   * @param browserName      : name of the browser (like: firefox, ie, chrome)
   *                         case insensitive
   * @param timeUnitInSecond : Timeout second for implicit time-out
   */
  public void setUpDriver(String browserName, int timeUnitInSecond) {
    System.setProperty("webdriver.chrome.whitelistedIps", "127.0.0.1");
    ChromeOptions option = new ChromeOptions();
    HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
    chromePrefs.put("profile.default_content_settings.popups", 0);
    option.setExperimentalOption("prefs", chromePrefs);
    option.setAcceptInsecureCerts(false); // never set this to true
    option.setHeadless(true); // set chrome as Headless
    option.addArguments("--disable-dev-shm-usage"); // as we are root
    option.addArguments("--no-sandbox"); // as we are running as root
    option.addArguments("--silent");
    option.addArguments("--port=4444");
    ChromeDriverService svc = new ChromeDriverService.Builder().usingPort(4444).build();
    Driver.driver = new ChromeDriver(svc, option);
    setWait(timeUnitInSecond);
    Driver.driver.manage().window().maximize();
  }

  public static void setWait(int timeUnitInSecond) {
    Driver.driver.manage().timeouts().implicitlyWait(timeUnitInSecond, TimeUnit.SECONDS);
  }

  /**
   * Closes the driver, deletes all cookies
   */
  public void closeDriver() {
    // Driver.driver.close();
    Driver.driver.quit();
  }

  public void closeWindow() {
    Driver.driver.close();
  }
}
