package annabot;

import tree.Operator;

/** A Mediator for the Claim classes
 * and the targets being verified.
 */
public class Processor {
	
	Claim claim;
	
	static Reporter reporter = new SystemReporter();
	
	public Processor() {
		// empty
	}
	
	public Processor(Claim checks) {
		super();
		this.claim = checks;
	}
	
	/** Process a given class with the constructed-in Claim (list of Operators) */
	public boolean process(Class<?> target) {
		return process(target, claim);
	}
	
	/** Process a given class with the provided Claim (list of Operators) */
	public boolean process(Class<?> target, Claim claim) {
		if (claim == null) {
			throw new NullPointerException(
				"Claim may not be null");
		}
		
		// If the Claim does not apply to this class,
		// it is not an error, so return true
		// from the whole method, indicating "OK"ness.
		final Operator[] classFilter = claim.getClassFilter();
		if (classFilter != null) {
			for (Operator check: classFilter) {
				if (!check.process(target)) {
					return true;
				}
			}
		}
		// Run the actual tests; if any of these fails.
		// return false as the test has failed.
		for (Operator check: claim.getOperators()) {
			if (!check.process(target)) {
				reporter.report(target, claim);
				return false;
			}
		}
		return true;
	}
	
	@Override
	public String toString() {
		return "Processor(" + claim.getClass() + ")";
	}
}
