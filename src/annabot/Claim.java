package annabot;

import tree.Operator;

/** A Claim is a Composite of a bunch
 * of Operators, making up the Assertion
 * about one API.
 */
public abstract class Claim {
	public abstract Operator[] getClassFilter();
	public abstract Operator[] getOperators();
}
