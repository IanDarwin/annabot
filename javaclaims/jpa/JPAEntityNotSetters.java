package jpa;

import javax.persistence.Entity;

import annabot.Claim;
import tree.*;

public class JPAEntityNotSetters extends Claim {
	
	public String getDescription() {
		return "JPA Entities may have field OR method annotations, not both";
	}
	public Operator[] getClassFilter() {
		return new Operator[] {
			new ClassAnnotated(Entity.class),	
		};
	}

	public Operator[] getOperators() {
		return new Operator[] {
			// make sure if they annotate methods, it's not setters
			new None(
					new MethodAnnotated("javax.persistence.*", "set*"))
		};
	}
}
