package annabot;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import javax.persistence.Column;
import javax.persistence.Id;

import demo.MyAnnotation;

/*
 * Annotations Assertion Based Object Testing for Java
 * Version 0.0 testbase - a few hard-coded claims.
 * Process each class, looking for conflicting uses
 * of JPA annotations: if you annoted both fields and
 * getters, one set or another will be ignored.
 */
public class AnnaBot1 {

	@MyAnnotation
	@Id
	@SuppressWarnings("unused")
	private int i;

	@MyAnnotation
	@Column(name="wah wah")
	public static void main(String[] args) {
		if (args.length == 0) {
			System.out.println( "Usage: AnnaBot1 className [...]");
			return;
		}
		for (String name : args) {
			try {
				process(name);
			} catch (ClassNotFoundException e) {
				System.err.println("Could not load class " + name);
				System.err.println("Cause: " + e);
			}
		}
	}

	/** Process a class whose name is given, replacing / with . and
	 * removing trailing ".class" if present.
	 * @param className
	 * @throws ClassNotFoundException
	 */
	public static void process(String className) throws ClassNotFoundException {
		final String realClassName = className.replace('/', '.').replaceAll(".class$", "");
		Class<?> c = Class.forName(realClassName);
		process(c);
	}

	/**
	 * Process one class, checking its annotations.
	 * @param c
	 */
	public static void process(Class<?> c) {
		System.out.println("Starting class " + c);
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
