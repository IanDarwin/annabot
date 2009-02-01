package annabot;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.darwinsys.io.ClassSourceUtils;
import com.darwinsys.io.SourceType;
import com.darwinsys.io.SourceUtils;
import com.darwinsys.lang.GetOpt;
import com.darwinsys.lang.GetOptDesc;
import com.darwinsys.util.Debug;


public class AnnaBot {

	protected static void usage(int i) {
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
	 * @param args -c or -j to provide classpath used by the target classes
	 * @param args claims (classname, directory or jar)
	 * @param args target classes to test (classname, directory or jar).
	 */
	public static void main(String[] args) throws Exception {
		GetOptDesc options[] = {
			new GetOptDesc('c', "classpath", true),
			new GetOptDesc('j', "jarDir", true),
		};
		GetOpt parser = new GetOpt(options);
		Map<String,String> optionsFound = parser.parseArguments(args);
		if (optionsFound.get("?") != null) {
			usage(1);
		}
		List<String> classPathElements = new ArrayList<String>();
		String cpe;
		if ((cpe = optionsFound.get("c")) != null) {
			final String[] split = cpe.split(":");
			classPathElements.addAll(Arrays.asList(split));
		}
		if ((cpe = optionsFound.get("j")) != null) {
			File f = new File(cpe);
			File[] jars = f.listFiles(new FilenameFilter() {
				public boolean accept(File dir, String name) {
					return name.endsWith(".jar");
				}		
			});
			if (jars != null || jars.length > 0) {
				for (File ff : jars) {
				classPathElements.add(ff.getCanonicalPath());
				}
			}
		}
		List<String> restOfArgs = parser.getFilenameList();
		if (restOfArgs.size() != 2) {
			usage(1);
		}
		String claims = restOfArgs.get(0);
		SourceType claimType = SourceUtils.classify(claims);
		String classesToTest = restOfArgs.get(1);
		SourceType classesType = SourceUtils.classify(classesToTest);
		System.out.printf("Getting list of claims from %s %s%n", claimType, claims );
		List<Class<?>> claimClasses = ClassSourceUtils.classListFromSource(claims);
		System.out.printf("Getting list of classes from %s %s%n", classesType, classesToTest);
		List<Class<?>> targets = ClassSourceUtils.classListFromSource(classesToTest, classPathElements);
		
		process(targets, claimClasses);
	}

	/**
	 * The big bad expensive mXn loop: do all the work,
	 * running m claims on each of n targets, report results.
	 * @param targets
	 * @param claimClasses
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	@SuppressWarnings("unchecked")
	static void process(List<Class<?>> targets, List<Class<?>> claimClasses) throws Exception {
		int classes = 0, errs = 0;
		
		for (Class<?> target : targets) {
			Debug.printf("annabot", "Class %s%n", target);
			++classes;

			for (Class<?>c : claimClasses) {

				Class<Claim> cc = (Class<Claim>)c;
				Processor p = new Processor(cc.newInstance());

				// process() will report errors via
				// Process.reporter.repor().
				errs += p.process(target) ? 0 : 1;
			}
		}
		System.out.printf(
			"AnnaBot: found %d error(s) in %d classes%n", errs, classes);
	}
}
