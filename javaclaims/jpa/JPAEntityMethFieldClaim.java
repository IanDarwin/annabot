package demo;

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
			new AtMostOne(
					new ClassAnnotated("javax.persistence.*"),
					new MethodAnnotated("javax.persistence.*"))
		};
	}
}
