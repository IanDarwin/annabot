package tree;


/** Placeholder when the target that must be 
 * annotated is a Class.
 */
public class AtMostOne implements Operator {

	Operator[] operators;
		
	public AtMostOne(Operator ... operators) {
		this.operators = operators;
	}
	
	public boolean process(Class<?> c) {
		int numTrue = 0;
		for (Operator o : operators) {
			if (o.process(c)) {
				numTrue++;
			}
		}
		return numTrue <= 1;
	}
}
