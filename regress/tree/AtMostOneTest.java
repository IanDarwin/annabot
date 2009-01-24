package tree;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/*
 * This is a JUnit test for the "AtMostOne" Operator.
 */
@RunWith(value=Parameterized.class)
public class AtMostOneTest extends LogicBaseTest {

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

	Operator[] data;
	private boolean expected;

	/** Constructor, gets arguments from data array; cast as needed */
	public AtMostOneTest(Object expected, Object value) {
		this.data = (Operator[])value;
		this.expected = (Boolean) expected;
	}

	@Test public void testPositive() {
		// Any class will do as the 2nd arg here
		assertEquals(expected, new AtMostOne(data).process(getClass()));
	}
}
