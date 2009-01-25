package tree;

import static org.junit.Assert.assertEquals;
import static tree.OperatorUtils.FALSE;
import static tree.OperatorUtils.TRUE;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/*
 * This is a JUnit test for the "Or" Operator class.
 */
@RunWith(value=Parameterized.class)
public class OrTest {

	/** This method provides data to the constructor for use in tests */
	@Parameters
	public static List<Object[]> data() {
		final Object[][] data = new Object[][] {
				{ true, new Operator[] { TRUE } },
				{ true, new Operator[] { TRUE, TRUE, TRUE } },
				{ false, new Operator[] { FALSE } },
				{ true, new Operator[] { FALSE, TRUE } },
		};
		return Arrays.asList(data);
	}

	Operator[] data;
	private boolean expected;

	/** Constructor, gets arguments from data array; cast as needed */
	public OrTest(Object expected, Object value) {
		this.data = (Operator[])value;
		this.expected = (Boolean) expected;
	}

	@Test public void testPositive() {
		// Any class will do as the 2nd arg here
		assertEquals(expected, new Or(data).process(getClass()));
	}
}
