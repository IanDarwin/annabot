package tree;

import java.lang.annotation.Annotation;
import java.util.regex.Pattern;

/** Placeholder when the target that must be 
 * annotated is a Class.
 */
public class ClassAnnotated implements Operator {
	
	private final String annotationName;
	private final Pattern regex;
	private final Class<? extends Annotation> annotationClass;

	public ClassAnnotated(String name) {
		this.annotationName = name;
		this.regex = Pattern.compile(name.replaceAll("\\*", ".*"));
		this.annotationClass = null;
	}

	public ClassAnnotated(Class<? extends Annotation> cl) {
		this.annotationName = null;
		this.regex = null;
		this.annotationClass = cl;
	}

	public boolean process(Class<?> c) {
		final String clazzName = c.getName();
		if (annotationName != null) {
			final Annotation[] classAnnotations = c.getDeclaredAnnotations();
			for (Annotation annotation : classAnnotations) {
				String string = annotation.toString();
				String annName = string.substring(1).replaceFirst("\\(.*", "");
				if (match(annName)) {
					return true;
				}				
			}
			return false;
		} else {
			return null == c.getAnnotation(annotationClass);
		}
	}

	private boolean match(String annName) {
		return regex.matcher(annName).matches();
	}
}
