package demo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MyEntityBad {
	@Id int id;
	String name = "Fred";
	@Column 
	public String getName() { return name; }
}
