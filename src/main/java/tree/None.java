package tree;

public class None implements Operator {

	Operator[] operators;	

	public None(Operator ... operators) {
		this.operators = operators;
	}

	/** Walk the list of constructed-in Operators and try each one;
	 * for "None", if any one is true, the whole thing is false.
	 */
	public boolean process(Class<?> c) {
		for (Operator o : operators) {
			if (o.process(c)) {
				return false;
			}
		}
		return true;
	}
}
