package com.dobriy.pages;

import org.openqa.selenium.By;

import webdriver.BaseForm;

public class NewPostPage extends BaseForm {

	public NewPostPage() {
		super(By.id("ped-editor"), "New Post Page");
	}

}
