package base;

import java.util.ArrayList;
import java.util.List;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Element {
  private WebElement webElement;
  private String elementValue;
  private ElementType elementType;
  private WebElementProvider webElementProvider;

  public Element() {
    elementType = null;
    webElementProvider = new WebElementProvider();
  }

  protected void setElementValue(String elementValue) {
    this.elementValue = elementValue;
  }

  protected String getElementValue() {
    return this.elementValue;
  }

  /**
   * This sets the elements with element types(ID, XPath, etc.) and elements names
   * 
   * @param elementInfo : Type of element to identify the element as string
   */
  protected void setElement(String elementInfo[]) {
    this.elementType = webElementProvider.getWebElementType(elementInfo[0]);
    this.elementValue = elementInfo[1];
  }

  protected void setElement(WebElement element) {
    this.webElement = element;
  }

  protected WebElement getWebElement() {
    return webElement;
  }

  /**
   * This method is use to click on a element
   */
  public void click() {
    webElement = webElementProvider.getWebElement(elementValue, elementType);
    waitForElementToBeClickable();
    webElement.click();
  }

  /**
   * This method is use to verify presence of a web element within the web page
   * 
   * @return true if the element exists within the webpage, else it returns false
   */

  public boolean isExists() {
    try {
      DriverSettings.setWait(2);
      webElement = webElementProvider.getWebElement(elementValue, elementType);
      return webElement.isDisplayed();
    } catch (ElementNotFoundException e) {
      return false;
    } finally {
      DriverSettings.setWait(Browser.timeToWait);
    }
  }

  public boolean isDisplayed() {
    try {
      webElement = webElementProvider.getWebElement(elementValue, elementType);
      // bringToFront(webElement);
      if (webElement == null) {
        System.out.println("null element");
        return false;
      } else {
        return webElement.isDisplayed();
      }
    } catch (Exception e) {
      return false;
    }
  }

  /**
   * This method is use to read the inner text of an element
   * 
   * @return The innerText of the element
   */

  public String read() {
    webElement = webElementProvider.getWebElement(elementValue, elementType);
    return webElement.getText();
  }

  /**
   * This method is use to read the content stored inside value attribute of an
   * element For many elements the value is not stored ad inner text rather stored
   * inside the value attribute, so the user can use this method to get the text
   * for that element
   * 
   * @return The text/value stored inside value attribute of an element
   */
  public String readByValue() {
    return getAttributeValue("value");
  }

  /**
   * This method is use to write the value provided by the user on that input
   * field
   * 
   * @param msg The value user want to write
   */
  public void write(String msg) {
    webElement = webElementProvider.getWebElement(elementValue, elementType);
    webElement.sendKeys(msg);
  }

  /**
   * This method is use to clear the existing text of an input field and then
   * write the value provided by the user on that input field
   * 
   * @param msg The value user want to write
   */

  public void clearAndWrite(String msg) throws InterruptedException {
    webElement = webElementProvider.getWebElement(elementValue, elementType);
    // webElement.clear();
    Thread.sleep(500);
    webElement.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE), msg);
  }

  /**
   * Returns attribute value based on the attribute name of the element
   * 
   * @param attributeName : name of the attribute associated with the element
   * @return : returns the value as String
   */
  public String getAttributeValue(String attributeName) {
    webElement = webElementProvider.getWebElement(elementValue, elementType);
    String s = webElement.getAttribute(attributeName);
    return s;
  }

  /**
   * Selects value from the drop-down/list box
   * 
   * @param data : the value to be selected
   */
  public void selectValue(String data) {
    webElement = webElementProvider.getWebElement(elementValue, elementType);
    Select select = new Select(webElement);
    select.selectByValue(data);
  }

  /**
   * Selects visible value from the drop-down/list box
   * 
   * @param data : the value to be selected
   */
  public void selectVisibleText(String data) {
    webElement = webElementProvider.getWebElement(elementValue, elementType);
    Select select = new Select(webElement);
    select.selectByVisibleText(data);
  }

  /**
   * This method is use to check if an element is in enable state or not
   * 
   * @return true: if the element is in enabled state
   * @return false: if the element is in disable state
   */
  public boolean isEnabled() {
    webElement = webElementProvider.getWebElement(elementValue, elementType);
    return webElement.isEnabled();
  }

  /**
   * This method is use to check if an element is selected or not, applicable for
   * check box/radio button
   * 
   * @return true: if the element is selected
   * @return false: if the element is not selected
   */
  public boolean isSelected() {
    webElement = webElementProvider.getWebElement(elementValue, elementType);
    return webElement.isSelected();
  }

  /**
   * This method is use to wipe out the text inside an input field
   */
  public void clear() {
    webElement = webElementProvider.getWebElement(elementValue, elementType);
    webElement.clear();
  }

  /**
   * This method is use to check any checkbox
   */

  public void check() {
    webElement = webElementProvider.getWebElement(elementValue, elementType);
    if (webElement.isSelected() == false) {
      webElement.click();
    }
  }

  /**
   * This method is use to uncheck any checkbox
   */
  public void unCheck() {
    webElement = webElementProvider.getWebElement(elementValue, elementType);
    if (webElement.isSelected() == true) {
      webElement.click();
    }
  }

  /**
   * This method is use to wipe out characters from any input fields from right to
   * left
   * 
   * @param length : number of character the user want to wipe out
   */
  public void clearAllChars(int length) {
    click();
    Actions action = new Actions(Driver.driver);

    for (int i = 0; i < length; i++) {
      action.sendKeys(Keys.BACK_SPACE).build().perform();
    }
  }

  /**
   * This method is use to wipe out the text inside an input field
   */
  public void clearAllChars() {
    click();
    clear();
    write("a");
    Actions action = new Actions(Driver.driver);
    action.sendKeys(Keys.BACK_SPACE).build().perform();
  }

  public void hitKey(Keys key) {

    webElement = webElementProvider.getWebElement(elementValue, elementType);
    webElement.sendKeys(key);

  }

  /**
   * This method is use to click on a element, this method use java script
   * executor. So it's recommended to used when the normal click method does not
   * work
   */
  public void javaScriptClick() {

    webElement = webElementProvider.getWebElement(elementValue, elementType);
    waitForElementToBeClickable();
    ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", webElement);

  }

  /**
   * This method is to get the current selected value of a dropdown list
   * 
   * @return the value of the selected option for a drop down list
   */
  public String getSelectedValue() {
    webElement = webElementProvider.getWebElement(elementValue, elementType);
    Select selectElement = new Select(webElement);
    return selectElement.getFirstSelectedOption().getText();
  }

  /**
   * This method is use to check if any toggle button is in active state or not,
   * This method check the 'background-color' css property to determine the state
   * of the toggle. If the color is green then it;s active else it's inactive
   * 
   * @return true: If the toggle is in active state
   * @return false: If the toggle is in deactivated state
   */
  public boolean isToggleSelected() {
    String colourHexValue = Color.fromString(getCSSvalue("background-color")).asHex();
    if (colourHexValue.equalsIgnoreCase("#12820E")) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * This method is use to get the value of any css property belongs to an element
   * 
   * @param cssProperty : the property name of which used want to value
   * @return The value of the css property
   */
  private String getCSSvalue(String cssProperty) {
    webElement = webElementProvider.getWebElement(elementValue, elementType);
    return webElement.getCssValue(cssProperty);
  }

  /**
   * This method is use to mouse hover on an element
   */
  public void mouseHover() {
    webElement = webElementProvider.getWebElement(elementValue, elementType);
    Actions action = new Actions(Driver.driver);
    action.moveToElement(webElement).build().perform();
  }

  /**
   * This method is use to click on any sub element or an parent element. This
   * method is designed mainly to select any option from unordered list
   * 
   * @param visibaleText: Visible value of the element
   * @param relativeXpath Relative Xpath of the sub elements
   */
  private void selectSubElementByVisibleText(String visibaleText, String relativeXpath) {
    List<WebElement> options = webElementProvider.getWebElements(this, relativeXpath, ElementType.Xpath);
    for (WebElement option : options) {
      if (option.getText().equals(visibaleText)) {
        option.click(); // click the desired option
        break;
      }
    }

  }

  /**
   * This method is use to select any option by it's value from unordered list
   * 
   * @param visibaleText : the value of the option
   */
  public void selectByVisibleTextFromUL(String visibaleText) {
    webElement = webElementProvider.getWebElement(elementValue, elementType);
    selectSubElementByVisibleText(visibaleText, ".//li");
  }

  /** This method is to get the page title **/
  public String getPageTitle() {
    return Driver.driver.getTitle();
  }

  /** This method is to switch to new window **/
  public void switchWindow() {
    // String winHandleBefore = Driver.driver.getWindowHandle();
    for (String winHandle : Driver.driver.getWindowHandles()) {
      Driver.driver.switchTo().window(winHandle);
    }
    // Driver.driver.switchTo().window(winHandleBefore);
  }

  public void clickByAction() {
    webElement = webElementProvider.getWebElement(elementValue, elementType);
    Actions action = new Actions(Driver.driver);
    action.click(webElement);
  }

  public ArrayList<String> getAllValuesFromList() {
    ArrayList<String> allValues = new ArrayList<>();
    webElement = webElementProvider.getWebElement(elementValue, elementType);
    Select selectElement = new Select(webElement);
    List<WebElement> options = selectElement.getOptions();
    for (int i = 0; i < options.size(); i++) {
      allValues.add(options.get(i).getText());
    }
    return allValues;
  }

  private ArrayList<WebElement> getAllOptionsFromList() {
    ArrayList<WebElement> allOptions = new ArrayList<>();
    webElement = webElementProvider.getWebElement(elementValue, elementType);
    Select selectElement = new Select(webElement);
    List<WebElement> options = selectElement.getOptions();
    for (int i = 0; i < options.size(); i++) {
      allOptions.add(options.get(i));
    }
    return allOptions;
  }

  /**
   * Selects value from the drop-down/list box based on index
   * 
   * @param index : the index of the option
   */
  public void selectByIndex(int index) {
    webElement = webElementProvider.getWebElement(elementValue, elementType);
    Select select = new Select(webElement);
    select.selectByIndex(index);
  }

  private void waitForElementToBeClickable() {
    WebDriverWait wait = new WebDriverWait(Driver.driver, 30);
    wait.until(ExpectedConditions.elementToBeClickable(webElement));
  }

  public void waitForElementToBeVisible() {
    webElement = webElementProvider.getWebElement(elementValue, elementType);
    WebDriverWait wait = new WebDriverWait(Driver.driver, 30);
    wait.until(ExpectedConditions.visibilityOf(webElement));
  }

}
