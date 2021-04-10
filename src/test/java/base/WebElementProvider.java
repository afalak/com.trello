package base;

import java.util.List;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Handles Element related functionalities like finding elements, mouse
 * hovering, listing elements etc.
 * 
 * @author Pratap
 *
 */
public class WebElementProvider {
  /**
   * It searches for the elements and if found more than one then returns first
   * element
   * 
   * @param elementName : value of the search property
   * @param type        : key attribute to look for the element
   * @return first web-element if found
   */
  public WebElement getWebElement(String elementName, ElementType type) {
    return getElement(elementName, type);
  }

  /**
   * It parse the element type and returns the ElementType data
   * 
   * @param elementType
   * @return ElementType
   */
  public ElementType getWebElementType(String elementType) {
    ElementType type = null;
    switch (elementType.toLowerCase()) {
    case "classname":
      type = ElementType.ClassName;
      break;
    case "id":
      type = ElementType.ID;
      break;
    case "xpath":
      type = ElementType.Xpath;
      break;
    case "name":
      type = ElementType.Name;
      break;
    case "tagname":
      type = ElementType.TagName;
      break;
    }
    return type;
  }

  /**
   * It searches for the elements and returns a List of WebElement(s)
   * 
   * @param elementName : value of the search property
   * @param type        : key attribute to look for the element
   * @return : List if WebElements found
   */
  public List<WebElement> getWebElements(String elementName, ElementType type) {
    List<WebElement> list = null;
    switch (type) {
    case ClassName:
      list = Driver.driver.findElements(By.className(elementName));
      break;
    case ID:
      list = Driver.driver.findElements(By.id(elementName));
      break;
    case Xpath:
      list = Driver.driver.findElements(By.xpath(elementName));
      break;
    case Name:
      list = Driver.driver.findElements(By.name(elementName));
      break;
    case TagName:
      list = Driver.driver.findElements(By.tagName(elementName));
      break;

    default:
      break;
    }
    return list;
  }

  public List<WebElement> getWebElements(Element masterElement, String elementName, ElementType type) {
    WebElement element = masterElement.getWebElement();
    List<WebElement> list = null;
    switch (type) {
    case ClassName:
      list = element.findElements(By.className(elementName));
      break;
    case ID:
      list = element.findElements(By.id(elementName));
      break;
    case Xpath:
      list = element.findElements(By.xpath(elementName));
      break;
    case Name:
      list = element.findElements(By.name(elementName));
      break;
    case TagName:
      list = element.findElements(By.tagName(elementName));
      break;

    default:
      break;
    }
    return list;
  }

  /**
   * It searches for the elements and if found more than one then returns first
   * element
   * 
   * @param elementName : value of the search property
   * @param type        : key attribute to look for the element
   * @return first web-element in the List if found
   */
  public WebElement getElement(String elementName, ElementType type) {
    List<WebElement> list = getWebElements(elementName, type);
    if (list.size() == 0)
      throw new ElementNotFoundException(elementName, type.toString(), elementName);
    else {
      return list.get(0);
    }
  }

  public WebElement getElement(Element masterElement, String elementName, ElementType type) {
    List<WebElement> list = getWebElements(masterElement, elementName, type);
    if (list.size() == 0)
      throw new ElementNotFoundException(elementName, type.toString(), elementName);
    else {
      return list.get(0);
    }
  }

}
