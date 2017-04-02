package webdriver;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;
import webdriver.Browser;

public class TestListener extends TestListenerAdapter {
	
	@Override
	public void onTestFailure(ITestResult result){
		makeScreenshot(result);
	}

	/*
	 * Получение скриншота всей страницы, сохранение его в папку с репортом и добавление ссылки в HTML Report
	 */
	private void makeScreenshot(ITestResult result){
		Screenshot aShot =new AShot().shootingStrategy(ShootingStrategies.viewportPasting(100)).takeScreenshot(Browser.getInstance().getDriver());
		String url =".//surefire-reports/screenshots/"+LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm-ss"))+".png";
		new File(".//surefire-reports/screenshots/").mkdirs();
		File file = new File(url);
		try {
			ImageIO.write(aShot.getImage(), "PNG", file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String st = "<br><a href='"+ file.getAbsolutePath()+"'> <img src='"+ file.getAbsolutePath()+"' height='250' width='400'/> </img></a><br>";
		Reporter.log(st);
	}
}
