package demo;

import annabot.Claim;
import tree.*;

public class JPAEmbeddableNoIdClaim extends Claim {
	
	public String getDescription() {
		return "JPA Embeddables may not have @Id";
	}
	public Operator[] getClassFilter() {
		return new Operator[] {
			new ClassAnnotated("javax.persistence.Embeddable"),	
		};
	}

	public Operator[] getOperators() {
		return new Operator[] {
			new None(
					new ClassAnnotated("javax.persistence.Id"),
					new MethodAnnotated("javax.persistence.Id"))
		};
	}
}
