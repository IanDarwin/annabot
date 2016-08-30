package tree;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.regex.Pattern;

/** Placeholder when the target that must be 
 * annotated is a Method.
 */
public class MethodAnnotated extends Annotated implements Operator {

	Pattern methodRegex;
	
	public MethodAnnotated(String annotationName, String methodPattern) {
		if (methodPattern != null) {
			this.methodRegex = 
				Pattern.compile(methodPattern.replaceAll("\\*", ".*"));
		}
		this.annotationName = annotationName;
		// They are entered as wildcard but we want regex
		this.annotationPattern = Pattern.compile(annotationName.replaceAll("\\*", ".*"));
		this.annotationClass = null;
	}
	public MethodAnnotated(String annotationName) {
		this(annotationName, null);
	}

	public MethodAnnotated(Class<? extends Annotation> cl, String methodPattern) {
		if (methodPattern != null) {
			this.methodRegex = 
				Pattern.compile(methodPattern.replaceAll("\\*", ".*"));
		}
		this.annotationName = null;
		this.annotationPattern = null;
		this.annotationClass = cl;
	}

	public MethodAnnotated(Class<? extends Annotation> cl) {
		this(cl, null);
	}

	public boolean process(Class<?> c) {
		for (Method m : c.getMethods()) {
			if (methodRegex != null) {
				String name = m.getName();
				if (!methodRegex.matcher(name).find())
					continue;
			}
			if (isAnnotated(m))
				return true;
		}
		return false;
	}
}
