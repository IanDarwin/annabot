package violations;

import javax.persistence.Entity;

/**
 * This class intentionally violates the rule
 * that a JPA Entity must have a no-argument
 * constructor, for testing purposes.
 */
@Entity
public class JPAEntityNoArgConstructor {
	public JPAEntityNoArgConstructor(String name) {
		// empty
	}
}
