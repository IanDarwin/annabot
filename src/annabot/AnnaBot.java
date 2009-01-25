package annabot;

import com.darwinsys.io.SourceType;
import com.darwinsys.io.SourceUtils;


public class AnnaBot {

	private static void doHelp(int i) {
		final String usage =
			"Usage: AnnaBot claims classes";
		System.err.println(usage);
		System.exit(i);
	}
		
	
	
	/**
	 * Annotations Assertion Based Object Testing for Java
	 * A version in which the user provides a pre-compiled
	 * parse list and a package name. The DSL is effectively
	 * a set of Constructor calls.
	 * @param args[0] - claims (classname, directory or jar)
	 * @param args[1] - classes to test (classname, directory or jar).
	 */
	public static void main(String[] args) throws Exception {
		if (args.length != 2) {
			doHelp(1);
		}
		String claims = args[0];
		SourceType claimType = SourceUtils.classify(claims);
		String classesToTest = args[1];
		SourceType classesType = SourceUtils.classify(classesToTest);
		System.out.printf("About to test w/ claims from %s %s%n", claimType, claims );
		System.out.printf("... on classes from %s %s%n", classesType, classesToTest);
		if (claimType != SourceType.CLASS) {
			System.err.println("can only handle one claim class so far");			
			return;
		}
		Class<Claim> c = (Class<Claim>) Class.forName(claims);
		
		if (classesType != SourceType.CLASS) {
			System.err.println("can only handle one target class so far");			
			return;
		}
				
		Processor p = new Processor(c.newInstance());
		final boolean result = p.process(Class.forName(classesToTest));
		System.out.println(result ? "OK" : "Fail");
	}
}
