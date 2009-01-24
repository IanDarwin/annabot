package tree;

import java.lang.annotation.Annotation;
import java.util.regex.Pattern;

/** Base for {Class,Method,Field}Annotated classes */
public class Annotated {
	protected String annotationName;
	protected Pattern regex;
	protected Class<? extends Annotation> annotationClass;

	protected boolean match(String annName) {
		return regex.matcher(annName).matches();
	}
}
