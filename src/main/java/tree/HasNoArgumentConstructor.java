package tree;

/** 
 * Check whether a given class has a no-argument constructor.
 * Technically has little to do with Annotations but 
 * makes for more-nearly-complete specification checking.
 */
public class HasNoArgumentConstructor implements Operator {

	public boolean process(Class<?> c) {
		try {
			// Find the constructor that takes no args
			c.getConstructor(new Class<?>[0]);
			return true;
		} catch (Exception e) {
			// System.out.println(e);
			return false;
		}
	}

}
