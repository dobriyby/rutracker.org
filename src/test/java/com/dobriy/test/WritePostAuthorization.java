package com.dobriy.test;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.dobriy.pages.ListThemePage;
import com.dobriy.pages.LoginPage;
import com.dobriy.pages.MainPage;
import com.dobriy.pages.NewPostPage;
import com.dobriy.pages.TorrentPage;

import webdriver.BaseTest;

public class WritePostAuthorization extends BaseTest {

	private String login;
	private String password;
	private String section1;
	private String section2;

	@Parameters({ "login", "password", "section1", "section2" })
	@BeforeClass
	public void bedoreClass(String login, String password, String section1, String section2) {
		this.login = login;
		this.password = password;
		this.section1 = section1;
		this.section2 = section2;
	}

	@Override
	public void runTest() {
		MainPage mainPage = new MainPage();

		logStep();
		mainPage.navigateToLangTable(section1);
		ListThemePage listThemePage = new ListThemePage();
		listThemePage.testTitle(section1);

		logStep();
		listThemePage.navigateToSection(section2);
		ListThemePage listThemePage2 = new ListThemePage();
		listThemePage2.testTitle(section2);

		logStep();
		TorrentPage torrentPage = listThemePage2.navigaetToTheme(1);
		torrentPage.clickAnswer();
		LoginPage loginPage = new LoginPage();
		loginPage.isLoginForm();

		logStep();
		loginPage.login(login, password);
		NewPostPage newPostPage = new NewPostPage();
		newPostPage.assertIsOpen();

	}

}
