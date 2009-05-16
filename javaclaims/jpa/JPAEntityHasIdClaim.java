package jpa;

import tree.ClassAnnotated;
import tree.FieldAnnotated;
import tree.MethodAnnotated;
import tree.Operator;
import tree.Or;
import annabot.Claim;

/** 
 * Test that JPA Entity has an @Id.
 */
public class JPAEntityHasIdClaim extends Claim {
	
	public String getDescription() {
		return "JPA Entity must have an ID";
	}
	public Operator[] getClassFilter() {
		return new Operator[] {
			new ClassAnnotated("javax.persistence.Entity"),	
		};
	}

	public Operator[] getOperators() {
		return new Operator[] {
			new Or(
					new FieldAnnotated(javax.persistence.Id.class),
					new MethodAnnotated(javax.persistence.Id.class))
		};
	}
}
