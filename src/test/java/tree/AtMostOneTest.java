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
 * JUnit test for the "AtMostOne" Operator.
 */
@RunWith(value=Parameterized.class)
public class AtMostOneTest {

	/** This method provides data to the constructor for use in tests */
	@Parameters
	public static List<Object[]> data() {
		final Object[][] data = new Object[][] {
				{ true, new Operator[] { TRUE } },
				{ true, new Operator[] { FALSE } },
				{ true, new Operator[] { FALSE, TRUE } },
				{ false, new Operator[] { TRUE, FALSE, TRUE } },
				{ false, new Operator[] { TRUE, TRUE, FALSE } },
		};
		return Arrays.asList(data);
	}

	private boolean expected;
	Operator[] data;

	/** Constructor, gets arguments from data array; cast as needed */
	public AtMostOneTest(Object expected, Object value) {
		this.expected = (Boolean) expected;
		this.data = (Operator[])value;
	}

	@Test public void testPositive() {
		// Any class will do as the "Class" arg here as long as
		// all the Operators from data() are TRUE or FALSE
		assertEquals(expected, new AtMostOne(data).process(getClass()));
	}
}
