package steps;

import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import base.BaseUtil;
import base.Browser;
import base.Driver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.qameta.allure.Attachment;
import utils.HelperUtils;

public class Hook extends BaseUtil {

  public static Browser browser;

  @Before
  public void InitilizaBrowser(Scenario scenario) throws Throwable {
    String scenarioID = scenario.toString();
    System.out.println("\n\n");
    System.out.println("****************************************************");
    System.out.println("Starting execution of Scenario:-   " + scenario.getName());
    System.out.println("****************************************************");
    System.out.println("\n\n");
    if (scenarioID.contains("loginPage")) {
      browser = new Browser();
    } else {
      if (Driver.driver == null || Driver.driver.toString() == null) {
        browser = new Browser();

      } else if (!Browser.isBrowserReachable()) {
        browser = new Browser();
      }
    }
  }

  @After
  public void CloseBrowser(Scenario scenario) throws IOException, InterruptedException {
    String scenarioID = scenario.toString();
    if (scenarioID.contains("loginPage")) {
      browser.close();
    } else {
      if (!HelperUtils.landToBasePage(browser)) {
        browser.close();
      }

    }
  }

  @Attachment(value = "Screenshot of {0}", type = "image/png", fileExtension = ".png")
  public byte[] saveScreenshot() {
    return ((TakesScreenshot) Driver.driver).getScreenshotAs(OutputType.BYTES);
  }
}
