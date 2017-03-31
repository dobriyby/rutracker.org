package com.dobriy.pages;

import org.junit.Assert;
import org.openqa.selenium.By;

import webdriver.BaseForm;
import webdriver.elements.Button;
import webdriver.elements.Table;

public class PrivateMessagePage extends BaseForm {

	private Button btnNewMessage = new Button(By.xpath("//img[contains(@alt,'new')]"), "New Message Button");
	private Table tblMessages = new Table(By.xpath("//table[@class='forumline']"), "Messages Table");
	private Button btnDeteleAll = new Button(By.name("deleteall"), "Delete All Button");
	private static final int SUBJECT_COL= 2;
	private String typeMessages;

	public PrivateMessagePage(final String type) {
		super(By.id("pm_header"), "PM Page");
		typeMessages = type;
	}

	public void navigateNewMessage() {
		btnNewMessage.click();
	}

	public String getTypeMessages() {
		return typeMessages;
	}

	public void searchSubject(final String subject) {
		try {
			Assert.assertNotNull(tblMessages.findStringInCol(SUBJECT_COL, subject));
			logger.info("Subject '" + subject + "' in Message Table is found");
		} catch (Throwable e) {
			logger.fatal("Subject '" + subject + "' in Message Table is not found");
		}
	}

	public void deleteAll() {
		btnDeteleAll.click();
	}

}
