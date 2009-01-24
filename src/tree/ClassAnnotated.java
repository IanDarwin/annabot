package tree;

import java.lang.annotation.Annotation;
import java.util.regex.Pattern;

/** Placeholder when the target that must be 
 * annotated is a Class.
 */
public class ClassAnnotated  extends Annotated implements Operator {
	
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
}
