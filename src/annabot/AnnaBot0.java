package annabot;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import javax.persistence.Column;
import javax.persistence.Id;

import demo.MyAnnotation;

/*
 * Very simple toy version, just to show checking annotations code
 */
public class AnnaBot1 {

	@MyAnnotation
	@Id
	int i;

	@MyAnnotation
	@Column(name="wah wah")
	public static void main(String[] args) throws Exception {
		if (args.length == 0) {
			System.out.println( "Usage: AnnaBot1 className [...]");
			return;
		}
		for (String name : args) {
			process(name);
		}
	}

	public static void process(String className) throws ClassNotFoundException {
		Class<?> c = Class.forName(className);
		process(c);
	}

	public static void process(Class<?> c) {
		// Don't use getDeclaredXXX() here, we do care about
		// inherited methods/fields that are annotated.
		Field[] fields = c.getDeclaredFields();
		Method[] methods = c.getDeclaredMethods();
		boolean fieldHasJpaAnno = false, methodHasJpaAnno = false;

		for (Field field : fields) {
			Annotation[] ann = field.getDeclaredAnnotations();
			for (Annotation a : ann) {
				Package pkg = a.annotationType().getPackage();
				if (pkg != null && pkg.getName().startsWith("javax.persistence")) {
					fieldHasJpaAnno = true;
					break;
				}
			}
		}

		for (Method method : methods) {
			Annotation[] ann = method.getDeclaredAnnotations();
			for (Annotation a : ann) {
				Package pkg = a.annotationType().getPackage();
				if (pkg != null && pkg.getName().startsWith("javax.persistence")) {
					methodHasJpaAnno = true;
					break;
				}
			}
		}

		if (fieldHasJpaAnno && methodHasJpaAnno) {
			System.err.printf("Class %s has JPA annotations both on field(s) and on method(s).", c.getName());
		}
	}
}
