package com.dobriy.pages;

import org.openqa.selenium.By;

import com.dobriy.pages.elements.Header;

import webdriver.BaseForm;
import webdriver.elements.Table;

public class MainPage extends BaseForm {
	
	private Table tblLearningLang = new Table(By.id("cf-34"),"Learning foreign languages Table");
	
	public Header header = new Header(By.id("page_header"),"Page Header");

	public MainPage() {
		super(By.id("latest_news"), "Main Page");
	}

	public void navigateToLangTable(final String section){
		tblLearningLang.getCell(section).findElement(By.tagName("a")).click();
	}
}
