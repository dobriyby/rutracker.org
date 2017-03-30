package webdriver.elements;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class ComboBox extends BaseElement {

	public ComboBox(final By locator, final String name) {
		super(locator, name);
	}

	public ComboBox(String string, String name) {
		super(string, name);
	}

	public ComboBox(By locator) {
		super(locator);
	}

	protected String getElementType() {
		return getLoc("loc.combobox");
	}

	public boolean isEnabled() {
		return this.getElement().isEnabled();
	}

	public void setOption(final String option) {
		waitForIsElementPresent();
		browser.getDriver().executeScript("arguments[0].style.border='3px solid red'", element);
		new Select(element).selectByVisibleText(option);
	}
	
	public void setPartOption(final String option) {
		waitForIsElementPresent();
		browser.getDriver().executeScript("arguments[0].style.border='3px solid red'", element);
		for(WebElement elem:element.findElements(By.tagName("option"))){
			if (elem.getText().contains(option)){
				elem.click();
				info("select :: "+elem.getText());
			}
		}
	}

	public boolean isContains(final String option) {
		for (String elem : getOptions()){
			if (elem.toLowerCase().contains(option)){
				return true;
			}
		}
		return false;
	}

	public List<String> getOptions() {
		waitForIsElementPresent();
		browser.getDriver().executeScript("arguments[0].style.border='3px solid red'", element);
		List<String> listOption = new ArrayList<String>();
		for (WebElement elem : new Select(element).getOptions()) {
			listOption.add(elem.getText());
		}
		return listOption;
	}
	
	public String getCheckedSection(){
		waitForIsElementPresent();
		return new Select(element).getFirstSelectedOption().getText();
	}
}
