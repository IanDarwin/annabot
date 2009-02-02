package jpa;

import annabot.Claim;
import tree.*;

/** 
 * Only for testing that NO fields are annotated.
 */
public class AdHocPAEntityClaim extends Claim {
	
	public String getDescription() {
		return "Warning about Field-based annotations";
	}
	public Operator[] getClassFilter() {
		return new Operator[] {
			new ClassAnnotated("javax.persistence.Entity"),	
		};
	}

	public Operator[] getOperators() {
		return new Operator[] {
				new None(
						new FieldAnnotated("javax.persistence.*"))
		};
	}
}
