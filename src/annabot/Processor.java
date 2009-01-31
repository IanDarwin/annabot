package annabot;

import tree.Operator;

public class Processor {
	Claim checks;
	
	public Processor() {
		// empty
	}
	
	public Processor(Claim checks) {
		super();
		this.checks = checks;
	}
	
	/** Process a given class with the provided list of Operators */
	public boolean process(Class<?> target, Claim checks) {
		if (checks == null) {
			throw new IllegalStateException(
			"Must provide Operator[] to constructor or to process() method"
			);
		}
		
		// If the Claim does not apply to this class,
		// it is not an error, so return true.
		final Operator[] classFilter = checks.getClassFilter();
		if (classFilter != null) {
			for (Operator check: classFilter) {
				if (!check.process(target)) {
					return true;
				}
			}
		}
		// Run the actual tests
		for (Operator check: checks.getOperators()) {
			if (!check.process(target))
				return false;
		}
		return true;
	}
	
	/** Process a given class with the default list of Operators */
	public boolean process(Class<?> target) {
		return process(target, checks);
	}
}
