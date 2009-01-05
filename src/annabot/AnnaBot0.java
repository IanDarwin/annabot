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
		String clName = args[0];
		Class<?> c = Class.forName(clName);
		// Don't use getDeclaredXXX() here, we do care about
		// inherited methods/fields that are annotated.
		Field[] fields = c.getDeclaredFields();
		Method[] methods = c.getDeclaredMethods();
		boolean fieldHasJpaAnno = false, methodHasJpaAnno = false;
		for (Field field : fields) {
			System.out.println(field);
			Annotation[] ann = field.getDeclaredAnnotations();
			for (Annotation a : ann) {
				System.out.println(field.getName() + "--" + a);
				Package pkg = a.getClass().getPackage();
				if (pkg != null && pkg.getName().startsWith("javax.persistence")) {
					fieldHasJpaAnno = true;
					System.out.println("1");
					break;
				}
			}
		}
		for (Method method : methods) {
			System.out.println(method);
			Annotation[] ann = method.getDeclaredAnnotations();
			for (Annotation a : ann) {
				System.out.println(method.getName() + "--" + a);
				Package pkg = a.getClass().getPackage();
				if (pkg != null && pkg.getName().startsWith("javax.persistence")) {
					methodHasJpaAnno = true;
					System.out.println("2");
					break;
				}
			}
		}
		if (fieldHasJpaAnno && methodHasJpaAnno) {
			System.err.printf("Class %s has JPA annotations both on field(s) and on method(s).", c);
		}
	}
}
