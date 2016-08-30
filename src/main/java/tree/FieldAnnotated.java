package tree;

import java.lang.annotation.Annotation;
import java.lang.reflect.AccessibleObject;
import java.util.regex.Pattern;

/** Operator to determine whether the given
 * Class has at least one method with the
 * given Annotation.
 */
public class FieldAnnotated extends Annotated implements Operator {
	
	public FieldAnnotated(String name) {
		this.annotationName = name;
		this.annotationPattern = Pattern.compile(name.replaceAll("\\*", ".*"));
		this.annotationClass = null;
	}

	public FieldAnnotated(Class<? extends Annotation> cl) {
		this.annotationName = null;
		this.annotationPattern = null;
		this.annotationClass = cl;
	}

	public boolean process(Class<?> c) {
		for (AccessibleObject o : c.getFields()) {
			if (isAnnotated(o))
				return true;
		}
		return false;
	}
}
