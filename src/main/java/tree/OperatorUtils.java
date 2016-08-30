package tree;

/** Static values, for e.g. testing AND and OR classes */
public abstract class OperatorUtils {
	
	public static final Operator TRUE = new Operator() {
		public boolean process(Class<?> c) {
			return true;
		}
	};

	public static final Operator FALSE = new Operator() {
		public boolean process(Class<?> c) {
			return false;
		}
	};
}
