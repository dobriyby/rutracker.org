package com.dobriy.pages;

import org.openqa.selenium.By;
import org.testng.Assert;

import webdriver.BaseForm;
import webdriver.elements.Button;
import webdriver.elements.Label;
import webdriver.elements.TextBox;

public class NewMessagePage extends BaseForm {

	private TextBox txbName = new TextBox(By.name("username"), " PM Name TextBox");
	private TextBox txbSubject = new TextBox(By.name("subject"), " PM Subject TextBox");
	private TextBox txbMessage = new TextBox(By.name("message"), " PM Message TextBox");
	private Button bntSendMessage = new Button(By.id("post-submit-btn"), "Send Message Button");
	private Label lblAlert = new Label(By.id("bb-alert-box"), "Alert Lable");
	private static final String ALERT_EMPTY = "Вы должны ввести текст сообщения";

	public NewMessagePage() {
		super(By.xpath("//b[contains(text(),'Отправить личное сообщение')]"), "New Message Page");

	}

	public NewMessagePage sendName(final String name) {
		txbName.setText(name);
		return this;
	}

	public NewMessagePage testName(final String name) {
		try {
			Assert.assertEquals(txbName.getValue(), name);
			info("Name recipient correct");
		} catch (Throwable e) {
			fatal("Name recipient not correct");
		}
		return this;
	}

	public NewMessagePage sendSabject(final String subject) {
		txbSubject.setText(subject);
		return this;
	}

	public NewMessagePage sendText(final String text) {
		txbMessage.setText(text);
		return this;
	}

	public void clickSendMessage() {
		bntSendMessage.click();
	}

	public void testAlert() {
		lblAlert.isPresent();
		try {
			Assert.assertTrue(lblAlert.getText().contains(ALERT_EMPTY));
			info(ALERT_EMPTY+" message is present");
		} catch (Throwable e) {
			fatal(ALERT_EMPTY+" message not present");
		}
	}

}
