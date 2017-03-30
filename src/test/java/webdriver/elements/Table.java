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
			return element.findElements(By.tagName("tr")).get(intRow).findElements(By.xpath(".//td|.//th")).get(intCol);
		} else {
			return null;
		}
	}

	public Boolean findStringInCol(final int intCol, final String string) {
		for (int i = 0; i <= sizeRow(); i++) {
			if (getCell(i, intCol).getText().contains(string)) {
				info("'" + getCell(i, intCol).getText() + "' is contains '" + string + "'");
				return true;
			} else {
				info("'" + getCell(i, intCol).getText() + "' is not contains '" + string + "'");
			}
			;
		}
		return false;
	}

	public int sizeRow() {
		waitForIsElementPresent();
		return element.findElements(By.tagName("tr")).size();
	}

	public int sizeCol() {
		waitForIsElementPresent();
		return element.findElements(By.tagName("tr")).get(0).findElements(By.tagName("td")).size();
	}
}
