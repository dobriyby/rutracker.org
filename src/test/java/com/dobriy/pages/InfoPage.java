package com.dobriy.pages;

import org.openqa.selenium.By;
import org.testng.Assert;

import webdriver.BaseForm;
import webdriver.elements.Button;
import webdriver.elements.Label;

/*
 * Класс-куртизантка, служит общим классом для всех страниц с информацией/предупреждении при выполнении действий
 */
public class InfoPage extends BaseForm {

	private Label lblInfo = new Label(By.xpath("//table[@class='forumline message']"), "Info Label");
	private static final String SUCCESS_SEND = "Ваше сообщение было отправлено";
	private static final String FAIL_SEND = "Ваше сообщение не отправлено";
	private static final String CONFIRM_DELETE = "Вы уверены, что хотите удалить эти сообщения?";
	private static final String MESSAGES_DELETE = "Выбранные сообщения были удалены";
	private static final String MESSAGES_NOT_DELETE = "Выбранные сообщения не удалены";
	private Button bntOutBox = new Button(By.xpath("//a/b[text()='Исходящие']"), "OutBox Button");
	private Button btnOk = new Button(By.name("confirm"), "OK Button");

	public InfoPage() {
		super(By.xpath("//table[@class='forumline message']"), "Info Page");
	}

	/*
	 * Проверка наличия сообщения об удачной отправке сообщения
	 */
	public void isMessageSend() {
		try {
			Assert.assertTrue(lblInfo.getText().contains(SUCCESS_SEND));
			logger.info(SUCCESS_SEND);
		} catch (Throwable e) {
			logger.fatal(FAIL_SEND);
		}
	}

	/*
	 * Проверка наличия сообщения об удачном удалении сообщений
	 */
	public void isMessagesDelete() {
		try {
			Assert.assertTrue(lblInfo.getText().contains(MESSAGES_DELETE));
			logger.info(MESSAGES_DELETE);
		} catch (Throwable e) {
			logger.fatal(MESSAGES_NOT_DELETE);
		}
	}

	public void clickOk() {
		btnOk.click();
	}

	/*
	 * Проверка наличия предупреждения при удалении сообщений
	 */
	public void isConfirmDeleteMessages() {
		Assert.assertTrue(lblInfo.getText().contains(CONFIRM_DELETE));
	}

	public void navigateOutBox() {
		bntOutBox.click();
	}

}
