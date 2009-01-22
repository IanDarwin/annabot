package annabot;

import tree.Operator;

/** A Claim is a Composite of a bunch
 * of Operators, making up the Assertion
 * about one API.
 */
public interface Claim {
	public Operator[] getClassFilter();
	public Operator[] getOperators();
}
