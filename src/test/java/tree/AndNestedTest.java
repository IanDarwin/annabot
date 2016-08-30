package tree;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import static tree.OperatorUtils.TRUE;

public class AndNestedTest {
	Operator x = new And(
			TRUE,
			new And(TRUE, TRUE)
	);
	
	@Test
	public void testAndAnd() {
		assertTrue(x.process(null));
	}
}
