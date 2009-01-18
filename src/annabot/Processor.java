package annabot;

import tree.Conjunction;
import tree.MethodAnnotated;
import tree.Operator;
import tree.RequiresOne;

public class Processor {
	Operator[] checks;
	public Processor(Operator[] checks) {
		super();
		this.checks = checks;
	}
	public boolean process(Class<?> target, Operator[] checks) {
		return true;
	}
	public boolean process(Class<?> target) {
		return process(target, checks);
	}
	public static void main(String[] args) {
		Operator[] checks = {
				Conjunction.AND,
				new RequiresOne(),
				new MethodAnnotated()
		};
		Processor p = new Processor(checks);
		p.process(Object.class);
	}
}
