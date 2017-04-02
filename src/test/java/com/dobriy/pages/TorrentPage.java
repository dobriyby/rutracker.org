package com.dobriy.pages;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import org.openqa.selenium.By;
import org.testng.Assert;

import com.dobriy.utils.FileThruScripts;

import net.seedboxer.bencode.BDecoder;
import webdriver.BaseForm;
import webdriver.elements.Button;
import webdriver.elements.Label;

public class TorrentPage extends BaseForm {

	private Label lblTitle = new Label(By.xpath("//h1[@class='maintitle']"), "Title Labels");
	private Button btnDownload = new Button(By.xpath("//a[contains(@class,'dl-link')]"), "Download Button");
	private Button btnAnswer = new Button(By.xpath("//img[contains(@alt,'Ответить')]"), "Answer Button");

	public TorrentPage() {
		super(By.xpath("//table[@id='topic_main']"), "Torrent Page");
	}

	public void testTitle(final String title) {
		try {
			Assert.assertTrue(lblTitle.getText().contains(title));
			info(lblTitle.getText() + " :: contains :: " + title);
		} catch (Throwable e) {
			fatal(lblTitle.getText() + " :: not contains :: " + title);
		}
	}
	
	/*
	 * Запуск теста скачивания торрент файла; получения из файла строки с описанием торрента и сравнение с url страницы
	 */
	public void testDownload() {
		String url = browser.getDriver().getCurrentUrl();
		InputStream in = null;
		try {
			in = FileThruScripts.download(browser.getDriver(), btnDownload.getElement().getAttribute("href"));
			String torrUrl = decodeTorrent(in);
			info(torrUrl + ":: is compared with :: " + url);
			Assert.assertEquals(torrUrl, url);
			info("Correct .torrent file");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	public void clickAnswer() {
		btnAnswer.click();
	}
	
	/*
	 * Декодирование файла со stream'а и получение строки с описанием("comment")
	 */
	@SuppressWarnings("rawtypes")
	private String decodeTorrent(InputStream in) throws IOException {
		BDecoder decoder = new BDecoder();
		Map bdecode = decoder.decodeStream(new BufferedInputStream(in));
		return new String((byte[]) bdecode.get("comment"));
	}
}
