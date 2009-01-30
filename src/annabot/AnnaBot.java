package annabot;

import java.util.List;

import com.darwinsys.io.ClassSourceUtils;
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
		System.out.printf("Getting list of claims from %s %s%n", claimType, claims );
		List<Class<?>> claimClasses = ClassSourceUtils.classListFromSource(claims);
		System.out.printf("Getting list of classes from %s %s%n", classesType, classesToTest);
		List<Class<?>> targets = ClassSourceUtils.classListFromSource(classesToTest);
		
		// The big bad expensive NxM loop: do all the work.
		for (Class<?>c : claimClasses) {
			
			Class<Claim> cc = (Class<Claim>)c;
			Processor p = new Processor(cc.newInstance());

			for (Class<?> clazz : targets) {
				System.out.printf("Class %s Result %s", classesToTest, 
						p.process(clazz) ? "OK" : "Fail");
			}
		}
	}
}
