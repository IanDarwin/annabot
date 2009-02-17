package annabot;

import tree.Operator;

/** A Claim is a Composite of a bunch
 * of Operators, making up one Assertion
 * about one API.
 */
public abstract class Claim {
	/**
	 * @return The description of the problem this claim finds;
	 * a better name might have been getErrorText().
	 */
	public abstract String getDescription();
	
	/**
	 * @return A list of Operators that are the "if" part of
	 * a claim; if any of these operators fails, the class
	 * will be bypassed and considered successful for this claim.
	 */
	public abstract Operator[] getClassFilter();

	/**
	 * @return a loist of Operators, all of which must return
	 * true for this class to be considered successful against
	 * this claim.
	 * Only used if the Operators in getClassFilter all pass.
	 */
	public abstract Operator[] getOperators();

}
