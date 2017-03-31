package com.dobriy.pages;

import org.openqa.selenium.By;
import org.testng.Assert;

import webdriver.BaseForm;
import webdriver.elements.Button;
import webdriver.elements.Label;

public class ProfilePage extends BaseForm {

	private Label lblProfile = new Label(By.xpath("//h1[contains(text(),'Профиль пользователя:')]"),
			" Profile Name Label");
	private Button btnSend = new Button(By.xpath("//a[text()='[Отправить]']"), "Send Button");

	public ProfilePage() {
		super(By.xpath("//h1[contains(text(),'Профиль пользователя:')]"), "Profile Page");
	}

	public void isProfile(final String profileName) {
		try {
			Assert.assertTrue(lblProfile.getText().contains(profileName));
			info("Profile correct");
		} catch (Throwable e) {
			fatal("Profile incorrect");
		}
	}

	public void clickSend() {
		btnSend.click();
	}

}
