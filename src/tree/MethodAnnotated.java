package tree;

/** Placeholder when the target that must be 
 * annotated is a Method.
 */
public class MethodAnnotated implements Operator {
	
	/** The required annotation; sadly we cannot
	 * use java.lang.annotation.Annotation as its type.
	 */
	Class<?> annotation;
}
