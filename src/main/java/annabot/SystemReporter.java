package annabot;

/** Reporter that just writes to System.err */
public class SystemReporter implements Reporter {

	public void report(Class<?> target, Claim claim) {
		System.err.printf("%s: %s%n", target, claim.getDescription());
		System.err.flush();
	}

}
