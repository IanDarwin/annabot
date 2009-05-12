package jpa;

import annabot.Claim;
import tree.*;

public class JPAEntityHasNoArgConstructor extends Claim {
	
	public String getDescription() {
		return "JPA Entities must have a no-arg constructor";
	}
	public Operator[] getClassFilter() {
		return new Operator[] {
			new ClassAnnotated(javax.persistence.Entity.class),	
		};
	}

	public Operator[] getOperators() {
		return new Operator[] {
			new RequiresOne(
				new HasNoArgumentConstructor()
			)
		};
	}
}
