package webdriver.decorator;

import java.lang.reflect.Field;

import org.openqa.selenium.support.pagefactory.Annotations;

/*
 * Расширенный класс Annotation для работы с дополнительной аннотацией Name.class 
 */
public class CustomAnnotation extends Annotations{
	private Field field;

	public CustomAnnotation(Field field) {
		super(field);
		this.field=field;
	}
	
	public String buildTitle(){
		return field.getAnnotation(Name.class).title();
	}


}
