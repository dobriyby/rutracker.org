package com.dobriy.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import webdriver.BaseForm;
import webdriver.elements.Label;
import webdriver.elements.Table;

public class ListThemePage extends BaseForm {

	private Label lblTitle = new Label(By.xpath("//h1[@class='maintitle']"), "Title Labels");
	private Table tblListSection = new Table(
			By.xpath("//th[contains(text(),'Форум')]/ancestor::table[@class='forumline forum']"), "List Section Table");
	private Table tblListTheme = new Table(
			By.xpath("//th[contains(text(),'Темы')]/ancestor::table[@class='forumline forum']"), "List Section Table");
	private static final int THEME_COL = 2;
	private static final String THEME_ROW = "Темы";

	public ListThemePage() {
		super(By.xpath("//img[contains(@alt,'Новая тема')]"), "List Theme Page");
	}

	/*
	 * Проверка title страницы
	 */
	public void testTitle(final String title) {
		assertEquals(lblTitle.getText(), title);
		info("Title '" + this.title + "' :: is correct");
	}

	public void navigateToSection(final String section) {
		tblListSection.findStringInCol(THEME_COL, section).findElement(By.tagName("a")).click();
	}

	/*
	 * Переход на страницу torrent'а и проверка title'а
	 */
	public TorrentPage navigaetToTheme(final int themeId) {
		WebElement elem = tblListTheme.getCell(THEME_ROW).findElements(By.xpath(".//parent::tr/following-sibling::tr"))
				.get(themeId - 1).findElement(By.xpath(".//a[contains(@id,'tt')]"));
		String titleTheme = elem.getText();
		elem.click();
		TorrentPage torrentPage = new TorrentPage();
		torrentPage.testTitle(titleTheme);
		return torrentPage;
	}
}
