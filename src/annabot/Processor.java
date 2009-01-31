package annabot;

import tree.Operator;

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
	
	/** Process a given class with the provided list of Operators */
	public boolean process(Class<?> target, Claim claim) {
		if (claim == null) {
			throw new NullPointerException(
				"Claim may not be null");
		}
		
		// If the Claim does not apply to this class,
		// it is not an error, so return true.
		final Operator[] classFilter = claim.getClassFilter();
		if (classFilter != null) {
			for (Operator check: classFilter) {
				if (!check.process(target)) {
					return true;
				}
			}
		}
		// Run the actual tests
		for (Operator check: claim.getOperators()) {
			if (!check.process(target)) {
				reporter.report(target, claim);
				return false;
			}
		}
		return true;
	}
	
	/** Process a given class with the default list of Operators */
	public boolean process(Class<?> target) {
		return process(target, claim);
	}
}
