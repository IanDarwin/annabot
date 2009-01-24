package tree;

import java.lang.annotation.Annotation;
import java.util.regex.Pattern;

/** Placeholder when the target that must be 
 * annotated is a Method.
 */
public class FieldAnnotated  extends Annotated implements Operator {
	
	public FieldAnnotated(String name) {
		this.annotationName = name;
		this.regex = Pattern.compile(name.replaceAll("\\*", ".*"));
		this.annotationClass = null;
	}

	public FieldAnnotated(Class<? extends Annotation> cl) {
		this.annotationName = null;
		this.regex = null;
		this.annotationClass = cl;
	}


	public boolean process(Class<?> c) {
		// TODO Auto-generated method stub
		return false;
	}
}
