package violations;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;

/** 
 * This class intentionally violates the
 * JPAEntityMethFieldClaim for testing it.
 */
@Entity
public class JPAEntityMethodFieldAnnotated {
	int id;
	@Basic
	String name;
	@Id
	public int getId() {
		return id;
	}
}
