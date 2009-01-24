package demo;

import annabot.Claim;
import tree.*;

public class JPAEntityClaims extends Claim {
	
	public Operator[] getClassFilter() {
		return new Operator[] {
			new ClassAnnotated("javax.persistence.Entity"),	
		};
	}

	public Operator[] getOperators() {
		return new Operator[] {
			new AtMostOne(
					new ClassAnnotated("javax.persistence.*"),
					new MethodAnnotated("javax.persistence.*"))
		};
	}
}
