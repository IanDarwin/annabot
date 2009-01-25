package annabot;

import tree.Operator;

public class Processor {
	Operator[] checks;
	public Processor() {
		// empty
	}
	public Processor(Operator[] checks) {
		super();
		this.checks = checks;
	}
	/** Process a given class with  the default list of Operators */
	public boolean process(Class<?> target, Operator[] checks) {
		if (checks == null) {
			throw new IllegalStateException(
			"Must provide Operator[] to constructor or to process() method"
			);
		}
		for (Operator check: checks) {
			System.out.println(check.process(target));
		}
		return true;
	}
	/** Process a given class with a non-default list of Operators */
	public boolean process(Class<?> target) {
		return process(target, checks);
	}
}
