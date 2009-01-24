package tree;

/** Toys for testing AND and OR classes */
public abstract class LogicBaseTest {
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
