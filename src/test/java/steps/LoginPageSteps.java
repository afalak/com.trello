package steps;

import ScreenClasses.LoginPage;
import base.BaseUtil;
import base.Browser;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import utils.LoadEnvironment;

public class LoginPageSteps extends BaseUtil {
  LoginPage loginPage = new LoginPage();

  @Given("^navigate to login page$")
  public void navigate_to_login_page() throws Throwable {
    LoadEnvironment.loadProperties();
    String LOGIN_URL = "https://" + LoadEnvironment.IP + LoginPath;
    System.out.println("Login to: " + LOGIN_URL);
    Browser.navigateTo(LOGIN_URL);
    Thread.sleep(2000);
  }

  @And("^enter Username as \"([^\"]*)\"$")
  public void enterUsernameas(String username) throws Throwable {
    loginPage.userNameTextIO.clear();
    loginPage.userNameTextIO.write(username);
    Thread.sleep(2000);
  }

  @And("^enter Password as \"([^\"]*)\"$")
  public void enterPasswordas(String password) throws Throwable {
    loginPage.passwordText.write(password);
  }

  @Then("^click on login button$")
  public void clickOnLoginButton() throws Throwable {
    loginPage.loginButton.click();
    Thread.sleep(2000);
  }

  @Then("^click on login submit button$")
  public void clickOnLoginsubmitButton() throws Throwable {
    loginPage.LoginSubmitButton.click();
    Thread.sleep(2000);
  }

  @Then("^click on board logo$")
  public void clickOnBoardLogo() throws Throwable {
    loginPage.board.click();
    Thread.sleep(2000);
  }

  @Then("^click on add a card$")
  public void clickOnAddCard() throws Throwable {
    loginPage.addacard.click();
    Thread.sleep(2000);
  }

  @Then("^Write card name$")
  public void CardName() throws Throwable {
    loginPage.givename.write("my card");
    ;
    Thread.sleep(2000);
  }

  @Then("^Save the card$")
  public void SaveTheCard() throws Throwable {
    loginPage.savecard.click();
    Thread.sleep(2000);
  }

  @Then("^check card is present$")
  public void CardPresent() throws Throwable {
    loginPage.cardpresent.isExists();
    Thread.sleep(2000);
  }

}
