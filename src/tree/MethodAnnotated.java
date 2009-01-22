package tree;

/** Placeholder when the target that must be 
 * annotated is a Method.
 */
public class MethodAnnotated implements Operator {
	
	public MethodAnnotated(String name) {
		
	}
	
	/** The required annotation; sadly we cannot
	 * use java.lang.annotation.Annotation as its type.
	 */
	Class<?> annotation;

	public boolean process(Class<?> c) {
		// TODO Auto-generated method stub
		return false;
	}
}
