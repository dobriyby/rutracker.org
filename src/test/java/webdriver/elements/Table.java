package webdriver.elements;

import org.openqa.selenium.By;

import java.util.List;

import org.openqa.selenium.*;

public class Table extends BaseElement {

	public Table(By loc, String nameOf) {
		super(loc, nameOf);
	}

	public Table(String string, String name) {
		super(string, name);
	}

	public Table(By locator) {
		super(locator);
	}

	@Override
	protected String getElementType() {
		return getLoc("loc.table");
	}

	public List<WebElement> getRow(final int intRow) {
		if (sizeRow() > 0) {
			return element.findElements(By.tagName("tr")).get(intRow).findElements(By.tagName("td"));
		} else {
			return null;
		}
	}

	public List<WebElement> getRows() {
		if (sizeRow() > 0) {
			return element.findElements(By.tagName("tr"));
		} else {
			return null;
		}
	}

	public WebElement getCell(final int intRow, final int intCol) {
		if (sizeRow() > 0) {
			WebElement elem = element.findElements(By.tagName("tr")).get(intRow - 1)
					.findElements(By.xpath(".//td|.//th")).get(intCol - 1);
			browser.getDriver().executeScript("arguments[0].style.border='3px solid red'", elem);
			return elem;
		} else {
			return null;
		}
	}

	public WebElement getCell(final String text) {
		for (int i = 1; i <= sizeCol(); i++) {
			WebElement elem = findStringInCol(i, text);
			if (elem != null) {
				return elem;
			}
		}
		return null;
	}

	public WebElement findStringInCol(final int intCol, final String string) {
		for (int i = 1; i <= sizeRow(); i++) {
			WebElement elem = getCell(i, intCol);
			if (elem.getText().contains(string)) {
				return elem;
			}
		}
		return null;
	}

	public int sizeRow() {
		waitForIsElementPresent();
		return element.findElements(By.tagName("tr")).size();
	}

	public int sizeCol() {
		waitForIsElementPresent();
		return element.findElements(By.tagName("tr")).get(1).findElements(By.tagName("td")).size();
	}
}
