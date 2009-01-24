package tree;

import java.lang.annotation.Annotation;
import java.lang.reflect.AccessibleObject;
import java.util.regex.Pattern;

/** Base for {Class,Method,Field}Annotated classes */
public class Annotated {
	protected String annotationName;
	protected Pattern regex;
	protected Class<? extends Annotation> annotationClass;

	/** Return true if the given annotation name
	 * matches the annotation pattern that this
	 * Annotated was constructed with.
	 * @param annName
	 * @return
	 */
	protected boolean annotationNameMatches(String annName) {
		return regex.matcher(annName).matches();
	}

	/**
	 * Annotations' toString() returns e.g.,
	 * '@demo.MyAnnotation(useless=true)'; this method
	 * extracts just the class name part.
	 * @param annotation
	 * @return
	 */
	protected String annotationClassName(Annotation annotation) {
		String string = annotation.toString();
		String annName = string.substring(1).replaceFirst("\\(.*", "");
		return annName;
	}

	/** Return true if the given AccessibleObject
	 * (method or field) is annoted with the annotation
	 * given in this Annotated's constructor. 
	 */
	protected boolean isAnnotated(AccessibleObject o) {
		if (annotationClass != null) {
			return(o.isAnnotationPresent(annotationClass));
		} else {
			final Annotation[] classAnnotations = o.getDeclaredAnnotations();
			for (Annotation annotation : classAnnotations) {
				if (annotationNameMatches(annotationClassName(annotation))) {
					return true;
				}				
			}
		}
		return false;
	}
}
