package com.dobriy.pages;

import org.openqa.selenium.By;

import webdriver.BaseForm;
import webdriver.elements.Button;
import webdriver.elements.ComboBox;
import webdriver.elements.TextBox;

public class SearchPage extends BaseForm {

	private TextBox txbAuthor = new TextBox(By.id("author"), "Author TextBoxs");
	private ComboBox cmbType = new ComboBox(By.id("dm"), "Type Search ComboBox");
	private Button btnSearch = new Button(By.name("submit"), "Submit Search Button");

	public SearchPage() {
		super(By.xpath("//h1[text()='Поиск']"), "Search Page");
	}

	public SearchPage setAuthor(final String author) {
		txbAuthor.setText(author);
		return this;
	}

	public SearchPage setType(final String type) {
		cmbType.setPartOption(type);
		return this;
	}

	public void search() {
		btnSearch.click();
	}

}
