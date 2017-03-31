package com.dobriy.pages;

import org.openqa.selenium.By;
import org.testng.Assert;

import webdriver.BaseForm;
import webdriver.elements.Button;
import webdriver.elements.TextBox;

public class LoginPage extends BaseForm {

	private TextBox txbLogin = new TextBox(By.name("login_username"), "Login TextBox");
	private TextBox txbPassword = new TextBox(By.name("login_password"), "Password TextBox");
	private Button btnLogin = new Button(By.xpath("//input[@value='Вход']"),"Login Button");

	public LoginPage() {
		super(By.xpath("//h1[@class='pagetitle' and text()='Вход']"), "Login Page");
	}

	public void isLoginForm() {
		try {
			Assert.assertTrue(txbLogin.isPresent());
			Assert.assertTrue(txbPassword.isPresent());
			info("Login Form is available");
		} catch (Throwable e) {
			fatal("Login Form Not available");
		}
	}
	
	public void login(final String login, final String password){
		txbLogin.setText(login);
		txbPassword.setText(password);
		btnLogin.click();
	}
}
