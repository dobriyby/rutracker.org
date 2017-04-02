package webdriver.decorator;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;


/*
 * Аннатация для получения titleName элемента 
 */
@Retention(RUNTIME)
@Target({ TYPE, FIELD })
public @interface Name {
	String title() default "";
}
