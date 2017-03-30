package com.dobriy.pages;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import com.dobriy.page.elements.Header;

import webdriver.BaseForm;
import webdriver.elements.Button;
import webdriver.elements.ComboBox;
import webdriver.elements.Label;
import webdriver.elements.Table;
import webdriver.elements.TextBox;

public class TrackerPage extends BaseForm {

	public Header header = new Header(By.id("page_header"), "Page Header");
	private ComboBox cmbPeriod = new ComboBox(By.id("tm"), "Period ComboBox");
	private ComboBox cmbSection = new ComboBox(By.id("fs-main"), "Section ComboBox");
	private Button btnNext = new Button(By.xpath("//a[contains(text(),'След.')]"), "Next Page Button");
	private Button btnSearch = new Button(By.id("tr-submit-btn"), "Search Button");
	private TextBox txbTitleSearch = new TextBox(By.id("title-search"), "Search TextBox");
	private Table tblTorrent = new Table(By.id("tor-tbl"), "Torrent Table");
	private Label lblCountTorrents = new Label(By.xpath("//p[contains(text(),'Результатов поиска')]"),
			"Size Torrents Lable");

	private TextBox txbSectionSearch = new TextBox(By.id("fs-qs-input"), "Section Search TextBox");

	public TrackerPage() {
		super(By.xpath("//h1[@class='maintitle' and text()='Трекер']"), "Tracker Page");
	}

	public void isSearch(final String message) {
		assertEquals(txbTitleSearch.getValue(), message);
	}

	public void testList(final String type) {
		if (tblTorrent.sizeRow() > 0) {
			int curPage = 1;
			float countTorrents = Integer.parseInt(lblCountTorrents.getText().split(" ")[2]);
			int sizePage = (int) Math.ceil(countTorrents / 50);
			while (curPage <= sizePage) {
				switch (type) {
				case "torrents":
					testCurPage();
					break;
				case "date and section":
					testListSection();
					testListPeriod();
					break;
				}
				if (curPage == sizePage) {
					break;
				}
				nextPage();
				curPage++;

			}
		}

	}

	private void testCurPage() {
		new Actions(browser.getDriver()).keyDown(Keys.CONTROL).keyDown(Keys.SHIFT).perform();
		for (int i = 1; i <= (tblTorrent.sizeRow() - 2); i++) {
			WebElement elem = tblTorrent.getCell(i, 3).findElement(By.xpath(".//a"));
			String title = elem.getText();
			elem.click();
			switchHandle();
			TorrentPage torrentPage = new TorrentPage();
			torrentPage.testTitle(title);
			logStep();
			torrentPage.testDownload();
			logStep();
			browser.getDriver().close();
			switchHandle();
		}
		new Actions(browser.getDriver()).keyUp(Keys.CONTROL).keyUp(Keys.SHIFT).perform();
	}

	private void switchHandle() {
		for (String winHandle : browser.getDriver().getWindowHandles()) {
			browser.getDriver().switchTo().window(winHandle);
		}
	}

	public void nextPage() {
		if (btnNext.isPresent()) {
			btnNext.click();
		}
	}

	public void setSectionSearch(final String section) {
		txbSectionSearch.setText(section);
		testSectionFilter(section);
	}

	private void testSectionFilter(final String section) {
		browser.getDriver().manage().timeouts().setScriptTimeout(15, TimeUnit.SECONDS);
		cmbSection.isPresent();
		for (String st : cmbSection.getOptions()) {
			if (st.contains("|-")) {
				Assert.assertTrue(st.toLowerCase().contains(section.toLowerCase()));
				info("Section :: " + st + " :: is correct");
			}
		}
		info("Section list is correct");
	}

	public TrackerPage testListSection() {
		for (int i = 1; i <= (tblTorrent.sizeRow() - 2); i++) {
			String lineSection = tblTorrent.getCell(i, 2).findElement(By.xpath(".//a")).getText();
			String curSection = cmbSection.getCheckedSection().replace("|-", "").trim();
			Assert.assertEquals(lineSection, curSection);
			info(lineSection + " :: equals :: " + curSection);
		}
		return this;
	}

	public TrackerPage testListPeriod() {
		for (int i = 1; i <= (tblTorrent.sizeRow() - 2); i++) {
			String lineSection = tblTorrent.getCell(i, 9).findElement(By.xpath(".//p")).getText();
			long duration = durationDate(lineSection);
			long curPeriod = 0;
			switch (cmbPeriod.getCheckedSection()) {
			case " последние 3 дня ":
				curPeriod = 3;
				break;
			}
			Assert.assertTrue(duration<=curPeriod);
			info(lineSection+" :: match :: "+cmbPeriod.getCheckedSection());
		}
		return this;
	}

	private long durationDate(final String lineSection) {
		Date date = null;
		try {
			date = new SimpleDateFormat("dd-MMM-yy", new Locale("ru")).parse(lineSection);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		LocalDateTime tor = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
		LocalDateTime cur = LocalDateTime.now();
		return Duration.between(tor, cur).toDays();
	}

	public TrackerPage setPeriod(final String period) {
		cmbPeriod.setPartOption(period);
		return this;
	}

	public TrackerPage setSection(final String section) {
		cmbSection.setPartOption(section);
		return this;
	}

	public void search() {
		btnSearch.click();
	}

}
