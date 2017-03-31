package com.dobriy.pages;

import org.openqa.selenium.By;

import webdriver.BaseForm;
import webdriver.elements.Table;

public class SearchResultPage extends BaseForm {

	private static final int AUTHOR_COL = 1;

	private Table tblRecords = new Table(By.xpath("//table[@class='topic']"), "Records Table");

	public SearchResultPage() {
		super(By.xpath("//h1[contains(text(),'Результатов поиска:')]"), "Search Result Page");
	}

	public void navigateAuthor(final String searchName) {
		tblRecords.findStringInCol(AUTHOR_COL, searchName).findElement(By.tagName("a")).click();
	}
}
