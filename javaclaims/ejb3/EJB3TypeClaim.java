package ejb3;

import javax.ejb.MessageDriven;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.persistence.Entity;

import tree.AtMostOne;
import tree.ClassAnnotated;
import tree.Operator;
import annabot.Claim;

public class EJB3TypeClaim extends Claim {

	@Override
	public Operator[] getClassFilter() {
		// no filter needed, it's light-weight
		return null;
	}

	@Override
	public String getDescription() {
		return "Class has conflicting top-level EJB annotations";
	}

	@Override
	public Operator[] getOperators() {
		return new Operator[] {
		    new AtMostOne(
		    		new ClassAnnotated(Entity.class), 
		    		new ClassAnnotated(Stateless.class), 
		    		new ClassAnnotated(Stateful.class),
		    		new ClassAnnotated(MessageDriven.class))
		};
	}
}
