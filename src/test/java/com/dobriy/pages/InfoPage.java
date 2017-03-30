package com.dobriy.pages;

import org.openqa.selenium.By;
import org.testng.Assert;

import webdriver.BaseForm;
import webdriver.elements.Button;
import webdriver.elements.Label;

public class InfoPage extends BaseForm {

	private Label lblInfo = new Label(By.xpath("//table[@class='forumline message']"), "Info Label");
	private String successSend = "Ваше сообщение было отправлено";
	private String failSend = "Ваше сообщение не отправлено";
	private String confirmDelete = "Вы уверены, что хотите удалить эти сообщения?";
	private String messagesDelete = "Выбранные сообщения были удалены";
	private String messagesNotDelete = "Выбранные сообщения не удалены";
	private Button bntOutBox = new Button(By.xpath("//a/b[text()='Исходящие']"), "OutBox Button");
	private Button btnOk = new Button(By.name("confirm"), "OK Button");

	public InfoPage() {
		super(By.xpath("//table[@class='forumline message']"), "Info Page");
	}

	public void isMessageSend() {
		try {
			Assert.assertTrue(lblInfo.getText().contains(successSend));
			logger.info(successSend);
		} catch (Throwable e) {
			logger.fatal(failSend);
		}
	}

	public void isMessagesDelete() {
		try {
			Assert.assertTrue(lblInfo.getText().contains(messagesDelete));
			logger.info(messagesDelete);
		} catch (Throwable e) {
			logger.fatal(messagesNotDelete);
		}
	}

	public void clickOk() {
		btnOk.click();
	}

	public void isConfirmDeleteMessages() {
		Assert.assertTrue(lblInfo.getText().contains(confirmDelete));
	}

	public void navigateOutBox() {
		bntOutBox.click();
	}

}
