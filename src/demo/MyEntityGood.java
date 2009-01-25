package demo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MyEntityGood {
	@Id int id;
	@Column String name = "Fred";
	public String getName() { return name; }
}
