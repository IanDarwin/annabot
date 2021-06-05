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

/*
 * Annotations Assertion Based Object Testing for Java
 * A version in which the user provides a pre-compiled
 * parse list and a package name. The DSL is effectively
 * a set of Constructor calls.
 */
public class AnnaBot {

	protected static void usage(int i) {
		final String usage =
			"Usage: AnnaBot claims classes";
		System.err.println(usage);
		System.exit(i);
	}	
	
	/**
	 * @param args -c or -j to provide classpath used by the target classes
	 * @param args claims (classname, directory or jar)
	 * @param args target classes to test (classname, directory or jar).
	 */
	public static void main(String[] args) throws Exception {
		GetOptDesc options[] = {
			new GetOptDesc('c', "classpath", true),
			new GetOptDesc('j', "jarDir", true),
		};
		GetOpt argsParser = new GetOpt(options);
		Map<String,String> optionsFound = argsParser.parseArguments(args);
		if (optionsFound.get("?") != null) {
			usage(1);
		}
		List<String> classPathElements = new ArrayList<String>();
		String cpe;
		if ((cpe = optionsFound.get("c")) != null) {
			final String[] split = cpe.split(File.pathSeparator);
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
		List<String> restOfArgs = argsParser.getFilenameList();
		if (restOfArgs.size() != 2) {
			usage(1);
		}
		String claimSource = restOfArgs.get(0);
		SourceType claimType = SourceUtils.classify(claimSource);
		String classesToTest = restOfArgs.get(1);

		long now = System.currentTimeMillis();

		SourceType classesType = SourceUtils.classify(classesToTest);
		List<Class<?>> claimClasses = ClassSourceUtils.classListFromSource(claimSource);
		List<Claim> claims = new ArrayList<Claim>(claimClasses.size());
		for (Class<?> c : claimClasses) {
			try {
				claims.add((Claim)c.newInstance());
			} catch (Exception e) {
				System.err.println(e);
			}
		}
		System.out.printf("Using %d claims from %s %s%n",
				claims.size(), claimType.toString().toLowerCase(), claimSource );
		List<Class<?>> targetClasses = ClassSourceUtils.classListFromSource(classesToTest, classPathElements);
		System.out.printf("Checking %d targets from %s %s%n",
				targetClasses.size(), classesType.toString().toLowerCase(), classesToTest);
		
		long end = System.currentTimeMillis();
		System.out.printf(
			"AnnaBot: Initialization took %.1f seconds%n", 
			(end - now) / 1000D);
		
		preVerify(claims);
		process(targetClasses, claims);
		postVerify(claims);
	}

	/**
	 * The big bad expensive mXn loop: do all the work,
	 * running m claims on each of n targets, report results.
	 * @param targets
	 * @param claims
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	@SuppressWarnings("unchecked")
	public static void process(List<Class<?>> targets, List<Claim> claims) throws Exception {
		int classes = 0, errs = 0;
		long now = System.currentTimeMillis();
		
		for (Class<?> target : targets) {
			Debug.printf("annabot", "Class %s%n", target);
			++classes;

			for (Claim c : claims) {

				Processor p = new Processor(c);

				// process() will report errors via
				// Process.reporter.report().
				// System.out.println(p + " processing " + target);
				errs += p.process(target) ? 0 : 1;
			}
		}
		long end = System.currentTimeMillis();
		System.out.printf(
			"AnnaBot: found %d error(s) in %d classes, time %.1f seconds%n", 
			errs, classes, (end - now) / 1000D);
	}

	static void preVerify(List<Claim> claims) throws InstantiationException, IllegalAccessException {
		for (Claim c : claims) {
			if (c instanceof PrePostVerify) {
				((PrePostVerify)c).preVerify();
			}
		}
	}
	static void postVerify(List<Claim> claims) throws InstantiationException, IllegalAccessException {
		for (Claim c : claims) {
			if (c instanceof PrePostVerify) {
				((PrePostVerify)c).postVerify();
			}
		}
	}
}
