package tree;

/**
 * Collect a list of Operators; our process() returns
 * true IF at least one of them returns true.
 * Side effects? the Operators are evaluated in the order 
 * they are given in the constructor.
 */
public class Or implements Operator {

	Operator[] operators;

	public Or(Operator ... operators) {
		this.operators = operators;
	}

	public boolean process(Class<?> c) {
		for (Operator o : operators) {
			if (o.process(c)) {
				return true;
			}
		}
		return false;
	}
}
