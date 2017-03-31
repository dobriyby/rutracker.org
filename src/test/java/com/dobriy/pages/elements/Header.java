package com.dobriy.pages.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import webdriver.decorator.CustomFieldDecorator;
import webdriver.decorator.Name;
import webdriver.elements.BaseElement;
import webdriver.elements.Button;
import webdriver.elements.Label;
import webdriver.elements.TextBox;

public class Header extends BaseElement {

	@FindBy(xpath = "//span[@class='a-like bold']")
	@Name(title = "Login Label")
	@CacheLookup
	private Label lblLogin;

	@FindBy(id = "top-login-uname")
	@Name(title = "Login TextBox")
	@CacheLookup
	private TextBox txbLogin;

	@FindBy(id = "top-login-pwd")
	@Name(title = "Password TextBox")
	@CacheLookup
	private TextBox txbPassword;

	@FindBy(id = "top-login-btn")
	@Name(title = "Login Button")
	@CacheLookup
	private Button bntLogin;

	@FindBy(xpath = "//a[contains(text(),'ЛС')]")
	@Name(title = "PM Button")
	@CacheLookup
	private Button bntMessage;
	
	@FindBy(id = "search-text")
	@Name(title = "Search TextBox")
	@CacheLookup
	private TextBox txbSearch;
	
	@FindBy(id = "search-submit")
	@Name(title = "Search Button")
	@CacheLookup
	private Button btnSearch;
	
	@FindBy(xpath = "//a[@class='logged-in-as-uname']/following-sibling::a")
	@Name(title = "Exit Button")
	@CacheLookup
	private Button btnExit;
	
	@FindBy(xpath = "//div[@id='main-nav']//b[contains(text(),'Трекер')]")
	@Name(title = "Tracker Button")
	@CacheLookup
	private Button btnTracker;
	
	@FindBy(xpath = "//div[@id='main-nav']//b[contains(text(),'Поиск')]")
	@Name(title = "Search Page Button")
	@CacheLookup
	private Button btnSearchPage;

	private By loginLable = By.xpath("//div[contains(@class,'logged-in-as')]");

	public Header(By locator, String nameOfElement) {
		super(locator, nameOfElement);
		PageFactory.initElements(new CustomFieldDecorator(browser.getDriver()), this);

	}

	@Override
	protected String getElementType() {
		return "Header";
	}

	public void login(final String login, final String password) {
		if (!txbLogin.isPresent()) {
			lblLogin.click();
		}

		txbLogin.setText(login);
		txbPassword.setText(password);
		bntLogin.click();
		isLogin(login);
	}

	public void isLogin(final String login) {
		Label loginStatus = new Label(loginLable, "Login Status");
		try {
			Assert.assertTrue(loginStatus.getText().contains(login));
			logger.info("Login '" + login + "' :: entered");
		} catch (Throwable e) {
			logger.fatal("Login  '" + login + "' :: not entered");
		}

	}
	
	public void navigatePM(){
		bntMessage.click();
	}
	
	public void sendSearch(final String message){
		txbSearch.setText(message);
	}
	
	public void search(){
		btnSearch.click();
	}
	
	public void exit(){
		btnExit.click();
	}
	
	public void navigateTracker(){
		btnTracker.click();
	}
	
	public void navigateSearch(){
		btnSearchPage.click();
	}

}
