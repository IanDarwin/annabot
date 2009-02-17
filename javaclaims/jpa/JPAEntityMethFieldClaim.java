package jpa;

import annabot.Claim;
import tree.*;

public class JPAEntityMethFieldClaim extends Claim {
	
	public String getDescription() {
		return "JPA Entities may have field OR method annotations, not both";
	}
	public Operator[] getClassFilter() {
		return new Operator[] {
			new ClassAnnotated("javax.persistence.Entity"),	
		};
	}

	public Operator[] getOperators() {
		return new Operator[] {
			// make sure they don't annotate both methods and fields
			new AtMostOne(
					new FieldAnnotated("javax.persistence.*"),
					new MethodAnnotated("javax.persistence.*")),
			// make sure if they annotate methods, it's not setters
			new None(
					new MethodAnnotated("javax.persistence.*", "set*"))
		};
	}
}
