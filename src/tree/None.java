package tree;

public class None implements Operator {

	Operator[] operators;	

	public None(Operator ... operators) {
		this.operators = operators;
	}
	
	public boolean process(Class<?> c) {
		int numTrue = 0;
		for (Operator o : operators) {
			if (o.process(c)) {
				numTrue++;
			}
		}
		return numTrue == 0;
	}
}
