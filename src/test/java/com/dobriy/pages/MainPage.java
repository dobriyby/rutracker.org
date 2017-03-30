package com.dobriy.pages;

import org.openqa.selenium.By;

import com.dobriy.page.elements.Header;

import webdriver.BaseForm;

public class MainPage extends BaseForm {
	
	public Header header = new Header(By.id("page_header"),"Page Header");

	public MainPage() {
		super(By.id("latest_news"), "Main Page");
	}

}
