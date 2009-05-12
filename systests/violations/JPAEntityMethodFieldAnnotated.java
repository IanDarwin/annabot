package violations;

import javax.persistence.Entity;
import javax.persistence.Id;

/** 
 * This class intentionally violates the
 * JPAEntityMethFieldClaim for testing it.
 */
@Entity
public class JPAEntityMethodFieldAnnotated {
	int id;
	@Id
	public void setId(int id) {
		this.id = id;
	}
}
