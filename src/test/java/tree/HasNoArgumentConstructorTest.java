package tree;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.FileReader;
import java.util.Date;

import org.junit.Test;

public class HasNoArgumentConstructorTest {

	Operator t = new HasNoArgumentConstructor();
	
	@Test
	public final void testPositive() {
		// java.util.Date does have a no-arg constructor
		assertTrue("HasNoArgumentConstructorTest.testPositive()", t.process(Date.class));
	}
	
	@Test
	public final void testNegative() {
		// java.io.FileReader does not (nor is ever likely to)
		assertFalse("HasNoArgumentConstructorTest.testPositive()", t.process(FileReader.class));
	}

}
