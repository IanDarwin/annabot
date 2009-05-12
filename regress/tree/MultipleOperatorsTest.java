package tree;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import annabot.Claim;
import annabot.Processor;

public class MultipleOperatorsTest {

	final Claim fakeClaim = new Claim() {

		@Override
		public Operator[] getClassFilter() {
			return null;
		}

		@Override
		public String getDescription() {
			return "Audacious forgery of a real Claim";
		}

		final Operator[] operators = new Operator[] {
				new HasNoArgumentConstructor(),
				new HasNoArgumentConstructor()
		};
		
		@Override
		public Operator[] getOperators() {
			return operators;
		}		
	};
	
	@Test
	public void testTwoOperatorsGood() {
		Processor p = new Processor(fakeClaim);
		assertTrue(p.process(getClass()));
	}
	
	@Test
	public void testTwoOperatorsOneBadFailure() {
		Processor p = new Processor(fakeClaim);
		// It is safe to modify this because JUnit creates
		// a new instance for each test method
		fakeClaim.getOperators()[1] = new None(
				new HasNoArgumentConstructor()
		);
		assertFalse(p.process(getClass()));
	}
}
