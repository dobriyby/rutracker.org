package com.dobriy.pages;

import org.openqa.selenium.By;

import webdriver.BaseForm;
import webdriver.elements.Button;
import webdriver.elements.TextBox;

public class NewMessagePage extends BaseForm {

	private TextBox txbName = new TextBox(By.name("username"), " PM Name TextBox");
	private TextBox txbSubject = new TextBox(By.name("subject"), " PM Subject TextBox");
	private TextBox txbMessage = new TextBox(By.name("message"), " PM Message TextBox");
	private Button bntSendMessage = new Button(By.id("post-submit-btn"), "Send Message Button");


	public NewMessagePage() {
		super(By.xpath("//th[@class ='thHead']"), "New Message Page");
		
	}

	public NewMessagePage sendName(final String name) {
		txbName.setText(name);
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
	
	public void clickSendMessage(){
		bntSendMessage.click();
	}

}
