package violations;

import javax.persistence.Entity;
import javax.persistence.Id;

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
	@Id
	public int getId() {
		return 42;
	}
}
