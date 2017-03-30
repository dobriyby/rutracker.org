package com.dobriy.test;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.dobriy.pages.InfoPage;
import com.dobriy.pages.MainPage;
import com.dobriy.pages.NewMessagePage;
import com.dobriy.pages.PrivateMessagePage;

import webdriver.BaseTest;

public class SendAndDeleteMessageTest extends BaseTest {

	private String login1;
	private String password1;
	private String login2;
	private String subject;
	private String message;

	@Parameters({ "login1", "password1", "login2", "subject", "message" })
	@BeforeClass
	public void bedoreClass(String login1, String password1, String login2, String subject, String message) {
		this.login1 = login1;
		this.password1 = password1;
		this.login2 = login2;
		this.subject = subject;
		this.message = message;
	}

	@Override
	public void runTest() {
		MainPage mainPage = new MainPage();

		logStep();
		mainPage.header.login(login1, password1);

		logStep();
		mainPage.header.navigatePM();
		PrivateMessagePage messagePage = new PrivateMessagePage("input");

		logStep();
		messagePage.navigateNewMessage();
		NewMessagePage newMessagePage = new NewMessagePage();

		logStep();
		newMessagePage.sendName(login2).sendSabject(subject).sendText(message);
		newMessagePage.clickSendMessage();
		InfoPage infoPage = new InfoPage();
		infoPage.isMessageSend();

		logStep();
		infoPage.navigateOutBox();
		PrivateMessagePage outputMessagePage = new PrivateMessagePage("output");
		outputMessagePage.searchSubject(subject);
		
		logStep();
		outputMessagePage.deleteAll();
		InfoPage infoPage2 = new InfoPage();
		infoPage2.isConfirmDeleteMessages();
		
		logStep();
		infoPage2.clickOk();
		infoPage2.isMessagesDelete();

	}
}
