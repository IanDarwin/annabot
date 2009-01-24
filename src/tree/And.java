package tree;

/** Return false unless all ops are true */
public class And implements Operator {

	Operator[] operators;
	
	public And(Operator ... operators) {
		this.operators = operators;
	}

	public boolean process(Class<?> c) {
		for (Operator o : operators) {
			if (!o.process(c)) {
				return false;
			}
		}
		return true;
	}
}
