/* Master AllTests for AnnaBot */

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(value=Suite.class)
@SuiteClasses(value={
	annabot.AllTests.class,
	tree.AllTests.class,
})
public class AnnaBotTests {
	// No Code Needed
}

