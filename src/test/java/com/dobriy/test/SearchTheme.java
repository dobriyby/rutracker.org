package com.dobriy.test;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.dobriy.pages.LoginPage;
import com.dobriy.pages.MainPage;
import com.dobriy.pages.TrackerPage;

import webdriver.BaseTest;

public class SearchTheme extends BaseTest {

	private String login1;
	private String password1;
	private String section;
	private String period;
	private String section2;

	@Parameters({ "login1", "password1", "section", "period", "section2" })
	@BeforeClass
	public void bedoreClass(String login1, String password1, String section, String period, String section2) {
		this.login1 = login1;
		this.password1 = password1;
		this.section = section;
		this.period = period;
		this.section2 = section2;
	}

	@Override
	public void runTest() {
		MainPage mainPage = new MainPage();

		logStep();
		mainPage.header.login(login1, password1);

		logStep();
		mainPage.header.navigateTracker();
		TrackerPage trackerPage = new TrackerPage();

		logStep();
		trackerPage.setSectionSearch(section);

		logStep();
	    trackerPage.setPeriod(period);
	    trackerPage.setSection(section2);
	    trackerPage.search();
	    trackerPage.testList("date and section");
	    
	    logStep();
	    trackerPage.header.exit();
	    LoginPage loginPage = new LoginPage();
		loginPage.isLoginForm();
	    
	}

}
