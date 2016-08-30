package examplefailures;

import javax.persistence.*;

/**
 * This is the "flawed" example from Figure Two of the paper.
 * The JPAEntityClaim file should find fault with it.
 */
@Entity public class Figure2 {
	@Id int id;
	String firstName;
	@Column(name="given_name") // DO NOT MOVE but should be before field, not getter.
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String fn) {
		firstName = fn;
	}
}
