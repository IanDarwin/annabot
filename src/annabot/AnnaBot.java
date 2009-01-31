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
		
		// The big bad expensive NxM loop: do all the work.
		for (Class<?>c : claimClasses) {
			
			Class<Claim> cc = (Class<Claim>)c;
			Processor p = new Processor(cc.newInstance());

			for (Class<?> clazz : targets) {
				System.out.printf("Class %s Result %s%n", clazz, 
						p.process(clazz) ? "OK" : "Fail");
			}
		}
	}
}
