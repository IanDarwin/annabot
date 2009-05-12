package violations;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;

/** 
 * This class intentionally violates the
 * JPAEntityMethFieldClaim for testing it.
 */
@Entity
public class JPAEntitySetMethodAnnotated {
	int id;
	@Basic
	String name;
	@Id
	public void setId(int id) {
		this.id = id;
	}
}
