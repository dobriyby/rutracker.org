package com.dobriy.test;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.dobriy.pages.MainPage;
import com.dobriy.pages.NewMessagePage;
import com.dobriy.pages.ProfilePage;
import com.dobriy.pages.SearchPage;
import com.dobriy.pages.SearchResultPage;

import webdriver.BaseTest;

public class SearchPersonAndSend extends BaseTest {

	private String login;
	private String password;
	private String searchName;
	private String type;

	@Parameters({ "login", "password", "searchName", "type" })
	@BeforeClass
	public void bedoreClass(String login, String password, String searchName, String type) {
		this.login = login;
		this.password = password;
		this.searchName = searchName;
		this.type = type;
	}

	@Override
	public void runTest() {
		MainPage mainPage = new MainPage();

		logStep();
		mainPage.header.login(login, password);

		logStep();
		mainPage.header.navigateSearch();
		SearchPage searchPage = new SearchPage();

		logStep();
		searchPage.setAuthor(searchName);
		searchPage.setType(type);
		searchPage.search();
		SearchResultPage resultPage = new SearchResultPage();

		logStep();
		resultPage.navigateAuthor(searchName);
		ProfilePage profilePage = new ProfilePage();
		profilePage.isProfile(searchName);

		logStep();
		profilePage.clickSend();
		NewMessagePage messagePage = new NewMessagePage();
		messagePage.testName(searchName);

		logStep();
		messagePage.clickSendMessage();
		messagePage.testAlert();

	}

}
