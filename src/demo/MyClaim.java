package demo;

import annabot.Claim;
import tree.*;

public class MyClaim implements Claim {
	Operator[] f = {
		new ClassAnnotated("javax.persistence.Entity"),	
	};

	Operator[] d = {
		new AtMostOne(
				new ClassAnnotated("javax.persistence.*"),
				new MethodAnnotated("javax.persistence.*"))
	};

	public Operator[] getClassFilter() {
		return f;
	}

	public Operator[] getOperators() {
		return d;
	}
}
