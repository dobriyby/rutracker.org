package com.dobriy.test;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.dobriy.pages.LoginPage;
import com.dobriy.pages.MainPage;
import com.dobriy.pages.TrackerPage;

import webdriver.BaseTest;

public class DownloadTorrent extends BaseTest {

	private String login1;
	private String password1;
	private String message;

	@Parameters({ "login1", "password1", "message" })
	@BeforeClass
	public void bedoreClass(String login1, String password1, String message) {
		this.login1 = login1;
		this.password1 = password1;
		this.message = message;
	}

	@Override
	public void runTest() {
		MainPage mainPage = new MainPage();

		logStep();
		mainPage.header.login(login1, password1);

		logStep();
		mainPage.header.sendSearch(message);
		mainPage.header.search();
		TrackerPage trackPage = new TrackerPage();
		trackPage.isSearch(message);
		
		logStep();
		trackPage.testList("torrents");
		
		logStep();
		trackPage.header.exit();
		LoginPage loginPage = new LoginPage();
		loginPage.isLoginForm();
	}

}
