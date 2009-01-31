package tree;

public class None implements Operator {

	Operator[] operators;	

	public None(Operator ... operators) {
		this.operators = operators;
	}
	
	public boolean process(Class<?> c) {
		for (Operator o : operators) {
			// If any one is true, the whole thing is false.
			if (o.process(c)) {
				return false;
			}
		}
		return true;
	}
}
