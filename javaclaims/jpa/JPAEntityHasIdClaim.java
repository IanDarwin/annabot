package jpa;

import tree.ClassAnnotated;
import tree.FieldAnnotated;
import tree.MethodAnnotated;
import tree.Operator;
import tree.RequiresOne;
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
			new RequiresOne(
					new FieldAnnotated("javax.persistence.Id"),
					new MethodAnnotated(javax.persistence.Id.class))
		};
	}
}
