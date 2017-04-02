package com.dobriy.utils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public abstract class FileThruScripts {

	@SuppressWarnings("unchecked")
	public static InputStream download(WebDriver driver, String url) throws IOException {
		driver.manage().timeouts().setScriptTimeout(15, TimeUnit.SECONDS);
		String script = "var url = arguments[0];" + "var callback = arguments[arguments.length - 1];"
				+ "var xhr = new XMLHttpRequest();" + "xhr.open('GET', url, true);"
				+ "xhr.responseType = \"arraybuffer\";" + "xhr.onload = function() {"
				+ "  var arrayBuffer = xhr.response;" + "  var byteArray = new Uint8Array(arrayBuffer);"
				+ "  callback(byteArray);" + "};" + "xhr.send();";
		Object response = ((JavascriptExecutor) driver).executeAsyncScript(script, url);
		ArrayList<Long> byteList = (ArrayList<Long>) response;
		byte[] bytes = new byte[byteList.size()];
		for (int i = 0; i < byteList.size(); i++) {
			bytes[i] = (byte) (long) byteList.get(i);
		}
		return new ByteArrayInputStream(bytes);
	}
}
