package webdriver.decorator;

import java.lang.reflect.Field;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.DefaultFieldDecorator;

public class CustomFieldDecorator extends DefaultFieldDecorator {

	public CustomFieldDecorator(final SearchContext searchContext) {
		super(new DefaultElementLocatorFactory(searchContext));
	}

	public Object decorate(ClassLoader loader, Field field) {
		Class<?> decoratableClass = decoratableClass(field);
		if (decoratableClass != null) {
			By locator = new CustomAnnotation(field).buildBy();
			String title = new CustomAnnotation(field).buildTitle();
			if (locator == null) {
				return null;
			}
			if (title == null) {
				return null;
			}
			return createInstance(decoratableClass, locator, title);
		}
		return null;
	}

	private Class<?> decoratableClass(Field field) {
		Class<?> clazz = field.getType();
		try {
			clazz.getConstructor(By.class, String.class);
		} catch (Exception e) {
			return null;
		}
		return clazz;
	}

	private <T> T createInstance(Class<T> clazz, By locator, String title) {
		try {
			return (T) clazz.getConstructor(By.class, String.class).newInstance(locator, title);
		} catch (Exception e) {
			throw new AssertionError("WebElement can't be represented as " + clazz);
		}
	}
}
